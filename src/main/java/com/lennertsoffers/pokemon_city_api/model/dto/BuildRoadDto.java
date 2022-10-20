package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.Location;
import com.lennertsoffers.pokemon_city_api.validation.BuildRoadsConstraint;

/**
 * <p>DTO containing the data needed to build a new road</p>
 * @param location The location where the road has to be built
 */
@BuildRoadsConstraint
public record BuildRoadDto(Location location) {}
