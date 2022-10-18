package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.Location;
import com.lennertsoffers.pokemon_city_api.model.SpritesheetLocation;
import com.lennertsoffers.pokemon_city_api.model.type.RoadType;

public record RoadDto(
        Long id,
        Location location,
        RoadType roadType,
        SpritesheetLocation spritesheetLocation
) {}
