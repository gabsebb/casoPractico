package com.example.votacionpresidencial.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:admin/dashboard";
        }
        return "login";
    }
    @GetMapping("/dashboard")
    public String showDashboard() {return "dashboard";}
}
