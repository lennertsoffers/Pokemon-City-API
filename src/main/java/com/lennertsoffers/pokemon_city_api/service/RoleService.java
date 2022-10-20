package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.Role;
import com.lennertsoffers.pokemon_city_api.security.RoleType;

public interface RoleService {
    /**
     * Persists the role to the database
     * @param role The role to be persisted in the database
     */
    void saveRole(Role role);

    /**
     * Checks if the role already is persisted in the database
     * @param role The role to check if its already persisted
     * @return True if the role is already persisted in the database
     */
    boolean rolePersisted(RoleType role);
}
