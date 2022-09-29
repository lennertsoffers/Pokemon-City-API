package com.lennertsoffers.pokemon_city_api.security;

public enum RoleType {
    PLAYER,
    ADMIN;

    public String fullName() {
        return "ROLE_" + this;
    }
}
