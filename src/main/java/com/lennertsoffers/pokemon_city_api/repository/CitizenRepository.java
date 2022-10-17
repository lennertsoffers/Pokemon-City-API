package com.lennertsoffers.pokemon_city_api.repository;

import com.lennertsoffers.pokemon_city_api.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {
    @Query("SELECT c FROM Citizen c WHERE c.city.user.id = :id")
    List<Citizen> getAllFromUser(@Param("id") Long id);

    @Query("SELECT c FROM Citizen c WHERE c.city.user.id = :id AND c.company IS NULL")
    List<Citizen> getAllUnassignedFromUser(@Param("id") Long id);
}
