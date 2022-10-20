package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.SpritesheetLocation;

/**
 * <p>DTO containing all important info about the static data of a company</p>
 * @param name
 * @param satisfactionModifier
 * @param xpWhenFinished
 * @param price
 * @param unlockedAtLevel
 * @param width
 * @param height
 * @param numberOfCitizens
 * @param maxRent
 * @param rentPerMinute
 * @param spritesheetLocation
 */
public record HouseDataDto(
    String name,
    int satisfactionModifier,
    int xpWhenFinished,
    int price,
    int unlockedAtLevel,
    int width,
    int height,
    int numberOfCitizens,
    int maxRent,
    int rentPerMinute,
    SpritesheetLocation spritesheetLocation
) {}
