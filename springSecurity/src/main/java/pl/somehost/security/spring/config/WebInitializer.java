package pl.somehost.security.spring.config;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import pl.somehost.security.spring.config.SpringContextInitializer;

public class WebInitializer  implements WebApplicationInitializer{

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext applicationContext =
                new AnnotationConfigWebApplicationContext();
        applicationContext.register(SpringContextInitializer.class);

        ServletRegistration.Dynamic dynamic =
                servletContext.addServlet("front-controller", new DispatcherServlet(applicationContext));
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping("/");

    }

}