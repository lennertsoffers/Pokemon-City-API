package com.lennertsoffers.pokemon_city_api.repository;

import com.lennertsoffers.pokemon_city_api.model.Buildable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildableRepository extends JpaRepository<Buildable, Long> {
    @Query("SELECT b FROM Buildable b WHERE b.city.user.id = :id AND NOT b.buildableTypeEnum = 'ROAD'")
    List<Buildable> getAllNotRoadFromUser(@Param("id") Long id);
    List<Buildable> findAllByCityUserId(Long id);
}
