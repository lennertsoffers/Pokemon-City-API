package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.Location;
import com.lennertsoffers.pokemon_city_api.model.SpritesheetLocation;
import com.lennertsoffers.pokemon_city_api.model.type.BuildableTypeEnum;
import com.lennertsoffers.pokemon_city_api.model.type.CompanyType;
import com.lennertsoffers.pokemon_city_api.model.type.SpecialisationType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CompanyWithEmployeesDto extends CompanyDto {
    private final List<CitizenDto> employees;

    public CompanyWithEmployeesDto(Long id, String name, int satisfactionModifier, int xpWhenFinished, int price, int unlockedAtLevel, int height, int width, Location location, BuildableTypeEnum buildableTypeEnum, LocalDateTime lastCollected, CompanyType companyType, int profitPerMinute, double employeeMultiplier, int maxAssignedCitizens, SpecialisationType specialisationType, SpritesheetLocation spritesheetLocation, List<CitizenDto> employees, int incomePerMinute, int assignedCitizens) {
        super(id, name, satisfactionModifier, xpWhenFinished, price, unlockedAtLevel, height, width, location, buildableTypeEnum, lastCollected, companyType, profitPerMinute, employeeMultiplier, maxAssignedCitizens, specialisationType, spritesheetLocation, incomePerMinute, assignedCitizens);
        this.employees = employees;
    }
}
