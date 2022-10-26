package com.lennertsoffers.pokemon_city_api.controller;

import com.lennertsoffers.pokemon_city_api.model.Buildable;
import com.lennertsoffers.pokemon_city_api.model.dto.*;
import com.lennertsoffers.pokemon_city_api.service.BuildableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * <b>/api/buildables</b>
 * <p>RestController that handles requests concerning generic buildables</p>
 * @see Buildable
 */
@RestController
@RequestMapping("/api/buildables")
@CrossOrigin(origins = "*")
@Validated
@RequiredArgsConstructor
public class BuildableController {
    private final BuildableService buildableService;

    /**
     * <b>/api/buildables</b>
     * <p>Returns a list of all the buildables of the authenticated user</p>
     * @return A list of the user's buildables (excluding roads)
     */
    @GetMapping
    public ResponseEntity<List<BuildableDto>> getBuildables() {
        return ResponseEntity.ok().body(buildableService.getBuildableDtos());
    }

    /**
     * <b>/api/buildables/{id}</b>
     * <p>Returns the single buildable with the provided id in the request</p>
     * <p>
     *     Before the request gets processed, the application checks if the provided buildableId belongs to the authenticated user. If this is not the case, a Forbidden status is returned
     * </p>
     * @param buildableId The buildableId provided in the path
     * @return The buildable if its found, or a NotFound status if it's not found
     */
    @GetMapping("/{id}")
    @PreAuthorize("@buildableServiceImpl.belongsToUser(#buildableId)")
    public ResponseEntity<BuildableDto> getBuildable(@P("buildableId") @PathVariable("id") Long buildableId) {
        BuildableDto buildableDto = buildableService.getDtoById(buildableId);
        if (buildableDto != null) return ResponseEntity.ok().body(buildableDto);

        return ResponseEntity.notFound().build();
    }

    /**
     * <b>/api/buildables/data</b>
     * <p>Returns a list of all the static buildable data that is used in the application</p>
     * <p>This will translate to a list of all the TypeEnums that are present in the game</p>
     * @return A list containing all the static data for buildables
     */
    @GetMapping("/data")
    public ResponseEntity<BuildableDataDto> getBuildableData() {
        return ResponseEntity.ok().body(buildableService.getBuildableData());
    }

    /**
     * <b>/api/buildables/build</b>
     * <p>Builds and returns a buildable instance of the object that got built</p>
     * @param buildableBuildDto The parsed RequestBody to a BuildableBuildDto containing all the information to build a new Buildable
     * @return The information for the buildable that got built
     * @see BuildableBuildDto
     */
    @PostMapping("/build")
    public ResponseEntity<Buildable> build(@Valid @RequestBody BuildableBuildDto buildableBuildDto) {
        return ResponseEntity.created(URI.create("/api/buildables/build")).body(buildableService.build(buildableBuildDto));
    }

    /**
     * <b>/api/buildables/move</b>
     * <p>Moves and returns a buildable instance of the object that got moved</p>
     * <p>
     *     Before the request gets processed, the application will check if the provided buildableId belongs to the authenticated user. If this is not the case, a Forbidden status is returned
     * </p>
     * @param buildableMoveDto The parsed RequestBody to a BuildableMoveDto containing all the information to move a buildable
     * @return The information for the buildable that got moved
     * @see BuildableBuildDto
     */
    @PutMapping("/move")
    @PreAuthorize("@buildableServiceImpl.belongsToUser(#buildableMoveDto.id())")
    public ResponseEntity<Buildable> move(@P("buildableMoveDto") @Valid @RequestBody BuildableMoveDto buildableMoveDto) {
        Buildable buildable = buildableService.move(buildableMoveDto);
        if (buildable != null) return ResponseEntity.ok().body(buildable);

        return ResponseEntity.notFound().build();
    }

    /**
     * <b>/api/buildables/demolish</b>
     * <p>Demolishes a buildable by its id and returns a boolean indicating if the demolishing was successful or not</p>
     * <p>
     *     Before the request gets processed, the application will check if the provided buildableId belongs to the authenticated user. If this is not the case, a Forbidden status is returned
     * </p>
     * @param buildableDemolishDto The parsed RequestBody to a BuildableDemolishDto containing all the information to demolish a buildable
     * @return Returns true if the demolishing was successful, false if not
     * @see BuildableDemolishDto
     */
    @DeleteMapping("/demolish")
    @PreAuthorize("@buildableServiceImpl.belongsToUser(#buildableDemolishDto.buildableId())")
    public ResponseEntity<Boolean> demolish(@P("buildableDemolishDto") @Valid @RequestBody BuildableDemolishDto buildableDemolishDto) {
        if (buildableService.demolish(buildableDemolishDto)) return ResponseEntity.ok().body(true);

        return ResponseEntity.notFound().build();
    }
}


