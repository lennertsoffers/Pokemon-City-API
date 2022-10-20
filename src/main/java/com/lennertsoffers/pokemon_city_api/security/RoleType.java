package com.lennertsoffers.pokemon_city_api.security;

/**
 * Holds the different types of roles
 */
public enum RoleType {
    PLAYER,
    ADMIN;

    /**
     * Returns the full name of the role, prefixed by 'ROLE_'
     * @return Returns the full name of the role
     */
    public String fullName() {
        return "ROLE_" + this;
    }
}
