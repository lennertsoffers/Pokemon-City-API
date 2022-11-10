package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.Location;
import com.lennertsoffers.pokemon_city_api.model.SpritesheetLocation;
import com.lennertsoffers.pokemon_city_api.model.type.BuildableTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BuildablePlacementDto {
    private final Long id;
    private final Location location;
    private final BuildableTypeEnum buildableTypeEnum;
    private final SpritesheetLocation spritesheetLocation;
}