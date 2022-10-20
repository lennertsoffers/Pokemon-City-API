package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.Location;
import com.lennertsoffers.pokemon_city_api.model.Road;
import com.lennertsoffers.pokemon_city_api.model.SpritesheetLocation;
import com.lennertsoffers.pokemon_city_api.model.type.RoadType;

/**
 * <p>DTO containing all important info about a road</p>
 * @param id
 * @param location
 * @param roadType
 * @param spritesheetLocation
 * @see Road
 */
public record RoadDto(
        Long id,
        Location location,
        RoadType roadType,
        SpritesheetLocation spritesheetLocation
) {}
