package com.lennertsoffers.pokemon_city_api.controller;

import com.lennertsoffers.pokemon_city_api.model.Road;
import com.lennertsoffers.pokemon_city_api.model.dto.BuildRoadsDto;
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
    public ResponseEntity<List<Road>> getRoads() {
        return ResponseEntity.ok().body(roadService.getRoads());
    }

    @PostMapping("/buildRoads")
    public ResponseEntity<List<Road>> buildRoads(@Valid @RequestBody BuildRoadsDto buildRoadsDto) {
        return ResponseEntity.created(URI.create("/api/buildables/buildRoads")).body(roadService.buildRoads(buildRoadsDto));
    }
}
