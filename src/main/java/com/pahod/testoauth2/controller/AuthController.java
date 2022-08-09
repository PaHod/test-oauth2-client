package com.pahod.testoauth2.controller;

import com.pahod.testoauth2.config.LoginResponseMessage;
import com.pahod.testoauth2.service.AuthService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @GetMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<LoginResponseMessage> login(String email, String pass) {
        val responseMessage = authService.login(email, pass);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseMessage);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception {
        request.logout();
        return "redirect:/";
    }

}
