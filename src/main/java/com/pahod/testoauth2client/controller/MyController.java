package com.pahod.testoauth2client.controller;

import com.pahod.testoauth2client.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/")
public class MyController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AuthService authService;


    private final String resourceHost = "http://localhost:8085/";
    private final String authURL = "http://localhost:8080/realms/test_realm/protocol/openid-connect/token";


    @GetMapping("/login")
    public String login(@PathParam("username") String username, @PathParam("pass") String password) {
        MultiValueMap<String, String> mapForm = authService.getCredentialsMap(username, password);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(mapForm, headers);
        ResponseEntity<Object> authResponse = restTemplate.exchange(authURL, HttpMethod.POST, request, Object.class);

        authService.parseResponse(authResponse.getBody());
        String accessToken = authService.getAccessToken();

        return "Great, you logged-in.\n Now You can go to /adminMenu.\n Your token: " + accessToken;
    }

    @GetMapping("/{destination}")
    public String adminMenu(@PathVariable("destination") String destination) {
        String accessToken = authService.getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.set("Authorization", "Bearer " + accessToken);
        System.out.println("Authorization Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        String url = resourceHost + destination;
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return "Here is your content for the " + destination + ":\n " + exchange.getBody();
    }
}
