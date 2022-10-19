package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.dto.BuildRoadDto;
import com.lennertsoffers.pokemon_city_api.model.dto.RoadDto;

import java.util.List;

public interface RoadService {
    List<RoadDto> getRoads();
    List<RoadDto> buildRoad(BuildRoadDto buildRoadDto);
    void updateRoads();
}
