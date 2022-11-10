package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.Statistics;

/**
 * <p>DTO containing all important info about a Statistics object</p>
 * @param timePlayed
 * @param totalValue
 * @param buildingsBuilt
 * @param buildingsDemolished
 * @param moneySpent
 * @param moneyCollected
 * @param incomePerMinute
 * @param maxedCitizens
 * @see Statistics
 */
public record StatisticsDto(
        int timePlayed,
        int totalValue,
        int buildingsBuilt,
        int buildingsDemolished,
        int moneySpent,
        int moneyCollected,
        int incomePerMinute,
        int maxedCitizens,
        long score
) {}
