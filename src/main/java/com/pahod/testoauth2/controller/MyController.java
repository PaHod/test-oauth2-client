package com.pahod.testoauth2.controller;

import com.pahod.testoauth2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MyController {


    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/hr_info")
    public String hrInfo() {
        return "hr_info";
    }

    @GetMapping("/manager_info")
    public String managerInfo() {
        return "manager_info";
    }
}
