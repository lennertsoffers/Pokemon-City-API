package com.lennertsoffers.pokemon_city_api.controller;

import com.lennertsoffers.pokemon_city_api.model.IncomeBuilding;
import com.lennertsoffers.pokemon_city_api.service.IncomeBuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <b>/api/incomeBuildings</b>
 * <p>RestController that handles requests concerning generic IncomeBuildings</p>
 * @see IncomeBuilding
 */
@RestController
@RequestMapping("/api/incomeBuildings")
@CrossOrigin(origins = "*")
@Validated
@RequiredArgsConstructor
public class IncomeBuildingController {
    private final IncomeBuildingService incomeBuildingService;

    /**
     * <b>/api/incomeBuildings/collectRent/{id}</b>
     * <p>Collects the rent for the IncomeBuilding with the id provided as a PathVariable</p>
     * <p>
     *     Before the request gets processed, the application checks if the provided IncomeBuildingId belongs to the authenticated user. If this is not the case, a Forbidden status is returned
     * </p>
     * @param id The id of the IncomeBuilding
     * @return The amount of rent that got collected
     */
    @GetMapping("/collectRent/{id}")
    @PreAuthorize("@buildableServiceImpl.belongsToUser(#id)")
    public ResponseEntity<Integer> collectRent(@P("id") @PathVariable Long id) {
        Integer collectedAmount = incomeBuildingService.collect(id);

        if (collectedAmount != null) return ResponseEntity.ok().body(collectedAmount);
        return ResponseEntity.notFound().build();
    }
}
