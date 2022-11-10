package com.lennertsoffers.pokemon_city_api.model.mapper;

import com.lennertsoffers.pokemon_city_api.model.*;
import com.lennertsoffers.pokemon_city_api.model.dto.*;
import com.lennertsoffers.pokemon_city_api.model.type.BuildableTypeEnum;
import com.lennertsoffers.pokemon_city_api.model.type.CompanyType;
import com.lennertsoffers.pokemon_city_api.model.type.DecorationType;
import com.lennertsoffers.pokemon_city_api.model.type.HouseType;
import com.lennertsoffers.pokemon_city_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p>Mapper to map Buildables to BuildableDtos on sub- and supertype</p>
 */
@Component
@RequiredArgsConstructor
public class BuildableMapper {
    private final UserService userService;

    /**
     * <p>Converts a BuildableBuildDto to a buildable</p>
     * <p>Sets default values such as the city of the buildable and if it is an IncomeBuilding, the lastCollected time (sys-time)</p>
     * @param buildableBuildDto The BuildableBuildDto having all the data to create a new buildable and match its type
     * @return The created buildable instance
     */
    public Buildable createBuildable(BuildableBuildDto buildableBuildDto) {
        // Gets the BuildableType from the DTO
        BuildableTypeEnum buildableType = BuildableTypeEnum.valueOf(buildableBuildDto.buildableType());
        // Converts the coordinates in the DTO to a location object
        Location location = new Location(buildableBuildDto.x(), buildableBuildDto.y());
        // Get the city of the current user
        City city = userService.getAuthUser().getCity();

        switch (buildableType) {
            case HOUSE -> {
                HouseType houseType = HouseType.valueOf(buildableBuildDto.name());
                House house = new House(houseType, location);
                house.setLastCollected(LocalDateTime.now());
                house.setCity(city);
                return house;
            }

            case COMPANY -> {
                CompanyType companyType = CompanyType.valueOf(buildableBuildDto.name());
                Company company = new Company(location, companyType);
                company.setLastCollected(LocalDateTime.now());
                company.setCity(city);
                return company;
            }

            case DECORATION -> {
                DecorationType decorationType = DecorationType.valueOf(buildableBuildDto.name());
                Decoration decoration = new Decoration(location, decorationType);
                decoration.setCity(city);
                return decoration;
            }
        }

        return null;
    }

    /**
     * <p>Maps a Buildable to a BuildableDto</p>
     * <p>Checks which subtype of Buildable the Buildable has and then converts it to the right DTO</p>
     * @param buildable The Buildable that has to be mapped
     * @return The mapped DTO object as corresponding subtype of Buildable
     */
    public BuildableDto toBuildableDto(Buildable buildable) {
        return switch (buildable.getBuildableTypeEnum()) {
            case HOUSE -> toHouseDto((House) buildable);
            case COMPANY -> toCompanyDto((Company) buildable);
            case DECORATION -> toDecorationDto((Decoration) buildable);
            default -> buildableDto(buildable);
        };
    }

    /**
     * <p>Maps a House object to a HouseDto object</p>
     * @param house The house that has to be mapped
     * @return The HouseDto object created from the House
     * @see HouseDto
     */
    public HouseDto toHouseDto(House house) {
        return new HouseDto(
                house.getId(),
                house.getName(),
                house.getSatisfactionModifier(),
                house.getXpWhenFinished(),
                house.getPrice(),
                house.getUnlockedAtLevel(),
                house.getHeight(),
                house.getWidth(),
                house.getLocation(),
                house.getBuildableTypeEnum(),
                house.getLastCollected(),
                house.getHouseType(),
                house.getNumberOfCitizens(),
                house.getRentPerMinute(),
                house.getMaxRent(),
                house.getSpritesheetLocation(),
                (int) Math.round(house.getIncomePerMinute())
        );
    }

    /**
     * <p>Maps a Company object to a CompanyDto object</p>
     * @param company The Company that has to be mapped
     * @return The CompanyDto object created from the Company
     * @see CompanyDto
     */
    public CompanyDto toCompanyDto(Company company) {
        return new CompanyDto(
                company.getId(),
                company.getName(),
                company.getSatisfactionModifier(),
                company.getXpWhenFinished(),
                company.getPrice(),
                company.getUnlockedAtLevel(),
                company.getHeight(),
                company.getWidth(),
                company.getLocation(),
                company.getBuildableTypeEnum(),
                company.getLastCollected(),
                company.getCompanyType(),
                company.getProfitPerMinute(),
                company.getEmployeeMultiplier(),
                company.getMaxAssignedCitizens(),
                company.getSpecialisationType(),
                company.getSpritesheetLocation(),
                (int) Math.round(company.getIncomePerMinute()),
                company.getAssignedCitizens().size()
        );
    }

    /**
     * <p>Maps a Decoration object to a DecorationDto object</p>
     * @param decoration The Decoration that has to be mapped
     * @return The DecorationDto object created from the Decoration
     * @see DecorationDto
     */
    public DecorationDto toDecorationDto(Decoration decoration) {
        return new DecorationDto(
                decoration.getId(),
                decoration.getName(),
                decoration.getSatisfactionModifier(),
                decoration.getXpWhenFinished(),
                decoration.getPrice(),
                decoration.getUnlockedAtLevel(),
                decoration.getHeight(),
                decoration.getWidth(),
                decoration.getLocation(),
                decoration.getBuildableTypeEnum(),
                decoration.getDecorationType(),
                decoration.getSpritesheetLocation()
        );
    }

    /**
     * <p>Maps a Road object to a RoadDto object</p>
     * @param road The Road that has to be mapped
     * @return The RoadDto object created from the Road
     * @see RoadDto
     */
    public RoadDto toRoadDto(Road road) {
        return new RoadDto(
                road.getId(),
                road.getLocation(),
                road.getRoadType(),
                road.getSpritesheetLocation()
        );
    }

    /**
     * Maps a buildable to a BuildablePlacementDto
     * This only includes the needed info to show the buildable
     * @param buildable The buildable to be converted
     * @return The mapped BuildablePlacementDto
     */
    public BuildablePlacementDto toBuildablePlacementDto(Buildable buildable) {
        if (buildable instanceof Company company) {
            return new CompanyPlacementDto(
                    buildable.getId(),
                    buildable.getLocation(),
                    buildable.getBuildableTypeEnum(),
                    buildable.getSpritesheetLocation(),
                    company.getSpecialisationType()
            );
        }

        return new BuildablePlacementDto(
                buildable.getId(),
                buildable.getLocation(),
                buildable.getBuildableTypeEnum(),
                buildable.getSpritesheetLocation()
        );
    }

    /**
     * <p>Maps a Buildable object to a BuildableDto object</p>
     * <p>Used to map to a generic buildable instead of a specific subtype</p>
     * @param buildable The Buildable that has to be mapped
     * @return The BuildableDto object created from the Buildable
     * @see BuildableDto
     */
    private BuildableDto buildableDto(Buildable buildable) {
        return new BuildableDto(
                buildable.getId(),
                buildable.getName(),
                buildable.getSatisfactionModifier(),
                buildable.getXpWhenFinished(),
                buildable.getPrice(),
                buildable.getUnlockedAtLevel(),
                buildable.getHeight(),
                buildable.getWidth(),
                buildable.getLocation(),
                buildable.getBuildableTypeEnum(),
                buildable.getSpritesheetLocation()
        );
    }
}
