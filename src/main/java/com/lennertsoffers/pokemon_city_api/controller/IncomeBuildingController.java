package com.lennertsoffers.pokemon_city_api.controller;

import com.lennertsoffers.pokemon_city_api.service.IncomeBuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8080"})
@RestController
@RequestMapping("/api/incomeBuildings")
@RequiredArgsConstructor
public class IncomeBuildingController {
    private final IncomeBuildingService incomeBuildingService;

    @GetMapping("/collectRent/{id}")
    @PreAuthorize("@buildableServiceImpl.belongsToUser(#id)")
    public ResponseEntity<Integer> collectRent(@P("id") @PathVariable Long id) {
        Integer collectedAmount = incomeBuildingService.collect(id);

        if (collectedAmount != null) {
            return ResponseEntity.ok().body(collectedAmount);
        }

        return ResponseEntity.notFound().build();
    }
}
