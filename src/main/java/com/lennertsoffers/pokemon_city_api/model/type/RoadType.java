package com.lennertsoffers.pokemon_city_api.model.type;

import com.lennertsoffers.pokemon_city_api.model.SpritesheetLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumerates the different roads
 */
@Getter
@AllArgsConstructor
public enum RoadType implements BuildableType {
    SINGLE_ROAD("Single Road", 0, 0, 0, 0, 2, 2, new SpritesheetLocation(0, 9)),
    CROSSROAD("Crossroad", 0, 0, 0, 0, 2, 2, new SpritesheetLocation(2, 11)),
    TO_TOP_ROAD("To Top Road", 0, 0, 0, 0, 2, 2, new SpritesheetLocation(4, 13)),
    TO_BOTTOM_ROAD("To Bottom Road", 0, 0, 0, 0, 2, 2, new SpritesheetLocation(6, 15)),
    TO_LEFT_ROAD("To Left Road", 0, 0, 0, 0, 2, 2, new SpritesheetLocation(16, 25)),
    TO_RIGHT_ROAD("To Right Road", 0, 0, 0, 0, 2, 2, new SpritesheetLocation(18, 27)),
    HORIZONTAL_ROAD("Horizontal Road", 0, 0, 0, 0, 2, 2, new SpritesheetLocation(20, 29)),
    VERTICAL_ROAD("Vertical Road", 0, 0, 0, 0, 2, 2, new SpritesheetLocation(22, 31)),
    LEFT_TOP_ROAD("Left Top Road", 0, 0, 0, 0, 2, 2, new SpritesheetLocation(32, 41)),
    RIGHT_TOP_ROAD("Right Top Road", 0, 0, 0, 0, 2, 2, new SpritesheetLocation(34, 43)),
    LEFT_BOTTOM_ROAD("Left Bottom Road", 0, 0, 0, 0, 2, 2, new SpritesheetLocation(36, 45)),
    RIGHT_BOTTOM_ROAD("Right Bottom Road", 0, 0, 0, 0, 2, 2, new SpritesheetLocation(38, 47)),
    T_TOP_ROAD("T Top Road", 0, 0, 0, 0, 2, 2, new SpritesheetLocation(48, 57)),
    T_BOTTOM_ROAD("T Bottom Road", 0, 0, 0, 0, 2, 2, new SpritesheetLocation(50, 59)),
    T_LEFT_ROAD("T Left Road", 0, 0, 0, 0, 2, 2, new SpritesheetLocation(52, 61)),
    T_RIGHT_ROAD("T Right Road", 0, 0, 0, 0, 2, 2, new SpritesheetLocation(54, 63));

    private final String name;
    private final int satisfactionModifier;
    private final int xpWhenFinished;
    private final int price;
    private final int unlockedAtLevel;
    private final int width;
    private final int height;
    private final SpritesheetLocation spritesheetLocation;
}
