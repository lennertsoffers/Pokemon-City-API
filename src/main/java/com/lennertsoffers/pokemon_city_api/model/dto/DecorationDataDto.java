package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.SpritesheetLocation;

public record DecorationDataDto (
        String name,
        int satisfactionModifier,
        int xpWhenFinished,
        int price,
        int unlockedAtLevel,
        int width,
        int height,
        SpritesheetLocation spritesheetLocation
) {}
