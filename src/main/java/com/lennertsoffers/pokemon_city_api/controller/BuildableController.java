package com.lennertsoffers.pokemon_city_api.controller;

import com.lennertsoffers.pokemon_city_api.model.Buildable;
import com.lennertsoffers.pokemon_city_api.model.User;
import com.lennertsoffers.pokemon_city_api.model.dto.BuildableCreationDto;
import com.lennertsoffers.pokemon_city_api.model.dto.BuildableDto;
import com.lennertsoffers.pokemon_city_api.service.BuildableService;
import com.lennertsoffers.pokemon_city_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/buildables")
@RequiredArgsConstructor
public class BuildableController {
    private final BuildableService buildableService;

    @GetMapping
    public ResponseEntity<List<BuildableDto>> getBuildables() {
        return ResponseEntity.ok().body(buildableService.getBuildableDtos());
    }

    @PostMapping("/build")
    public ResponseEntity<Buildable> build(@Valid @RequestBody BuildableCreationDto buildableCreationDto) {
        return ResponseEntity.created(URI.create("/api/buildables/build")).body(buildableService.build(buildableCreationDto));
    }
}
