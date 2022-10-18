package com.lennertsoffers.pokemon_city_api.model.mapper;

import com.lennertsoffers.pokemon_city_api.model.Company;
import com.lennertsoffers.pokemon_city_api.model.dto.CompanyWithEmployeesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyMapper {
    private final CitizenMapper citizenMapper;

    public CompanyWithEmployeesDto toCompaniesWithEmployeesDto(Company company) {
        return new CompanyWithEmployeesDto(
                company.getId(),
                company.getName(),
                company.getSatisfactionModifier(),
                company.getXpWhenFinished(),
                company.getPrice(),
                company.getUnlockedAtLevel(),
                company.getHeight(),
                company.getWidth(),
                company.getLocation(),
                company.getBuildableTypeEnum(),
                company.getLastCollected(),
                company.getCompanyType(),
                company.getProfitPerMinute(),
                company.getEmployeeMultiplier(),
                company.getMaxAssignedCitizens(),
                company.getSpecialisationType(),
                company.getSpritesheetLocation(),
                company.getAssignedCitizens().stream().map(citizenMapper::toCitizenDto).toList(),
                (int) Math.round(company.getIncomePerMinute()),
                company.getAssignedCitizens().size()
        );
    }
}
