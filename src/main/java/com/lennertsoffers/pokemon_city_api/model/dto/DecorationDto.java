package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.Location;
import com.lennertsoffers.pokemon_city_api.model.type.DecorationType;
import lombok.Getter;

@Getter
public class DecorationDto extends BuildableDto {
    private final DecorationType decorationType;

    public DecorationDto(Long id, String name, int satisfactionModifier, int xpWhenFinished, int price, int unlockedAtLevel, int height, int width, Location location, DecorationType decorationType) {
        super(id, name, satisfactionModifier, xpWhenFinished, price, unlockedAtLevel, height, width, location);
        this.decorationType = decorationType;
    }
}
