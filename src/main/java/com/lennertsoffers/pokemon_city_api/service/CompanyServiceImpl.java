package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.dto.CompanyWithEmployeesDto;
import com.lennertsoffers.pokemon_city_api.model.mapper.CompanyMapper;
import com.lennertsoffers.pokemon_city_api.repository.IncomeBuildingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyMapper companyMapper;
    private final IncomeBuildingRepository incomeBuildingRepository;
    private final UserService userService;

    @Override
    public List<CompanyWithEmployeesDto> getCompaniesWithEmployees() {
        return incomeBuildingRepository
                .getAllCompaniesFromUser(userService.getAuthUser().getId())
                .stream()
                .map(companyMapper::toCompaniesWithEmployeesDto)
                .toList();
    }
}
