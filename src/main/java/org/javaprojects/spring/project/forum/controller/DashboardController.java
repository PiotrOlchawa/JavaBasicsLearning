package org.javaprojects.spring.project.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping(path = "/dashboard", produces = "application/html")
    public String getDashboard(){
        return "dashboard";
    }

}
