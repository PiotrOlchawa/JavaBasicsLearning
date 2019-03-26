package org.javaprojects.spring.project.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class StaticWebPageController {
    private static final int MY_DIGIT = 5;

    @RequestMapping("/thymeleaf")
    private String index(Map<String, Object> model) {
        model.put("variable", "My Thymeleaf variable");
        model.put("one", 1);
        model.put("two", 2);
        model.put("digit", MY_DIGIT);
        return "index";
    }
}
