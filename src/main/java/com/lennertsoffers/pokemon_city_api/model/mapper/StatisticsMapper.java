package com.lennertsoffers.pokemon_city_api.model.mapper;

import com.lennertsoffers.pokemon_city_api.model.Statistics;
import com.lennertsoffers.pokemon_city_api.model.dto.StatisticsDto;
import org.springframework.stereotype.Component;

@Component
public class StatisticsMapper {
    public StatisticsDto toStatisticsDto(Statistics statistics) {
        return new StatisticsDto(
                statistics.getTimePlayed(),
                statistics.getTotalValue(),
                statistics.getBuildingsBuilt(),
                statistics.getBuildingsDemolished(),
                statistics.getMoneySpent(),
                statistics.getMoneyCollected(),
                statistics.getIncomePerMinute(),
                statistics.getMaxedCitizens()
        );
    }
}
