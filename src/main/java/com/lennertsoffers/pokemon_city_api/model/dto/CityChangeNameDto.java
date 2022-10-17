package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.validation.CityNameConstraint;

public record CityChangeNameDto (
        @CityNameConstraint String name
) {}
