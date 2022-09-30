package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.Location;
import com.lennertsoffers.pokemon_city_api.model.type.BuildableTypeEnum;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class IncomeBuildingDto extends BuildableDto {
    private final LocalDateTime lastCollected;

    public IncomeBuildingDto(Long id, String name, int satisfactionModifier, int xpWhenFinished, int price, int unlockedAtLevel, int height, int width, Location location, BuildableTypeEnum buildableTypeEnum, LocalDateTime lastCollected) {
        super(id, name, satisfactionModifier, xpWhenFinished, price, unlockedAtLevel, height, width, location, buildableTypeEnum);
        this.lastCollected = lastCollected;
    }
}
