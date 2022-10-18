package com.lennertsoffers.pokemon_city_api.repository;

import com.lennertsoffers.pokemon_city_api.model.Road;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoadRepository extends JpaRepository<Road, Long> {
    @Query("SELECT r FROM Road r WHERE r.city.user.id = :id")
    List<Road> getAllFromUser(@Param("id") Long id);
}
