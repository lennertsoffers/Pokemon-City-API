package com.lennertsoffers.pokemon_city_api.repository;

import com.lennertsoffers.pokemon_city_api.model.IncomeBuilding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeBuildingRepository extends JpaRepository<IncomeBuilding, Long> {
    IncomeBuilding findIncomeBuildingById(Long id);

    @Query("SELECT i FROM IncomeBuilding i WHERE i.city.user.id = :id AND (i.buildableTypeEnum = 'COMPANY' OR i.buildableTypeEnum = 'HOUSE')")
    List<IncomeBuilding> getAllFromUser(@Param("id") Long id);
}
