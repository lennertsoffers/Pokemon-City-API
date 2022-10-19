package com.lennertsoffers.pokemon_city_api.controller;

import com.lennertsoffers.pokemon_city_api.model.dto.BuildRoadDto;
import com.lennertsoffers.pokemon_city_api.model.dto.RoadDto;
import com.lennertsoffers.pokemon_city_api.service.RoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/roads")
@RequiredArgsConstructor
public class RoadController {
    private final RoadService roadService;

    @GetMapping
    public ResponseEntity<List<RoadDto>> getRoads() {
        return ResponseEntity.ok().body(roadService.getRoads());
    }

    @PostMapping("/buildRoad")
    public ResponseEntity<List<RoadDto>> buildRoad(@Valid @RequestBody BuildRoadDto buildRoadDto) {
        return ResponseEntity.created(URI.create("/api/buildables/buildRoad")).body(roadService.buildRoad(buildRoadDto));
    }
}
