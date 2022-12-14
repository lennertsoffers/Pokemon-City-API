package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.Road;
import com.lennertsoffers.pokemon_city_api.model.User;
import com.lennertsoffers.pokemon_city_api.model.dto.BuildRoadDto;
import com.lennertsoffers.pokemon_city_api.model.dto.RoadDto;
import com.lennertsoffers.pokemon_city_api.model.mapper.BuildableMapper;
import com.lennertsoffers.pokemon_city_api.model.type.RoadType;
import com.lennertsoffers.pokemon_city_api.repository.RoadRepository;
import com.lennertsoffers.pokemon_city_api.util.RoadUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoadServiceImpl implements RoadService{
    private final RoadRepository roadRepository;
    private final UserService userService;
    private final BuildableMapper buildableMapper;

    @Override
    public List<RoadDto> getRoads() {
        return this.roadRepository
                .getAllFromUser(userService.getUserData().id())
                .stream()
                .map(buildableMapper::toRoadDto)
                .toList();
    }

    @Override
    public List<RoadDto> buildRoad(BuildRoadDto buildRoadDto) {
        User user = this.userService.getAuthUser();

        // Create a new road and set the city
        Road newRoad = new Road(RoadType.SINGLE_ROAD, buildRoadDto.location());
        newRoad.setCity(user.getCity());
        // Get all roads from the user and add the new road
        List<Road> roadMap = this.roadRepository.getAllFromUser(user.getId());
        roadMap.add(newRoad);

        // Update the roadTypes of all the roads
        roadMap.forEach(road -> road.setRoadType(RoadUtils.getRoadType(road.getLocation(), roadMap)));
        // Save the roads to the database
        List<Road> savedRoads = this.roadRepository.saveAll(roadMap);

        // Return the updated roads in DTO form
        return savedRoads.stream().map(buildableMapper::toRoadDto).toList();
    }

    @Override
    public void updateRoads() {
        List<Road> roadMap = this.roadRepository.getAllFromUser(this.userService.getUserData().id());
        roadMap.forEach(road -> road.setRoadType(RoadUtils.getRoadType(road.getLocation(), roadMap)));
        this.roadRepository.saveAll(roadMap);
    }
}
