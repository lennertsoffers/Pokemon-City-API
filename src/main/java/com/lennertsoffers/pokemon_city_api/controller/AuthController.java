package com.lennertsoffers.pokemon_city_api.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lennertsoffers.pokemon_city_api.exception.AuthFilterExceptionHandler;
import com.lennertsoffers.pokemon_city_api.model.City;
import com.lennertsoffers.pokemon_city_api.model.Role;
import com.lennertsoffers.pokemon_city_api.model.User;
import com.lennertsoffers.pokemon_city_api.model.dto.UserCreationDto;
import com.lennertsoffers.pokemon_city_api.repository.CityRepository;
import com.lennertsoffers.pokemon_city_api.service.CityService;
import com.lennertsoffers.pokemon_city_api.service.UserService;
import com.lennertsoffers.pokemon_city_api.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static com.lennertsoffers.pokemon_city_api.security.RoleType.*;

@RestController
@RequestMapping("/auth")
@Validated
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final CityService cityService;

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

    @PostMapping("/register")
    public void register(@Valid @RequestBody UserCreationDto userCreationDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = new User(userCreationDto.username(), userCreationDto.password());
        City city = new City();
        city.setName(userCreationDto.username() + " city");
        city.setDateCreated(LocalDate.now());
        city.setUser(user);

        userService.saveUser(user);
        cityService.save(city);

        user = userService.addRoleToUser(userCreationDto.username(), PLAYER);

        JwtTokenUtils.addTokensToResponse(
                user.getUsername(),
                user.getRoles().stream().map(Role::getName).toList(),
                request.getRequestURL().toString(),
                response
        );
    }
}












