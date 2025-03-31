package com.example.votacionpresidencial.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            boolean isAdmin = authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
            boolean isVotante = authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_VOTANTE"));
            if (isAdmin) {
                return "redirect:/dashboard";
            } else if (isVotante) {
                return "redirect:/votacion";
            }
        }
        return "login";
    }
    @GetMapping("/dashboard")
    public String showDashboard() {return "dashboard";}

    @GetMapping("/votacion")
    public String showVotacionPage(Authentication authentication) {
            return "votantes/votacion";
    }
}
