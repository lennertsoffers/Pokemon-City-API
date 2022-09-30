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

@Component
@RequiredArgsConstructor
public class BuildableMapper {
    private final UserService userService;

    public Buildable toBuildable(BuildableCreationDto buildableCreationDto) {
        BuildableTypeEnum buildableType = BuildableTypeEnum.valueOf(buildableCreationDto.buildableType());
        Location location = new Location(buildableCreationDto.x(), buildableCreationDto.y());
        City city = userService.getAuthUser().getCity();

        switch (buildableType) {
            case HOUSE -> {
                HouseType houseType = HouseType.valueOf(buildableCreationDto.name());
                House house = new House(houseType, location);
                house.setLastCollected(LocalDateTime.now());
                house.setCity(city);
                return house;
            }

            case COMPANY -> {
                CompanyType companyType = CompanyType.valueOf(buildableCreationDto.name());
                Company company = new Company(location, companyType);
                company.setLastCollected(LocalDateTime.now());
                company.setCity(city);
                return company;
            }

            case DECORATION -> {
                DecorationType decorationType = DecorationType.valueOf(buildableCreationDto.name());
                Decoration decoration = new Decoration(location, decorationType);
                decoration.setCity(city);
                return decoration;
            }
        }

        return null;
    }

    public BuildableDto toBuildableDto(Buildable buildable) {
        return switch (buildable.getBuildableTypeEnum()) {
            case HOUSE -> toHouseDto((House) buildable);
            case COMPANY -> toCompanyDto((Company) buildable);
            case DECORATION -> toDecorationDto((Decoration) buildable);
        };
    }

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
                house.getLastCollected(),
                house.getHouseType(),
                house.getNumberOfCitizens(),
                house.getRentPerMinute(),
                house.getMaxRent()
        );
    }

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
                company.getLastCollected(),
                company.getCompanyType(),
                company.getProfitPerMinute()
        );
    }

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
                decoration.getDecorationType()
        );
    }
}
