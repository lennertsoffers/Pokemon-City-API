package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.City;
import com.lennertsoffers.pokemon_city_api.model.dto.CityChangeNameDto;
import org.springframework.stereotype.Service;

public interface CityService {
    City save(City city);
    String changeCityName(CityChangeNameDto cityChangeNameDto);
}
