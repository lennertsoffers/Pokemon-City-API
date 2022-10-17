package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.dto.CompanyWithEmployeesDto;

import java.util.List;

public interface CompanyService {
    List<CompanyWithEmployeesDto> getCompaniesWithEmployees();
}
