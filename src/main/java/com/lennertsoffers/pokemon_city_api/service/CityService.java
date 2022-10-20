package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.City;
import com.lennertsoffers.pokemon_city_api.model.dto.CityChangeNameDto;

public interface CityService {
    /**
     * Saves the city to the database
     * @param city The city to be persisted
     * @return The persisted city
     */
    City save(City city);

    /**
     * Changes the name of the city to the name in the DTO
     * @param cityChangeNameDto The CityChangeNameDto including the new name for the city
     * @return The new name of the city
     */
    String changeCityName(CityChangeNameDto cityChangeNameDto);
}
