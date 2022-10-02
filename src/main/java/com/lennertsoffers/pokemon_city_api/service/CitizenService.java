package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.Citizen;
import com.lennertsoffers.pokemon_city_api.model.City;
import com.lennertsoffers.pokemon_city_api.model.dto.CitizenAssignmentDto;
import com.lennertsoffers.pokemon_city_api.model.dto.CitizenDto;

import java.util.List;

public interface CitizenService {
    List<CitizenDto> getAllFromCurrentUser();
    Citizen spawnCitizen(City city);
    boolean assignToCompany(CitizenAssignmentDto citizenAssignmentDto);
    boolean unAssign(Long citizenId);
    boolean belongsToUser(Long id);
}
