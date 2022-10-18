package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.Road;
import com.lennertsoffers.pokemon_city_api.model.User;
import com.lennertsoffers.pokemon_city_api.model.dto.BuildRoadsDto;
import com.lennertsoffers.pokemon_city_api.model.dto.RoadDto;
import com.lennertsoffers.pokemon_city_api.model.mapper.BuildableMapper;
import com.lennertsoffers.pokemon_city_api.model.type.RoadType;
import com.lennertsoffers.pokemon_city_api.repository.RoadRepository;
import com.lennertsoffers.pokemon_city_api.util.RoadUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<RoadDto> buildRoads(BuildRoadsDto buildRoadsDto) {
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

        List<Road> savedRoads = this.roadRepository.saveAll(roadMap);

        return savedRoads.stream().map(buildableMapper::toRoadDto).toList();
    }

    @Override
    public void updateRoads() {
        List<Road> roadMap = this.roadRepository.getAllFromUser(this.userService.getUserData().id());
        roadMap.forEach(road -> road.setRoadType(RoadUtils.getRoadType(road.getLocation(), roadMap)));
        this.roadRepository.saveAll(roadMap);
    }
}
