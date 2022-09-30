package com.lennertsoffers.pokemon_city_api.repository;

import com.lennertsoffers.pokemon_city_api.model.Buildable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildableRepository extends JpaRepository<Buildable, Long> {
}
