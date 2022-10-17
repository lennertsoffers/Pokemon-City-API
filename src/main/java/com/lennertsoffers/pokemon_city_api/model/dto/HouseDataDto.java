package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.SpritesheetLocation;

public record HouseDataDto(
    String name,
    int satisfactionModifier,
    int xpWhenFinished,
    int price,
    int unlockedAtLevel,
    int width,
    int height,
    int numberOfCitizens,
    int maxRent,
    int rentPerMinute,
    SpritesheetLocation spritesheetLocation
) {}
