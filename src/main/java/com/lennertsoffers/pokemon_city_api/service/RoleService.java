package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.Role;
import com.lennertsoffers.pokemon_city_api.security.RoleType;
import org.springframework.stereotype.Service;

public interface RoleService {
    void saveRole(Role role);
    boolean rolePersisted(RoleType role);
}
