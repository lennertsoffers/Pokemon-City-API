package com.lennertsoffers.pokemon_city_api.repository;

import com.lennertsoffers.pokemon_city_api.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
}
