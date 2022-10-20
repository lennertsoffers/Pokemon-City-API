package com.lennertsoffers.pokemon_city_api.controller;

import com.lennertsoffers.pokemon_city_api.model.User;
import com.lennertsoffers.pokemon_city_api.model.dto.UserDataDto;
import com.lennertsoffers.pokemon_city_api.model.dto.UserUpdateStatisticsDto;
import com.lennertsoffers.pokemon_city_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <b>/api/users</b>
 * <p>RestController that handles requests concerning users</p>
 * @see User
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
@Validated
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * <b>/users/me</b>
     * <p>Returns the data about the user sending the request</p>
     * @return The UserData of the authenticated user
     * @see UserDataDto
     */
    @GetMapping("/me")
    public ResponseEntity<UserDataDto> getUserData() {
        return ResponseEntity.ok().body(userService.getUserData());
    }

    /**
     * <b>/users/me/updateStatistics</b>
     * <p>Updates the statics of the user that sends the request, with the values provided in the RequestBody</p>
     * @param userUpdateStatisticsDto The parsed RequestBody to a UserUpdateStatisticsDto containing values to update the user's statistics
     * @return A response with status code OK
     * @see UserUpdateStatisticsDto
     */
    @PostMapping("/me/updateStatistics")
    public ResponseEntity<?> updateStatistics(@RequestBody UserUpdateStatisticsDto userUpdateStatisticsDto) {
        userService.updateStatistics(userUpdateStatisticsDto);
        return ResponseEntity.ok().build();
    }
}
