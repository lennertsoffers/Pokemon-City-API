package com.lennertsoffers.pokemon_city_api.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration class where authentication and authorization gets configured
 */
public class AuthDsl extends AbstractHttpConfigurer<AuthDsl, HttpSecurity> {
    @Override
    public void configure(HttpSecurity http) {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        http.addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilter(new AuthenticationFilter(authenticationManager));
    }

    public static AuthDsl authDsl() {
        return new AuthDsl();
    }
}
