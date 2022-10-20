package com.lennertsoffers.pokemon_city_api.service;

public interface IncomeBuildingService {
    /**
     * Collects the accumulated money in the IncomeBuilding with the provided id
     * @param id The id of the IncomeBuilding where collect the money
     * @return The amount of money that got collected
     */
    Integer collect(Long id);
}
