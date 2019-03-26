package pl.somehost.security.spring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"pl.somehost.security.spring.repository"},
        entityManagerFactoryRef = "emf",
        transactionManagerRef = "h2transactionManager")
@PropertySource(value= {"classpath:application.properties"}) // is associated with autowired environment at line 37
public class DatabaseConfig {

    public static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfig.class);

    @Autowired
    private Environment environment;

    @Value(value = "classpath:data.sql")
    Resource sqlData;
    @Value(value = "classpath:schema.sql")
    Resource sqlSchema;

    @Bean
    public DriverManagerDataSource getDataSource() {

        try {
            String sqlCreate, sqlPopulate = "none";
            sqlCreate = sqlSchema.getURL().toString();
            sqlPopulate = sqlData.getURI().toString();
            LOGGER.info("sciezka create: " + sqlCreate);
            LOGGER.info("sciezka populate:" + sqlPopulate);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("db.driver"));
        //dataSource.setUrl("jdbc:h2:file:./springSecurity;INIT=runscript from './data.sql'");

        dataSource.setUrl(environment.getRequiredProperty("db.url"));

        /*try {
            dataSource.setUrl("jdbc:h2:file:./springSecurity;INIT=runscript from '" + sqlSchema.getURL().toString() + "'\\;"
                    + "runscript from '" + sqlData.getURL().toString() + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        dataSource.setUsername(environment.getRequiredProperty("db.username"));
        dataSource.setPassword(environment.getRequiredProperty("db.password"));
        return dataSource;
    }

    // Default transaction naming transactionManager
    @Bean(name = "h2transactionManager")
    @Autowired
    public PlatformTransactionManager getTransactionManager(EntityManagerFactory emf) {
        JpaTransactionManager jpaTransaction = new JpaTransactionManager();
        jpaTransaction.setEntityManagerFactory(emf);
        return jpaTransaction;
    }

    @Bean(name = "emf")
    public LocalContainerEntityManagerFactoryBean getEMF() {

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(getDataSource());
        emf.setPersistenceUnitName("spring-jpa-unit");
        emf.setJpaVendorAdapter(getHibernateAdapter());
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        jpaProperties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        jpaProperties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        jpaProperties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));

        emf.setJpaProperties(jpaProperties);
        emf.setPackagesToScan("pl.somehost.security.spring");
        return emf;
    }

    @Bean
    public JpaVendorAdapter getHibernateAdapter() {
        return new HibernateJpaVendorAdapter();
    }


}
