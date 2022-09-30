package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.Buildable;
import com.lennertsoffers.pokemon_city_api.model.Location;
import com.lennertsoffers.pokemon_city_api.model.dto.BuildableBuildDto;
import com.lennertsoffers.pokemon_city_api.model.dto.BuildableDto;
import com.lennertsoffers.pokemon_city_api.model.dto.BuildableMoveDto;
import com.lennertsoffers.pokemon_city_api.model.mapper.BuildableMapper;
import com.lennertsoffers.pokemon_city_api.repository.BuildableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public Buildable getById(Long id) {
        Optional<Buildable> optionalBuildable = buildableRepository.findById(id);

        if (optionalBuildable.isEmpty()) return null;
        return optionalBuildable.get();
    }

    @Override
    public Buildable build(BuildableBuildDto buildableBuildDto) {
        Buildable buildable = buildableMapper.toBuildable(buildableBuildDto);

        return this.buildableRepository.save(buildable);
    }

    @Override
    public Buildable move(Long id, BuildableMoveDto buildableMoveDto) {
        Optional<Buildable> optionalBuildable = buildableRepository.findById(id);
        if (optionalBuildable.isEmpty()) return null;

        Buildable buildable = optionalBuildable.get();
        buildable.setLocation(new Location(buildableMoveDto.x(), buildableMoveDto.y()));

        return buildableRepository.save(buildable);
    }

    @Override
    public Boolean demolish(Long id) {
        if (buildableRepository.existsById(id)) {
            buildableRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public boolean belongsToUser(Long id) {
        Optional<Buildable> optionalBuildable = buildableRepository.findById(id);

        if (optionalBuildable.isEmpty()) return false;

        Buildable buildable = optionalBuildable.get();
        return Objects.equals(buildable.getCity().getUser().getId(), userService.getAuthUser().getId());
    }
}
