package com.lennertsoffers.pokemon_city_api.controller;

import com.lennertsoffers.pokemon_city_api.model.City;
import com.lennertsoffers.pokemon_city_api.model.dto.CityChangeNameDto;
import com.lennertsoffers.pokemon_city_api.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <b>/api/city</b>
 * <p>RestController that handles requests concerning cities</p>
 * @see City
 */
@RestController
@RequestMapping("/api/city")
@CrossOrigin(origins = "*")
@Validated
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    /**
     * <b>/api/city/changeName</b>
     * <p>Changes the name of the city to the name provided in the RequestBody</p>
     * @param cityChangeNameDto The parsed RequestBody to a CityChangeNameDto containing all the information to change the name of a city
     * @return The new name of the city
     * @see CityChangeNameDto
     */
    @PostMapping("/changeName")
    public ResponseEntity<String> changeName(@Valid @RequestBody CityChangeNameDto cityChangeNameDto) {
        return ResponseEntity.ok().body(cityService.changeCityName(cityChangeNameDto));
    }
}
