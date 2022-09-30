package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.Statistics;
import com.lennertsoffers.pokemon_city_api.repository.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    private final StatisticsRepository statisticsRepository;

    @Override
    public Statistics saveStatistics(Statistics statistics) {
        return statisticsRepository.save(statistics);
    }
}
