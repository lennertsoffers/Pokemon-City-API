package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.SpritesheetLocation;
import com.lennertsoffers.pokemon_city_api.model.type.SpecialisationType;

/**
 * <p>DTO containing all the important data about the static data of a company</p>
 * @param name
 * @param satisfactionModifier
 * @param xpWhenFinished
 * @param price
 * @param unlockedAtLevel
 * @param width
 * @param height
 * @param profitPerMinute
 * @param specialisationType
 * @param maxAssignedCitizens
 * @param spritesheetLocation
 */
public record CompanyDataDto (
        String name,
        int satisfactionModifier,
        int xpWhenFinished,
        int price,
        int unlockedAtLevel,
        int width,
        int height,
        int profitPerMinute,
        SpecialisationType specialisationType,
        int maxAssignedCitizens,
        SpritesheetLocation spritesheetLocation
) {}
