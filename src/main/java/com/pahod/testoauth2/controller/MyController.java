package com.pahod.testoauth2.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class MyController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception {
        request.logout();
        return "redirect:/";
    }

    @GetMapping("/moderator_page")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorPage() {
        return "moderator_page";
    }

    @GetMapping("/admin_page")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminPage() {
        return "admin_page";
    }
}
