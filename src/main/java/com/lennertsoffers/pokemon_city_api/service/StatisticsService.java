package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.Statistics;

public interface StatisticsService {
    /**
     * Persists the statistics in the database
     * @param statistics The statistics to be persisted in the database
     */
    void saveStatistics(Statistics statistics);
}
