package com.lennertsoffers.pokemon_city_api.controller;

import com.lennertsoffers.pokemon_city_api.model.Road;
import com.lennertsoffers.pokemon_city_api.model.dto.BuildRoadDto;
import com.lennertsoffers.pokemon_city_api.model.dto.RoadDto;
import com.lennertsoffers.pokemon_city_api.service.RoadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * <b>/api/roads</b>
 * <p>RestController that handles requests concerning roads</p>
 * @see Road
 */
@RestController
@RequestMapping("/api/roads")
@CrossOrigin(origins = "*")
@Validated
@RequiredArgsConstructor
public class RoadController {
    private final RoadService roadService;

    /**
     * <b>/api/roads</b>
     * <p>Returns a list of all the roads of the authenticated user</p>
     * @return A list of the user's roads
     */
    @GetMapping
    public ResponseEntity<List<RoadDto>> getRoads() {
        return ResponseEntity.ok().body(roadService.getRoads());
    }

    /**
     * <b>/api/roads/buildRoad</b>
     * <p>Builds a road using the data provided in the RequestBody</p>
     * @param buildRoadDto The parsed RequestBody to a BuildRoadDto containing all the information build a new road
     * @return A list of all the roads that belong to the user
     * @see BuildRoadDto
     */
    @PostMapping("/buildRoad")
    public ResponseEntity<List<RoadDto>> buildRoad(@Valid @RequestBody BuildRoadDto buildRoadDto) {
        return ResponseEntity.created(URI.create("/api/buildables/buildRoad")).body(roadService.buildRoad(buildRoadDto));
    }
}
