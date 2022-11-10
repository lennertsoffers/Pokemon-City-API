package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.Buildable;
import com.lennertsoffers.pokemon_city_api.model.dto.*;
import com.lennertsoffers.pokemon_city_api.model.type.BuildableType;

import java.util.List;

public interface BuildableService {
    /**
     * Gets a list of all the buildables of the user excluding roads as BuildableDto
     * @return A list of BuildableDtos
     */
    List<BuildableDto> getBuildableDtos();

    /**
     * Gets a list of the buildables in the city of the user with the provided id
     * @return A list of BuildablePlacementDtos
     */
    List<BuildablePlacementDto> getBuildablesFromUser(Long userId);

    /**
     * <p>Returns a list of all the static buildable data that is used in the application</p>
     * <p>This will translate to a list of all the TypeEnums that are present in the game</p>
     * @return A list containing all the static data for buildables
     */
    BuildableDataDto getBuildableData();

    /**
     * <p>Returns the single buildable with the provided id</p>
     * @param id The buildableId of the building that has to be returned
     * @return The buildable with the buildableId
     */
    Buildable getById(Long id);

    /**
     * Returns the single buildable with the provided id as a BuildableDto
     * @param id The buildableId of the buildable that has to be returned
     * @return The BuildableDto with the buildableId
     */
    BuildableDto getDtoById(Long id);

    /**
     * <p>Builds and returns a buildable instance of the object that got built</p>
     * @param buildableBuildDto The BuildableBuildDto containing all the information to build a new Buildable
     * @return The information for the buildable that got built
     */
    Buildable build(BuildableBuildDto buildableBuildDto);

    /**
     * <p>Moves and returns a buildable instance of the object that got moved</p>
     * @param buildableMoveDto The BuildableMoveDto containing all the information to move a buildable
     * @return The information for the buildable that got moved
     */
    Buildable move(BuildableMoveDto buildableMoveDto);

    /**
     * <p>Demolishes a buildable by its id and returns a boolean indicating if the demolishing was successful or not</p>
     * @param buildableDemolishDto The BuildableDemolishDto containing all the information to demolish a buildable
     * @return Returns true if the demolishing was successful, false if not
     */
    Boolean demolish(BuildableDemolishDto buildableDemolishDto);

    /**
     * Checks if the buildable belongs to the user with the provided id
     * @param id The id of the user
     * @return True if the buildable belongs to the user with the id
     */
    boolean belongsToUser(Long id);

    /**
     * Returns the corresponding BuildableType
     * @param buildableType Name of the subtype of Buildable
     * @param name Name of the BuildableType
     * @return The corresponding BuildableType to the parameters
     */
    BuildableType getBuildableType(String buildableType, String name);
}
