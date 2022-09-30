package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.Location;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class IncomeBuildingDto extends BuildableDto {
    private final LocalDateTime lastCollected;

    public IncomeBuildingDto(Long id, String name, int satisfactionModifier, int xpWhenFinished, int price, int unlockedAtLevel, int height, int width, Location location, LocalDateTime lastCollected) {
        super(id, name, satisfactionModifier, xpWhenFinished, price, unlockedAtLevel, height, width, location);
        this.lastCollected = lastCollected;
    }
}
