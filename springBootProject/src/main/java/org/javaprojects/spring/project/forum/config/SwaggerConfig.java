package org.javaprojects.spring.project.forum.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Configuration
//Enable Swagger
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public static final Contact DEFAULT_CONTACT = new Contact("Piotr","","mounting@protonmail.ch");
    private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Avesome Social media API",
            "Social media API",
            "0.1",
            "",
            "",
            "Apache 2.0",
            "");
    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES
            = new HashSet<String>(Arrays.asList("application/json","application/xml"));
//Bean -Docket
    // Swagger 2
    // All the paths
    // All the api-s

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }

}
