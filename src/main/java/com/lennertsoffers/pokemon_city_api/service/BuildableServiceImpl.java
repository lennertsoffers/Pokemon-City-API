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

        // Get all the buildables from the user that are not roads
        // For each company, update the citizens
        // Map the buildable to their corresponding buildableDto
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
    public List<BuildablePlacementDto> getBuildablesFromUser(Long userId) {
        return this.buildableRepository
                .findAllByCityUserId(userId)
                .stream()
                .map(buildableMapper::toBuildablePlacementDto)
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

        // Update the citizens if the buildable is a company
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
        // Create an empty buildable
        Buildable buildable = buildableMapper.createBuildable(buildableBuildDto);

        // Add the corresponding xp to the user
        // Remove the corresponding money from the user
        User user = buildable.getCity().getUser();
        user.addXp(buildable.getXpWhenFinished());
        user.removeMoney(buildable.getPrice());

        // If the buildable is a house, spawn the correct amount of citizens
        if (buildable instanceof House house) {
            for (int i = 0; i < house.getNumberOfCitizens(); i++) {
                citizenService.spawnCitizen(user.getCity());
            }
        }

        // Update the users statistics
        user.getStatistics().updateBuildingsBuilt(1);
        user.getStatistics().updateMoneySpent(buildable.getPrice());

        // Save the buildable in the database
        return this.buildableRepository.save(buildable);
    }

    @Override
    public Buildable move(BuildableMoveDto buildableMoveDto) {
        Optional<Buildable> optionalBuildable = buildableRepository.findById(buildableMoveDto.id());
        if (optionalBuildable.isEmpty()) return null;

        Buildable buildable = optionalBuildable.get();

        // Set the new location of the buildable to the location in the BuildableMoveDto
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

        // Add half of the price back to the user
        user.addMoney(buildable.getPrice() / 2);

        // If the buildable is a house, remove the citizenIds provided in the DTO
        if (buildable instanceof House house) {
            // Remove maximum the number of citizens of the house from the city
            int limit = house.getNumberOfCitizens();
            buildableDemolishDto.citizenIds().stream().distinct().limit(limit).forEach(citizenService::killCitizen);
        }
        // If the buildable is a company, un-assign all citizens
        else if (buildable instanceof Company company) {
            company.unEmployAll();
        }

        // Delete the buildable from the database
        buildableRepository.deleteById(buildableId);

        // If the buildable was a road, update the roads
        if (buildable instanceof Road) this.roadService.updateRoads();

        // Update the statistics of the user
        user.getStatistics().updateBuildingsDemolished(1);

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
