package com.pahod.testoauth2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;

@Controller
@RequestMapping("/")
public class MyController {

    @GetMapping("/")
    public String home() {
        return "home";
    }


    @GetMapping("/moderator_page")
    @RolesAllowed("MODERATOR")
    public String moderatorPage() {
        return "moderator_page";
    }

    @GetMapping("/admin_page")
    @RolesAllowed("ADMIN")
    public String adminPage() {
        return "admin_page";
    }
}
