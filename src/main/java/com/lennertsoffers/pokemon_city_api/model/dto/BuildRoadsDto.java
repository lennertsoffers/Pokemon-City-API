package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.Location;
import com.lennertsoffers.pokemon_city_api.validation.BuildRoadsConstraint;

import java.util.List;

@BuildRoadsConstraint
public record BuildRoadsDto(List<Location> locations) {}
