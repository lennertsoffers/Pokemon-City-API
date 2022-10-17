package com.lennertsoffers.pokemon_city_api.model.dto;

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
