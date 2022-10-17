package com.lennertsoffers.pokemon_city_api.controller;

import com.lennertsoffers.pokemon_city_api.model.dto.CityChangeNameDto;
import com.lennertsoffers.pokemon_city_api.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/city")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @PostMapping("/changeName")
    public ResponseEntity<String> changeName(@Valid @RequestBody CityChangeNameDto cityChangeNameDto) {
        return ResponseEntity.ok().body(cityService.changeCityName(cityChangeNameDto));
    }
}
