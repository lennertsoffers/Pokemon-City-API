package com.lennertsoffers.pokemon_city_api.controller;

import com.lennertsoffers.pokemon_city_api.model.User;
import com.lennertsoffers.pokemon_city_api.model.dto.UserDataDto;
import com.lennertsoffers.pokemon_city_api.model.dto.UserUpdateStatisticsDto;
import com.lennertsoffers.pokemon_city_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8080"})
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

    @PostMapping("/me/updateStatistics")
    public ResponseEntity<?> updateStatistics(@RequestBody UserUpdateStatisticsDto userUpdateStatisticsDto) {
        userService.updateStatistics(userUpdateStatisticsDto);
        return ResponseEntity.ok().build();
    }
}
