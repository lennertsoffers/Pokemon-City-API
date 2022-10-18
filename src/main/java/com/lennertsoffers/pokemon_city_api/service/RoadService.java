package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.Road;
import com.lennertsoffers.pokemon_city_api.model.dto.BuildRoadsDto;

import java.util.List;

public interface RoadService {
    List<Road> getRoads();
    List<Road> buildRoads(BuildRoadsDto buildRoadsDto);
    void updateRoads();
}
