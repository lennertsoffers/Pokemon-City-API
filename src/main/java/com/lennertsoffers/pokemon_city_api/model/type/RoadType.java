package com.lennertsoffers.pokemon_city_api.model.type;

import com.lennertsoffers.pokemon_city_api.model.SpritesheetLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoadType implements BuildableType {
    SINGLE_ROAD("Single Road", 0, 0, 0, 0, 1, 1, new SpritesheetLocation(1, 1)),
    TO_TOP_ROAD("To Top Road", 0, 0, 0, 0, 1, 1, new SpritesheetLocation(1, 1)),
    TO_BOTTOM_ROAD("To Bottom Road", 0, 0, 0, 0, 1, 1, new SpritesheetLocation(1, 1)),
    TO_LEFT_ROAD("To Left Road", 0, 0, 0, 0, 1, 1, new SpritesheetLocation(1, 1)),
    TO_RIGHT_ROAD("To Right Road", 0, 0, 0, 0, 1, 1, new SpritesheetLocation(1, 1)),
    HORIZONTAL_ROAD("Horizontal Road", 0, 0, 0, 0, 1, 1, new SpritesheetLocation(1, 1)),
    VERTICAL_ROAD("Vertical Road", 0, 0, 0, 0, 1, 1, new SpritesheetLocation(1, 1)),
    LEFT_TOP_ROAD("Left Top Road", 0, 0, 0, 0, 1, 1, new SpritesheetLocation(1, 1)),
    RIGHT_TOP_ROAD("Right Top Road", 0, 0, 0, 0, 1, 1, new SpritesheetLocation(1, 1)),
    LEFT_BOTTOM_ROAD("Left Bottom Road", 0, 0, 0, 0, 1, 1, new SpritesheetLocation(1, 1)),
    RIGHT_BOTTOM_ROAD("Right Bottom Road", 0, 0, 0, 0, 1, 1, new SpritesheetLocation(1, 1)),
    T_TOP_ROAD("T Top Road", 0, 0, 0, 0, 1, 1, new SpritesheetLocation(1, 1)),
    T_BOTTOM_ROAD("T Bottom Road", 0, 0, 0, 0, 1, 1, new SpritesheetLocation(1, 1)),
    T_LEFT_ROAD("T Left Road", 0, 0, 0, 0, 1, 1, new SpritesheetLocation(1, 1)),
    T_RIGHT_ROAD("T Right Road", 0, 0, 0, 0, 1, 1, new SpritesheetLocation(1, 1)),
    CROSSROAD("Crossroad", 0, 0, 0, 0, 1, 1, new SpritesheetLocation(1, 1));

    private final String name;
    private final int satisfactionModifier;
    private final int xpWhenFinished;
    private final int price;
    private final int unlockedAtLevel;
    private final int width;
    private final int height;
    private final SpritesheetLocation spritesheetLocation;
}
