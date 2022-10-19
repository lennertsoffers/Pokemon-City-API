package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.Location;
import com.lennertsoffers.pokemon_city_api.validation.BuildRoadsConstraint;

@BuildRoadsConstraint
public record BuildRoadDto(Location location) {}
