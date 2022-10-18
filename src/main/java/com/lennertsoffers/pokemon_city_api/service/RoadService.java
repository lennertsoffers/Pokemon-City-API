package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.dto.BuildRoadsDto;
import com.lennertsoffers.pokemon_city_api.model.dto.RoadDto;

import java.util.List;

public interface RoadService {
    List<RoadDto> getRoads();
    List<RoadDto> buildRoads(BuildRoadsDto buildRoadsDto);
    void updateRoads();
}
