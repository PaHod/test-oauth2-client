package com.pahod.testoauth2client.service;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.LinkedHashMap;

@Service
public class AuthService {
    private final String username = "test_admin";
    private final String password = "test_admin";
    private final String clientId = "test_client_id";
    private final String clientSecret = "RSzTO9xFH07339ovAbqXCu7Pq8aIIeP9";
    private final String grantType = "password";


    private String accessToken;
    private String tokenType;
    private String refreshToken;
    private int expires_in;
    private String scope;

    public MultiValueMap<String, String> getCredentialsMap(String username, String password) {
        MultiValueMap<String, String> mapForm = new LinkedMultiValueMap<>();
        mapForm.add("username", username);
        mapForm.add("password", password);
        mapForm.add("client_id", clientId);
        mapForm.add("client_secret", clientSecret);
        mapForm.add("grant_type", grantType);
        return mapForm;
    }

    public void parseResponse(Object body) {
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) body;
        if (map != null) {
            this.accessToken = (String) map.get("access_token");
            this.tokenType = (String) map.get("token_type");
            this.refreshToken = (String) map.get("refresh_token");
            this.expires_in = (int) map.get("expires_in");
            this.scope = (String) map.get("scope");
        }

    }

    public String getAccessToken() {
        return accessToken;
    }
}
