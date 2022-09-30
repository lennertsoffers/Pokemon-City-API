package com.lennertsoffers.pokemon_city_api.repository;

import com.lennertsoffers.pokemon_city_api.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
