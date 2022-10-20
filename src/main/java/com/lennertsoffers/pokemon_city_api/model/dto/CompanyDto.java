package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.Company;
import com.lennertsoffers.pokemon_city_api.model.Location;
import com.lennertsoffers.pokemon_city_api.model.SpritesheetLocation;
import com.lennertsoffers.pokemon_city_api.model.type.BuildableTypeEnum;
import com.lennertsoffers.pokemon_city_api.model.type.CompanyType;
import com.lennertsoffers.pokemon_city_api.model.type.SpecialisationType;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * <p>DTO containing all important info about a company</p>
 * @see Company
 */
@Getter
public class CompanyDto extends IncomeBuildingDto {
    private final CompanyType companyType;
    private final int profitPerMinute;
    private final double employeeMultiplier;
    private final int maxAssignedCitizens;
    private final int incomePerMinute;
    private final int assignedCitizens;
    private final SpecialisationType specialisationType;

    public CompanyDto(Long id, String name, int satisfactionModifier, int xpWhenFinished, int price, int unlockedAtLevel, int height, int width, Location location, BuildableTypeEnum buildableTypeEnum, LocalDateTime lastCollected, CompanyType companyType, int profitPerMinute, double employeeMultiplier, int maxAssignedCitizens, SpecialisationType specialisationType, SpritesheetLocation spritesheetLocation, int incomePerMinute, int assignedCitizens) {
        super(id, name, satisfactionModifier, xpWhenFinished, price, unlockedAtLevel, height, width, location, buildableTypeEnum, lastCollected, spritesheetLocation);
        this.companyType = companyType;
        this.profitPerMinute = profitPerMinute;
        this.employeeMultiplier = employeeMultiplier;
        this.maxAssignedCitizens = maxAssignedCitizens;
        this.specialisationType = specialisationType;
        this.incomePerMinute = incomePerMinute;
        this.assignedCitizens = assignedCitizens;
    }
}

