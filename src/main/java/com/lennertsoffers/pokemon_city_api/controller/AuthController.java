package com.lennertsoffers.pokemon_city_api.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lennertsoffers.pokemon_city_api.exception.AuthFilterExceptionHandler;
import com.lennertsoffers.pokemon_city_api.model.Role;
import com.lennertsoffers.pokemon_city_api.model.User;
import com.lennertsoffers.pokemon_city_api.model.dto.UserCreationDto;
import com.lennertsoffers.pokemon_city_api.service.UserService;
import com.lennertsoffers.pokemon_city_api.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>/auth</b>
 * <p>RestController that handles all authentication requests</p>
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@Validated
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    /**
     * <b>/auth/refreshToken</b><br/>
     * <p>Refreshes the JWT token for the client</p>
     * <p>
     *     If the client sends a request providing a valid refresh_token, a new access_token gets generated and the old one gets replaced
     * </p>
     * <p>
     *     If the client doesn't provide an refresh_token, the refreshing will fail and the client will get a unsuccessful response
     * </p>
     *
     * @param request The incoming request from the client having a refresh_token
     * @param response The response in the chain
     * @throws IOException If the writing to the response goes wrong
     */
    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Read the authorization heard from the request
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Only continue if a bearer token is provided in the authorization header
        if (JwtTokenUtils.hasBearerToken(authorizationHeader)) {
            try {
                // Decode the token to get the username and roles
                DecodedJWT decodedJWT = JwtTokenUtils.decodeJwt(authorizationHeader);
                String username = decodedJWT.getSubject();
                // Get the user from the database
                User user = userService.getUser(username);

                // Generate a new token
                String accessToken = JwtTokenUtils.genToken(
                        username,
                        "roles",
                        user.getRoles().stream().map(Role::getName).toList(),
                        request.getRequestURL().toString(),
                        JwtTokenUtils.DEFAULT_JWT_ALIVE_TIME
                );

                // Put both the newly generated access_token and the old refresh_token in a map
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", authorizationHeader.substring("Bearer ".length()));

                // Write the map with the tokens to the response
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception e) {
                AuthFilterExceptionHandler.handle(e, response);
            }
        }
    }

    /**
     * <b>/auth/register</b><br/>
     * <p>
     *     Registers a user with the data provided in the RequestBody
     * </p>
     * <p>
     *     Upon creation a new city gets created that is linked to the user, as well as a new statistics object
     * </p>
     *
     * @param userCreationDto The parsed RequestBody to a UserCreationDto holding all the information to create a new user
     * @param request The incoming request from the client having a valid RequestBody that can get parsed to a UserCreationDto
     * @param response The response in the chain
     * @throws IOException If the writing to the response goes wrong
     * @see UserService
     * @see UserCreationDto
     */
    @PostMapping("/register")
    public void register(@Valid @RequestBody UserCreationDto userCreationDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Register a new user via theUserService
        User user = userService.register(userCreationDto);

        // Add the tokens to the response
        JwtTokenUtils.addTokensToResponse(
                user.getUsername(),
                user.getRoles().stream().map(Role::getName).toList(),
                request.getRequestURL().toString(),
                response
        );
    }
}












