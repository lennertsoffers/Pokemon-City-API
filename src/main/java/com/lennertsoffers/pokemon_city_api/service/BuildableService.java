package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.Buildable;
import com.lennertsoffers.pokemon_city_api.model.Road;
import com.lennertsoffers.pokemon_city_api.model.dto.*;
import com.lennertsoffers.pokemon_city_api.model.type.BuildableType;

import java.util.List;

public interface BuildableService {
    List<BuildableDto> getBuildableDtos();
    BuildableDataDto getBuildableData();
    Buildable getById(Long id);
    BuildableDto getDtoById(Long id);
    Buildable build(BuildableBuildDto buildableBuildDto);
    List<Road> buildRoads(BuildRoadsDto buildRoadsDto);
    Buildable move(BuildableMoveDto buildableMoveDto);
    Boolean demolish(BuildableDemolishDto buildableDemolishDto);
    boolean belongsToUser(Long id);
    BuildableType getBuildableType(String buildableType, String name);
}
