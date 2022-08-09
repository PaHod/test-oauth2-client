package com.pahod.testoauth2.service;

import com.pahod.testoauth2.config.LoginResponseMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.keycloak.authorization.client.AuthorizationDeniedException;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.util.HttpResponseException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthzClient authzClient;

    public LoginResponseMessage login(String email, String pass) {
        log.info("START login for user {}", email);
        try {
            val response = authzClient.authorization(email, pass)
                    .authorize();
            val result = LoginResponseMessage.builder()
                    .tokenType(response.getTokenType())
                    .token(response.getToken())
                    .build();
            log.info("FINISH login for user {} successfully", email);
            return result;
        } catch (AuthorizationDeniedException | HttpResponseException ex) {
            log.debug("Exception when login {}", email, ex);
            log.info("FINISH login for user {} is bad", email);
            throw ex;
        } catch (Exception ex) {
            log.error("Some error occurred during login");
            throw ex;
        }
    }
}
