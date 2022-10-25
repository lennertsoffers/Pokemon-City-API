package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.User;
import com.lennertsoffers.pokemon_city_api.model.dto.UserCreationDto;
import com.lennertsoffers.pokemon_city_api.model.dto.UserDataDto;
import com.lennertsoffers.pokemon_city_api.model.dto.UserFilterDto;
import com.lennertsoffers.pokemon_city_api.model.dto.UserUpdateStatisticsDto;
import com.lennertsoffers.pokemon_city_api.security.RoleType;

import java.util.List;

public interface UserService {
    /**
     * <p>Registers the user to the application</p>
     * <p>While creating a new user, the user gets assigned a new city and a Statistics instance</p>
     * @param userCreationDto The DTO containing the username and password for the user
     * @return The newly created user
     */
    User register(UserCreationDto userCreationDto);

    /**
     * Persists the user in the database
     * @param user The user to be persisted
     */
    void saveUser(User user);

    /**
     * Adds a RoleType to the user
     * @param username The username of the user to give the new role
     * @param roleType The type of the role to add to the user
     * @return The user instance with the newly added role
     */
    User addRoleToUser(String username, RoleType roleType);

    /**
     * Finds and gets the user by its username
     * @param username The username of the user to be returned
     * @return The user instance if its found, otherwise null
     */
    User getUser(String username);

    /**
     * Finds and gets the user by its id
     * @param userId The id of the user to be returned
     * @return The user instance if its found, otherwise null
     */
    User getUser(Long userId);

    /**
     * Gets the ranking of the users after the minimum for the amount
     * @param min Minimum rank
     * @param amount Amount of users
     * @return The users ranked by score
     */
    List<UserDataDto> getRanking(Integer min, Integer amount);

    /**
     * Gets the users that meet the filters
     * @param filter The filters to be met
     * @return The filtered list of users
     */
    List<UserDataDto> getFiltered(UserFilterDto filter);

    /**
     * @return The user that is authentication in the securityContext
     */
    User getAuthUser();

    /**
     * Returns all the important data of the current user via a UserDataDto
     * @return The UserDataDto containing all the important userData
     */
    UserDataDto getUserData();

    /**
     * @return A list with all the users in the system
     */
    List<User> getUsers();

    /**
     * <p>Updates the statics of the current user, with the values provided in the UserUpdateStatisticsDto</p>
     * @param userUpdateStatisticsDto The UserUpdateStatisticsDto containing values to update the user's statistics
     */
    void updateStatistics(UserUpdateStatisticsDto userUpdateStatisticsDto);
}
