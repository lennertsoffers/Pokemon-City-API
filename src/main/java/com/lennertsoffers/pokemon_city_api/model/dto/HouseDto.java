package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.Location;
import com.lennertsoffers.pokemon_city_api.model.SpritesheetLocation;
import com.lennertsoffers.pokemon_city_api.model.type.BuildableTypeEnum;
import com.lennertsoffers.pokemon_city_api.model.type.HouseType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class HouseDto extends IncomeBuildingDto {
    private final HouseType houseType;
    private final int numberOfCitizens;
    private final int maxRent;
    private final int rentPerMinute;
    private final int incomePerMinute;

    public HouseDto(Long id, String name, int satisfactionModifier, int xpWhenFinished, int price, int unlockedAtLevel, int height, int width, Location location, BuildableTypeEnum buildableTypeEnum, LocalDateTime lastCollected, HouseType houseType, int numberOfCitizens, int rentPerMinute, int maxRent, SpritesheetLocation spritesheetLocation, int incomePerMinute) {
        super(id, name, satisfactionModifier, xpWhenFinished, price, unlockedAtLevel, height, width, location, buildableTypeEnum, lastCollected, spritesheetLocation);
        this.houseType = houseType;
        this.numberOfCitizens = numberOfCitizens;
        this.maxRent = maxRent;
        this.rentPerMinute = rentPerMinute;
        this.incomePerMinute = incomePerMinute;
    }
}
