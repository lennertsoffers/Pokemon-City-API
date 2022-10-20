package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.Decoration;
import com.lennertsoffers.pokemon_city_api.model.Location;
import com.lennertsoffers.pokemon_city_api.model.SpritesheetLocation;
import com.lennertsoffers.pokemon_city_api.model.type.BuildableTypeEnum;
import com.lennertsoffers.pokemon_city_api.model.type.DecorationType;
import lombok.Getter;

/**
 * <p>DTO containing all important info about a decoration</p>
 * @see Decoration
 */
@Getter
public class DecorationDto extends BuildableDto {
    private final DecorationType decorationType;

    public DecorationDto(Long id, String name, int satisfactionModifier, int xpWhenFinished, int price, int unlockedAtLevel, int height, int width, Location location, BuildableTypeEnum buildableTypeEnum, DecorationType decorationType, SpritesheetLocation spritesheetLocation) {
        super(id, name, satisfactionModifier, xpWhenFinished, price, unlockedAtLevel, height, width, location, buildableTypeEnum, spritesheetLocation);
        this.decorationType = decorationType;
    }
}
