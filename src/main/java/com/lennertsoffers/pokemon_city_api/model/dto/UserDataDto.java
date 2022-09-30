package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.Statistics;

public record UserDataDto(
        Long id,
        String username,
        int xp,
        int level,
        int money,
        Statistics statistics
) {}
