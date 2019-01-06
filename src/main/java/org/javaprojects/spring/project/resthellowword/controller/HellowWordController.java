package org.javaprojects.spring.project.resthellowword.controller;

import org.javaprojects.spring.project.resthellowword.domain.HellowWordBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
import java.util.Optional;

@RestController
public class HellowWordController {

    public static final Logger LOGGER = LoggerFactory.getLogger(HellowWordController.class);

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private LocaleResolver sessionLocaleResolver;

    @GetMapping(path = "/hellowWorld")
    public String hellowWorld() {
        return "Hellow World";
    }

    @GetMapping(path = "/hellowWorldBean")
    public HellowWordBean hellowWorldBean() {
        return new HellowWordBean("Hellow Word Bean");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hellowWorldBean/{name}")
    public HellowWordBean hellowWorldBeanPath(@PathVariable String name) {
        return new HellowWordBean(String.format("Hellow Word Bean, %s ", name));
    }

/*    @GetMapping(path = "/hellowWorldInternationalizedBean")
    public HellowWordBean hellowWorldInternationalizedBean(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return new HellowWordBean(
                messageSource.getMessage("good.morning.message", null, locale)
        );
    }*/
    /*OR*/

    @GetMapping(path = "/hellowWorldInternationalizedBean")
    public HellowWordBean hellowWorldInternationalizedBean() {
        return new HellowWordBean(
                messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale())
        );
    }


}
