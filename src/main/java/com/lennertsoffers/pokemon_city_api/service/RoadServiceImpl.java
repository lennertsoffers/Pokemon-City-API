package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.Road;
import com.lennertsoffers.pokemon_city_api.model.User;
import com.lennertsoffers.pokemon_city_api.model.dto.BuildRoadsDto;
import com.lennertsoffers.pokemon_city_api.model.type.RoadType;
import com.lennertsoffers.pokemon_city_api.repository.RoadRepository;
import com.lennertsoffers.pokemon_city_api.util.RoadUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// TODO - Fix buildable mapper
// TODO - Validation on move (you cannot move roads)

@Service
@RequiredArgsConstructor
public class RoadServiceImpl implements RoadService{
    private final RoadRepository roadRepository;
    private final UserService userService;

    @Override
    public List<Road> getRoads() {
        return this.roadRepository.getAllFromUser(userService.getUserData().id());
    }

    @Override
    public List<Road> buildRoads(BuildRoadsDto buildRoadsDto) {
        User user = this.userService.getAuthUser();

        List<Road> newRoads = new ArrayList<>();
        buildRoadsDto.locations().forEach(location -> {
            Road road = new Road(RoadType.SINGLE_ROAD, location);
            road.setCity(user.getCity());

            newRoads.add(road);
        });

        List<Road> roadMap = this.roadRepository.getAllFromUser(user.getId());
        roadMap.addAll(newRoads);

        roadMap.forEach(road -> road.setRoadType(RoadUtils.getRoadType(road.getLocation(), roadMap)));

        return this.roadRepository.saveAll(roadMap);
    }

    @Override
    public void updateRoads() {
        List<Road> roadMap = this.roadRepository.getAllFromUser(this.userService.getUserData().id());
        roadMap.forEach(road -> road.setRoadType(RoadUtils.getRoadType(road.getLocation(), roadMap)));
        this.roadRepository.saveAll(roadMap);
    }
}
