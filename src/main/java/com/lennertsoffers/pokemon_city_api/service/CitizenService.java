package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.City;
import com.lennertsoffers.pokemon_city_api.model.dto.CitizenAssignmentDto;
import com.lennertsoffers.pokemon_city_api.model.dto.CitizenDto;

import java.util.List;

public interface CitizenService {
    /**
     * <p>Returns a list of all the citizens living in the city of the authenticated user</p>
     * @return A list of citizens
     */
    List<CitizenDto> getAllFromCurrentUser();

    /**
     * <p>Returns a list of the citizens living in the city of the authenticated user, but excludes citizens assigned to a company</p>
     * @return A list of citizens
     */
    List<CitizenDto> getUnassignedCitizensFromCurrentUser();

    /**
     * <p>Creates a new citizen with a random name and random specialisation values</p>
     * @param city The city where the citizen should be spawned
     */
    void spawnCitizen(City city);

    /**
     * Deletes the citizen with the specified id
     * @param id Id of the citizen that should be deleted
     */
    void killCitizen(Long id);

    /**
     * Assigns the citizen with the id specified in the DTO to the company with the id specified in the DTO
     * @param citizenAssignmentDto The DTO containing the data to assign a citizen to a company
     * @return True if the assignment was successful
     */
    boolean assignToCompany(CitizenAssignmentDto citizenAssignmentDto);

    /**
     * Un-assigns the citizen from the company it is assigned to
     * @param citizenId The id of the citizen that should be un-assigned
     * @return True if the un-assignment was successful
     */
    boolean unAssign(Long citizenId);

    /**
     * Checks if the citizen with id belongs to the current user
     * @param id The id of the citizen
     * @return True if the citizen belongs to the current user
     */
    boolean belongsToUser(Long id);
}
