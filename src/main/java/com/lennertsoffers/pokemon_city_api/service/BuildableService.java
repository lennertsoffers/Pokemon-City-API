package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.Buildable;
import com.lennertsoffers.pokemon_city_api.model.dto.BuildableCreationDto;
import com.lennertsoffers.pokemon_city_api.model.dto.BuildableDto;

import java.util.List;

public interface BuildableService {
    List<BuildableDto> getBuildableDtos();
    Buildable build(BuildableCreationDto buildableCreationDto);
}
