package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.Buildable;
import com.lennertsoffers.pokemon_city_api.model.dto.BuildableCreationDto;
import com.lennertsoffers.pokemon_city_api.model.dto.BuildableDto;
import com.lennertsoffers.pokemon_city_api.model.mapper.BuildableMapper;
import com.lennertsoffers.pokemon_city_api.repository.BuildableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildableServiceImpl implements BuildableService {
    private final BuildableRepository buildableRepository;
    private final UserService userService;
    private final BuildableMapper buildableMapper;

    @Override
    public List<BuildableDto> getBuildableDtos() {
        return userService
                .getAuthUser()
                .getCity()
                .getBuildables()
                .stream()
                .map(buildableMapper::toBuildableDto)
                .toList();
    }

    @Override
    public Buildable build(BuildableCreationDto buildableCreationDto) {
        Buildable buildable = buildableMapper.toBuildable(buildableCreationDto);

        return this.buildableRepository.save(buildable);
    }
}
