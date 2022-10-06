package com.lennertsoffers.pokemon_city_api.controller;

import com.lennertsoffers.pokemon_city_api.model.Buildable;
import com.lennertsoffers.pokemon_city_api.model.dto.*;
import com.lennertsoffers.pokemon_city_api.service.BuildableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/data")
    public ResponseEntity<BuildableDataDto> getBuildableData() {
        return ResponseEntity.ok().body(buildableService.getBuildableData());
    }

    @PostMapping("/build")
    public ResponseEntity<Buildable> build(@Valid @RequestBody BuildableBuildDto buildableBuildDto) {
        return ResponseEntity.created(URI.create("/api/buildables/build")).body(buildableService.build(buildableBuildDto));
    }

    @PutMapping("/move/{id}")
    @PreAuthorize("@buildableServiceImpl.belongsToUser(#id)")
    public ResponseEntity<Buildable> move(@P("id") @PathVariable("id") Long id , @Valid @RequestBody BuildableMoveDto buildableMoveDto) {
        Buildable buildable = buildableService.move(id, buildableMoveDto);
        if (buildable != null) return ResponseEntity.ok().body(buildable);

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/demolish")
    @PreAuthorize("@buildableServiceImpl.belongsToUser(#buildableDemolishDto.buildableId())")
    public ResponseEntity<Boolean> demolish(@P("buildableDemolishDto") @Valid @RequestBody BuildableDemolishDto buildableDemolishDto) {
        if (buildableService.demolish(buildableDemolishDto)) return ResponseEntity.ok().body(true);

        return ResponseEntity.notFound().build();
    }
}
