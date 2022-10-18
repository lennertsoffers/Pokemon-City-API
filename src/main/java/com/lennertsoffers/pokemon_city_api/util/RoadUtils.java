package com.lennertsoffers.pokemon_city_api.util;

import com.lennertsoffers.pokemon_city_api.model.Location;
import com.lennertsoffers.pokemon_city_api.model.Road;
import com.lennertsoffers.pokemon_city_api.model.type.RoadType;

import java.util.List;

public class RoadUtils {
    public static RoadType getRoadType(Location location, List<Road> roadMap) {
        boolean topAdjacent = false;
        boolean bottomAdjacent = false;
        boolean leftAdjacent = false;
        boolean rightAdjacent = false;

        if (roadMap.stream().anyMatch(road -> road.getLocation().equals(location.copy().add(0, -1)))) topAdjacent = true;
        if (roadMap.stream().anyMatch(road -> road.getLocation().equals(location.copy().add(0, 1)))) bottomAdjacent = true;
        if (roadMap.stream().anyMatch(road -> road.getLocation().equals(location.copy().add(-1, 0)))) leftAdjacent = true;
        if (roadMap.stream().anyMatch(road -> road.getLocation().equals(location.copy().add(1, 0)))) rightAdjacent = true;

        if (!topAdjacent && !bottomAdjacent && !leftAdjacent && !rightAdjacent) return RoadType.SINGLE_ROAD;
        if (topAdjacent && bottomAdjacent && leftAdjacent && rightAdjacent) return RoadType.CROSSROAD;

        if (topAdjacent && !bottomAdjacent && !leftAdjacent && !rightAdjacent) return RoadType.TO_TOP_ROAD;
        if (!topAdjacent && bottomAdjacent && !leftAdjacent && !rightAdjacent) return RoadType.TO_BOTTOM_ROAD;
        if (!topAdjacent && !bottomAdjacent && leftAdjacent && !rightAdjacent) return RoadType.TO_LEFT_ROAD;
        if (!topAdjacent && !bottomAdjacent && !leftAdjacent) return RoadType.TO_RIGHT_ROAD;

        if (topAdjacent && bottomAdjacent && !leftAdjacent && !rightAdjacent) return RoadType.VERTICAL_ROAD;
        if (!topAdjacent && !bottomAdjacent) return RoadType.HORIZONTAL_ROAD;

        if (topAdjacent && !bottomAdjacent && leftAdjacent && !rightAdjacent) return RoadType.LEFT_TOP_ROAD;
        if (topAdjacent && !bottomAdjacent && !leftAdjacent) return RoadType.RIGHT_TOP_ROAD;
        if (!topAdjacent && leftAdjacent && !rightAdjacent) return RoadType.LEFT_BOTTOM_ROAD;
        if (!topAdjacent && !leftAdjacent) return RoadType.RIGHT_BOTTOM_ROAD;

        if (topAdjacent && !bottomAdjacent) return RoadType.T_TOP_ROAD;
        if (!topAdjacent) return RoadType.T_BOTTOM_ROAD;
        if (leftAdjacent) return RoadType.T_LEFT_ROAD;
        return RoadType.T_RIGHT_ROAD;
    }
}
