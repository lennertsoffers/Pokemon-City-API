package com.lennertsoffers.pokemon_city_api.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lennertsoffers.pokemon_city_api.exception.AuthFilterExceptionHandler;
import com.lennertsoffers.pokemon_city_api.model.Role;
import com.lennertsoffers.pokemon_city_api.model.User;
import com.lennertsoffers.pokemon_city_api.service.UserService;
import com.lennertsoffers.pokemon_city_api.util.JwtTokenUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (JwtTokenUtils.hasBearerToken(authorizationHeader)) {
            try {
                DecodedJWT decodedJWT = JwtTokenUtils.decodeJwt(authorizationHeader);

                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);

                String accessToken = JwtTokenUtils.genToken(
                        username,
                        "roles",
                        user.getRoles().stream().map(Role::getName).toList(),
                        request.getRequestURL().toString(),
                        JwtTokenUtils.DEFAULT_JWT_ALIVE_TIME
                );

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", authorizationHeader.substring("Bearer ".length()));

                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception e) {
                AuthFilterExceptionHandler.handle(e, response);
            }
        }
    }
}
