package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.*;
import com.lennertsoffers.pokemon_city_api.model.dto.*;
import com.lennertsoffers.pokemon_city_api.model.mapper.BuildableMapper;
import com.lennertsoffers.pokemon_city_api.model.mapper.TypeDataMapper;
import com.lennertsoffers.pokemon_city_api.model.type.*;
import com.lennertsoffers.pokemon_city_api.repository.BuildableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BuildableServiceImpl implements BuildableService {
    private final BuildableRepository buildableRepository;
    private final UserService userService;
    private final CitizenService citizenService;
    private final RoadService roadService;
    private final BuildableMapper buildableMapper;
    private final TypeDataMapper typeDataMapper;

    @Override
    public List<BuildableDto> getBuildableDtos() {
        Long userId = userService
                .getAuthUser()
                .getId();

        return buildableRepository
                .getAllNotRoadFromUser(userId)
                .stream()
                .peek(buildable -> {
                    if (buildable instanceof Company company) {
                        company.getAssignedCitizens().forEach(Citizen::update);
                    }
                })
                .map(buildableMapper::toBuildableDto)
                .toList();
    }

    @Override
    public BuildableDataDto getBuildableData() {
        return new BuildableDataDto(
                Arrays.stream(HouseType.values()).map(typeDataMapper::toHouseDataDto).toList(),
                Arrays.stream(CompanyType.values()).map(typeDataMapper::toCompanyDataDto).sorted(Comparator.comparingInt(CompanyDataDto::unlockedAtLevel)).toList(),
                Arrays.stream(DecorationType.values()).map(typeDataMapper::toDecorationDataDto).toList()
        );
    }

    @Override
    public Buildable getById(Long id) {
        Optional<Buildable> optionalBuildable = buildableRepository.findById(id);

        if (optionalBuildable.isEmpty()) return null;

        Buildable buildable = optionalBuildable.get();
        if (buildable instanceof Company company) {
            company.getAssignedCitizens().forEach(Citizen::update);
        }

        return buildable;
    }

    @Override
    public BuildableDto getDtoById(Long id) {
        Buildable buildable = this.getById(id);

        if (buildable == null) return null;

        return buildableMapper.toBuildableDto(buildable);
    }

    @Override
    public Buildable build(BuildableBuildDto buildableBuildDto) {
        Buildable buildable = buildableMapper.createBuildable(buildableBuildDto);

        User user = buildable.getCity().getUser();
        user.addXp(buildable.getXpWhenFinished());
        user.removeMoney(buildable.getPrice());

        if (buildable instanceof House house) {
            for (int i = 0; i < house.getNumberOfCitizens(); i++) {
                citizenService.spawnCitizen(user.getCity());
            }
        }

        user.getStatistics().updateBuildingsBuild(1);
        user.getStatistics().updateMoneySpent(buildable.getPrice());

        return this.buildableRepository.save(buildable);
    }

    @Override
    public Buildable move(BuildableMoveDto buildableMoveDto) {
        Optional<Buildable> optionalBuildable = buildableRepository.findById(buildableMoveDto.id());
        if (optionalBuildable.isEmpty()) return null;

        Buildable buildable = optionalBuildable.get();
        buildable.setLocation(new Location(buildableMoveDto.x(), buildableMoveDto.y()));

        return buildableRepository.save(buildable);
    }

    @Override
    public Boolean demolish(BuildableDemolishDto buildableDemolishDto) {
        Long buildableId = buildableDemolishDto.buildableId();

        Optional<Buildable> optionalBuildable = buildableRepository.findById(buildableId);

        if (optionalBuildable.isEmpty()) {
            return false;
        }

        Buildable buildable = optionalBuildable.get();

        User user = buildable.getCity().getUser();

        user.addMoney(buildable.getPrice() / 2);

        if (buildable instanceof House) {
            buildableDemolishDto.citizenIds().stream().distinct().forEach(citizenService::killCitizen);
        } else if (buildable instanceof Company company) {
            company.unEmployAll();
        }

        buildableRepository.deleteById(buildableId);

        user.getStatistics().updateBuildingsDemolished(1);
        if (buildable instanceof Road) this.roadService.updateRoads();

        return true;
    }

    @Override
    public boolean belongsToUser(Long id) {
        Optional<Buildable> optionalBuildable = buildableRepository.findById(id);

        if (optionalBuildable.isEmpty()) return false;

        Buildable buildable = optionalBuildable.get();
        return Objects.equals(buildable.getCity().getUser().getId(), userService.getAuthUser().getId());
    }

    @Override
    public BuildableType getBuildableType(String buildableType, String name) {
        return switch (BuildableTypeEnum.valueOf(buildableType)) {
            case HOUSE -> HouseType.valueOf(name);
            case COMPANY -> CompanyType.valueOf(name);
            case DECORATION -> DecorationType.valueOf(name);
            case ROAD -> RoadType.SINGLE_ROAD;
        };
    }
}
