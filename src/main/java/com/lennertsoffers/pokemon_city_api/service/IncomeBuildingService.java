package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.IncomeBuilding;

import java.util.List;

public interface IncomeBuildingService {
    List<IncomeBuilding> getAllFromUser(Long id);
    Integer collect(Long id);
}
