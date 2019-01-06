package org.javaprojects.spring.project.forum.controller;

import org.javaprojects.spring.project.forum.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping(path = "/dashboard", produces = "application/html")
    public String getDashboard(@AuthenticationPrincipal User user, ModelMap model)
    {
        model.put("user", user);
        return "dashboard";
    }
}
