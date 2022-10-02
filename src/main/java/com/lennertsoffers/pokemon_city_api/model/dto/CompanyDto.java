package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.Citizen;
import com.lennertsoffers.pokemon_city_api.model.Location;
import com.lennertsoffers.pokemon_city_api.model.type.BuildableTypeEnum;
import com.lennertsoffers.pokemon_city_api.model.type.CompanyType;
import com.lennertsoffers.pokemon_city_api.model.type.SpecialisationType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CompanyDto extends IncomeBuildingDto {
    private final CompanyType companyType;
    private final int profitPerMinute;
    private final int employeeMultiplier;
    private final int maxAssignedCitizens;
    private final SpecialisationType specialisationType;
    private final List<Citizen> employees;

    public CompanyDto(Long id, String name, int satisfactionModifier, int xpWhenFinished, int price, int unlockedAtLevel, int height, int width, Location location, BuildableTypeEnum buildableTypeEnum, LocalDateTime lastCollected, CompanyType companyType, int profitPerMinute, int employeeMultiplier, int maxAssignedCitizens, SpecialisationType specialisationType, List<Citizen> employees) {
        super(id, name, satisfactionModifier, xpWhenFinished, price, unlockedAtLevel, height, width, location, buildableTypeEnum, lastCollected);
        this.companyType = companyType;
        this.profitPerMinute = profitPerMinute;
        this.employeeMultiplier = employeeMultiplier;
        this.maxAssignedCitizens = maxAssignedCitizens;
        this.specialisationType = specialisationType;
        this.employees = employees;
    }
}

