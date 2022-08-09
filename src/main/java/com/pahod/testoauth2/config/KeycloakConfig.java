package com.pahod.testoauth2.config;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.authorization.client.AuthzClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {
    @Bean
    public KeycloakSpringBootConfigResolver keycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    @Bean
    public AuthzClient keycloakAuthClient(KeycloakSpringBootProperties props) {
        org.keycloak.authorization.client.Configuration configuration = new org.keycloak.authorization.client.Configuration(
                props.getAuthServerUrl(), props.getRealm(), props.getResource(), props.getCredentials(), null
        );
        return AuthzClient.create(configuration);
    }
}