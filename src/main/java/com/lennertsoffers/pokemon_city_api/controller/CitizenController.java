package com.lennertsoffers.pokemon_city_api.controller;

import com.lennertsoffers.pokemon_city_api.model.dto.CitizenAssignmentDto;
import com.lennertsoffers.pokemon_city_api.model.dto.CitizenDto;
import com.lennertsoffers.pokemon_city_api.service.CitizenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8080"})
@RestController
@RequestMapping("/api/citizens")
@RequiredArgsConstructor
public class CitizenController {
    private final CitizenService citizenService;

    @GetMapping
    public ResponseEntity<List<CitizenDto>> getCitizens() {
        return ResponseEntity.ok().body(citizenService.getAllFromCurrentUser());
    }

    @GetMapping("/unassigned")
    public ResponseEntity<List<CitizenDto>> getUnassignedCitizens() {
        return ResponseEntity.ok().body(citizenService.getUnassignedCitizensFromCurrentUser());
    }

    @PutMapping("/assign")
    @PreAuthorize("@citizenServiceImpl.belongsToUser(#citizenAssignmentDto.citizenId()) && @buildableServiceImpl.belongsToUser(#citizenAssignmentDto.companyId())")
    public ResponseEntity<Boolean> assignCitizen(@P("citizenAssignmentDto") @RequestBody CitizenAssignmentDto citizenAssignmentDto) {
        return citizenService.assignToCompany(citizenAssignmentDto) ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

    @PutMapping("/unAssign/{id}")
    @PreAuthorize("@citizenServiceImpl.belongsToUser(#id)")
    public ResponseEntity<Boolean> unAssignCitizen(@P("id") @PathVariable("id") Long id) {
        return citizenService.unAssign(id) ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }
}
