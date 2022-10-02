package com.lennertsoffers.pokemon_city_api.model.dto;

public record StatisticsDto(
        int timePlayed,
        int totalValue,
        int buildingsBuilt,
        int moneySpent,
        int moneyCollected,
        int profit,
        int incomePerMinute,
        int maxedCitizens
) {}
