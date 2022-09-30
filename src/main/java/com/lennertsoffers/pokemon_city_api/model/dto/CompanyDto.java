package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.Location;
import com.lennertsoffers.pokemon_city_api.model.type.BuildableTypeEnum;
import com.lennertsoffers.pokemon_city_api.model.type.CompanyType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CompanyDto extends IncomeBuildingDto {
    private final CompanyType companyType;
    private final int profitPerMinute;

    public CompanyDto(Long id, String name, int satisfactionModifier, int xpWhenFinished, int price, int unlockedAtLevel, int height, int width, Location location, BuildableTypeEnum buildableTypeEnum, LocalDateTime lastCollected, CompanyType companyType, int profitPerMinute) {
        super(id, name, satisfactionModifier, xpWhenFinished, price, unlockedAtLevel, height, width, location, buildableTypeEnum, lastCollected);
        this.companyType = companyType;
        this.profitPerMinute = profitPerMinute;
    }
}

