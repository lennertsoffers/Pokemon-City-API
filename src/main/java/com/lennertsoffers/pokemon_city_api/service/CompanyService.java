package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.dto.CompanyWithEmployeesDto;

import java.util.List;

public interface CompanyService {
    /**
     * Returns the list of the users companies together with their employees as DTO
     * @return A list of CompanyWithEmployeesDto
     */
    List<CompanyWithEmployeesDto> getCompaniesWithEmployees();
}
