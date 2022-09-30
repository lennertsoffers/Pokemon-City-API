package com.lennertsoffers.pokemon_city_api.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HouseType implements BuildableType {
    SMALL_HOUSE("Small House", -2, 100, 1, 0, 5, 5, 2, 50, 1);

    private final String name;
    private final int satisfactionModifier;
    private final int xpWhenFinished;
    private final int price;
    private final int unlockedAtLevel;
    private final int width;
    private final int height;
    private final int numberOfCitizens;
    private final int maxRent;
    private final int rentPerMinute;
}
