package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.Buildable;
import com.lennertsoffers.pokemon_city_api.model.Location;
import com.lennertsoffers.pokemon_city_api.model.SpritesheetLocation;
import com.lennertsoffers.pokemon_city_api.model.type.BuildableTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>DTO containing all important data about a Buildable</p>
 * @see Buildable
 */
@AllArgsConstructor
@Getter
public class BuildableDto {
    private final Long id;
    private final String name;
    private final int satisfactionModifier;
    private final int xpWhenFinished;
    private final int price;
    private final int unlockedAtLevel;
    private final int height;
    private final int width;
    private final Location location;
    private final BuildableTypeEnum buildableTypeEnum;
    private final SpritesheetLocation spritesheetLocation;
}
