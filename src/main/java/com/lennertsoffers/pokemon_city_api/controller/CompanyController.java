package com.lennertsoffers.pokemon_city_api.controller;

import com.lennertsoffers.pokemon_city_api.model.Company;
import com.lennertsoffers.pokemon_city_api.model.dto.CompanyWithEmployeesDto;
import com.lennertsoffers.pokemon_city_api.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b>/api/companies</b>
 * <p>RestController that handles requests concerning companies</p>
 * @see Company
 */
@RestController
@RequestMapping("/api/companies")
@CrossOrigin(origins = "*")
@Validated
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    /**
     * <b>/api/companies</b>
     * <p>Returns a list of all the companies of the authenticated user</p>
     * @return A list of the user's companies
     */
    @GetMapping
    public ResponseEntity<List<CompanyWithEmployeesDto>> getCompanies() {
        return ResponseEntity.ok().body(this.companyService.getCompaniesWithEmployees());
    }
}
