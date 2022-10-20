package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.validation.CityNameConstraint;

/**
 * <p>DTO containing the data to change the name of a city</p>
 * @param name The new name for the city
 */
public record CityChangeNameDto (
        @CityNameConstraint String name
) {}
