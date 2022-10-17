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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/buildables")
@RequiredArgsConstructor
public class BuildableController {
    private final BuildableService buildableService;

    @GetMapping
    public ResponseEntity<List<BuildableDto>> getBuildables() {
        return ResponseEntity.ok().body(buildableService.getBuildableDtos());
    }

    @GetMapping("/{id}")
    @PreAuthorize("@buildableServiceImpl.belongsToUser(#buildableId)")
    public ResponseEntity<BuildableDto> getBuildable(@P("buildableId") @PathVariable("id") Long buildableId) {
        BuildableDto buildableDto = buildableService.getDtoById(buildableId);
        if (buildableDto != null) return ResponseEntity.ok().body(buildableDto);

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/data")
    public ResponseEntity<BuildableDataDto> getBuildableData() {
        return ResponseEntity.ok().body(buildableService.getBuildableData());
    }

    @PostMapping("/build")
    public ResponseEntity<Buildable> build(@Valid @RequestBody BuildableBuildDto buildableBuildDto) {
        return ResponseEntity.created(URI.create("/api/buildables/build")).body(buildableService.build(buildableBuildDto));
    }

    @PutMapping("/move")
    @PreAuthorize("@buildableServiceImpl.belongsToUser(#buildableMoveDto.id())")
    public ResponseEntity<Buildable> move(@P("buildableMoveDto") @Valid @RequestBody BuildableMoveDto buildableMoveDto) {
        Buildable buildable = buildableService.move(buildableMoveDto);
        if (buildable != null) return ResponseEntity.ok().body(buildable);

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/demolish")
    @PreAuthorize("@buildableServiceImpl.belongsToUser(#buildableDemolishDto.buildableId())")
    public ResponseEntity<Boolean> demolish(@P("buildableDemolishDto") @Valid @RequestBody BuildableDemolishDto buildableDemolishDto) {
        // TODO - Take only needed citizenIds to delete in validation and only remove needed in service
        if (buildableService.demolish(buildableDemolishDto)) return ResponseEntity.ok().body(true);

        return ResponseEntity.notFound().build();
    }
}
