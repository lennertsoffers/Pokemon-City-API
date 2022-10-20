package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.SpritesheetLocation;

/**
 * <p>DTO containing all the important data about the static data of a decoration</p>
 * @param name
 * @param satisfactionModifier
 * @param xpWhenFinished
 * @param price
 * @param unlockedAtLevel
 * @param width
 * @param height
 * @param spritesheetLocation
 */
public record DecorationDataDto (
        String name,
        int satisfactionModifier,
        int xpWhenFinished,
        int price,
        int unlockedAtLevel,
        int width,
        int height,
        SpritesheetLocation spritesheetLocation
) {}
