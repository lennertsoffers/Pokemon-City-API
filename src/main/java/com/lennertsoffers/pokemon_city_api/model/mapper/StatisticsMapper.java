package com.lennertsoffers.pokemon_city_api.model.mapper;

import com.lennertsoffers.pokemon_city_api.model.Statistics;
import com.lennertsoffers.pokemon_city_api.model.dto.StatisticsDto;
import org.springframework.stereotype.Component;

/**
 * <p>Mapper to map Statistics to StatisticsDtos</p>
 */
@Component
public class StatisticsMapper {
    /**
     * <p>Maps a Statistics object to a StatisticsDto object</p>
     * @param statistics The Statistics that has to be mapped
     * @return The StatisticsDto object created from the Statistics
     * @see StatisticsDto
     */
    public StatisticsDto toStatisticsDto(Statistics statistics) {
        return new StatisticsDto(
                statistics.getTimePlayed(),
                statistics.getTotalValue(),
                statistics.getBuildingsBuilt(),
                statistics.getBuildingsDemolished(),
                statistics.getMoneySpent(),
                statistics.getMoneyCollected(),
                (int) Math.round(statistics.getIncomePerMinute()),
                statistics.getMaxedCitizens()
        );
    }
}
