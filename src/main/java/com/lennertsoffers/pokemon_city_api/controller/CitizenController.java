package com.lennertsoffers.pokemon_city_api.controller;

import com.lennertsoffers.pokemon_city_api.model.Citizen;
import com.lennertsoffers.pokemon_city_api.model.dto.CitizenAssignmentDto;
import com.lennertsoffers.pokemon_city_api.model.dto.CitizenDto;
import com.lennertsoffers.pokemon_city_api.service.CitizenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <b>/api/citizens</b>
 * <p>RestController that handles requests concerning citizens</p>
 * @see Citizen
 */
@RestController
@RequestMapping("/api/citizens")
@CrossOrigin(origins = "*")
@Validated
@RequiredArgsConstructor
public class CitizenController {
    private final CitizenService citizenService;

    /**
     * <b>/api/citizens</b>
     * <p>Returns a list of all the citizens living in the city of the authenticated user</p>
     * @return A list of citizens
     */
    @GetMapping
    public ResponseEntity<List<CitizenDto>> getCitizens() {
        return ResponseEntity.ok().body(citizenService.getAllFromCurrentUser());
    }

    /**
     * <b>/api/citizens/unassigned</b>
     * <p>Returns a list of the citizens living in the city of the authenticated user, but excludes citizens assigned to a company</p>
     * @return A list of citizens
     */
    @GetMapping("/unassigned")
    public ResponseEntity<List<CitizenDto>> getUnassignedCitizens() {
        return ResponseEntity.ok().body(citizenService.getUnassignedCitizensFromCurrentUser());
    }

    /**
     * <b>/api/citizens/assign</b>
     * <p>Assigns a citizen to a company</p>
     * <p>
     *     Before the request gets processed, the application checks if the provided citizenId belongs to the authenticated user. If this is not the case, a Forbidden status is returned.
     *     The same validations is conducted on the companyId
     * </p>
     * @param citizenAssignmentDto The parsed RequestBody to a CitizenAssignmentDto containing all the information to assign a citizen to a company
     * @return True if the assignment was successful, false otherwise
     * @see CitizenAssignmentDto
     */
    @PutMapping("/assign")
    @PreAuthorize("@citizenServiceImpl.belongsToUser(#citizenAssignmentDto.citizenId()) && @buildableServiceImpl.belongsToUser(#citizenAssignmentDto.companyId())")
    public ResponseEntity<Boolean> assignCitizen(@P("citizenAssignmentDto") @RequestBody CitizenAssignmentDto citizenAssignmentDto) {
        return citizenService.assignToCompany(citizenAssignmentDto) ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

    /**
     * <b>/api/citizens/unAssign/{id}</b>
     * <p>Un-assigns a citizens from a company</p>
     * <p>
     *     Before the request gets processed, the application checks if the provided citizenId belongs to the authenticated user.
     *     If this is not the case, a Forbidden status is returned
     * </p>
     * @param id The citizenId in the path
     * @return True if the un-assignment was successful, false otherwise
     */
    @PutMapping("/unAssign/{id}")
    @PreAuthorize("@citizenServiceImpl.belongsToUser(#id)")
    public ResponseEntity<Boolean> unAssignCitizen(@P("id") @PathVariable("id") Long id) {
        return citizenService.unAssign(id) ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }
}
