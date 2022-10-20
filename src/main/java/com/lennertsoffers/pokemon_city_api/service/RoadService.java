package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.dto.BuildRoadDto;
import com.lennertsoffers.pokemon_city_api.model.dto.RoadDto;

import java.util.List;

public interface RoadService {
    /**
     * <p>Returns a list of all the roads of the authenticated user</p>
     * @return A list of the user's roads
     */
    List<RoadDto> getRoads();

    /**
     * <p>Builds a road using the data provided BuildRoadDto</p>
     * @param buildRoadDto The BuildRoadDto containing all the information build a new road
     * @return A list of all the roads that belong to the user
     */
    List<RoadDto> buildRoad(BuildRoadDto buildRoadDto);

    /**
     * Updates the orientation of all the roads of the current user
     */
    void updateRoads();
}
