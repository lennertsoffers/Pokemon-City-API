package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.Buildable;
import com.lennertsoffers.pokemon_city_api.model.dto.BuildableBuildDto;
import com.lennertsoffers.pokemon_city_api.model.dto.BuildableDto;
import com.lennertsoffers.pokemon_city_api.model.dto.BuildableMoveDto;
import com.lennertsoffers.pokemon_city_api.model.type.BuildableType;

import java.util.List;

public interface BuildableService {
    List<BuildableDto> getBuildableDtos();
    Buildable getById(Long id);
    Buildable build(BuildableBuildDto buildableBuildDto);
    Buildable move(Long id, BuildableMoveDto buildableMoveDto);
    Boolean demolish(Long id);
    boolean belongsToUser(Long id);
    BuildableType getBuildableType(String buildableType, String name);
}
