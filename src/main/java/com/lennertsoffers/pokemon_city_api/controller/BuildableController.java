package com.lennertsoffers.pokemon_city_api.controller;

import com.lennertsoffers.pokemon_city_api.model.Buildable;
import com.lennertsoffers.pokemon_city_api.model.dto.BuildableBuildDto;
import com.lennertsoffers.pokemon_city_api.model.dto.BuildableDto;
import com.lennertsoffers.pokemon_city_api.model.dto.BuildableMoveDto;
import com.lennertsoffers.pokemon_city_api.service.BuildableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
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
    public ResponseEntity<Buildable> build(@Valid @RequestBody BuildableBuildDto buildableBuildDto) {
        return ResponseEntity.created(URI.create("/api/buildables/build")).body(buildableService.build(buildableBuildDto));
    }

    @PutMapping("/move/{id}")
    @PreAuthorize("@buildableServiceImpl.belongsToUser(#id)")
    public ResponseEntity<Buildable> move(@P("id") @PathVariable("id") Long id , @Valid @RequestBody BuildableMoveDto buildableMoveDto) {
        return ResponseEntity.ok().body(buildableService.move(id, buildableMoveDto));
    }

    @DeleteMapping("/demolish/{id}")
    @PreAuthorize("@buildableServiceImpl.belongsToUser(#id)")
    public ResponseEntity<Boolean> demolish(@P("id") @PathVariable("id") Long id) {
        if (buildableService.demolish(id)) return ResponseEntity.ok().body(true);
        return ResponseEntity.notFound().build();
    }
}
