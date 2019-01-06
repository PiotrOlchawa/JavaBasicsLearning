package org.javaprojects.spring.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

//@Configuration
@SpringBootApplication
public class SpringProjectApplication {

    public static final Logger LOGGER = LoggerFactory.getLogger(SpringBootApplication.class);

    public static void main(String[] args) {
        ApplicationContext applcationContext = SpringApplication.run(SpringProjectApplication.class, args);

        if (LOGGER.isDebugEnabled()) {
            for (String name : applcationContext.getBeanDefinitionNames()) {
                System.out.println(name);
            }
        }
    }

    // Internationalization
    @Bean
    public LocaleResolver localeResolver() {
        //SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        // OR
        AcceptHeaderLocaleResolver sessionLocaleResolver = new AcceptHeaderLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.US);
        return sessionLocaleResolver;
    }

    // Custom messages source
    // aplication.properties replaces this bean
/*    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("messages");
        return resourceBundleMessageSource;
    }*/
}
