package com.lennertsoffers.pokemon_city_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <b>Represents the location a sprite has on the spritesheet</b>
 * <p>Contains the topLeft index and the bottomRight. The width and height can be calculated by these two field</p>
 */
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
