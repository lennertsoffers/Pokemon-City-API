package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.User;

/**
 * <p>DTO containing all the important information concerning the data of a User</p>
 * @param id
 * @param username
 * @param xp
 * @param level
 * @param money
 * @param citizens
 * @param employedCitizens
 * @param satisfaction
 * @param cityName
 * @param statistics
 * @see User
 */
public record UserDataDto(
        Long id,
        String username,
        int xp,
        int level,
        int money,
        int citizens,
        int employedCitizens,
        double satisfaction,
        String cityName,
        StatisticsDto statistics
) {}
