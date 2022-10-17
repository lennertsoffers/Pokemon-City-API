package com.lennertsoffers.pokemon_city_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SpritesheetLocation {
    private final int topLeft;
    private final int bottomRight;
    private final boolean separated;

    public SpritesheetLocation(int topLeftPosition, int bottomRightPosition) {
        this.topLeft = topLeftPosition;
        this.bottomRight = bottomRightPosition;
        this.separated = false;
    }
}
