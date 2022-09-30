package com.lennertsoffers.pokemon_city_api.controller;

import com.lennertsoffers.pokemon_city_api.model.User;
import com.lennertsoffers.pokemon_city_api.model.dto.UserDataDto;
import com.lennertsoffers.pokemon_city_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/me")
    public ResponseEntity<UserDataDto> getUserData() {
        return ResponseEntity.ok().body(userService.getUserData());
    }
}
