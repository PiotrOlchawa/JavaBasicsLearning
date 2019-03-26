package pl.somehost.security.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.somehost.security.spring.domain.User;
import pl.somehost.security.spring.service.AdminService;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    AdminService adminService;

    @GetMapping(path = "/dashboard", produces = "application/html")
    public String getDashboard(@AuthenticationPrincipal User user, ModelMap model)
    {
        model.put("user", user);
        // This will generate exception because user has no admin role
        //List<User> userList = adminService.retriveAllUsers();
        return "dashboard";
    }
}
