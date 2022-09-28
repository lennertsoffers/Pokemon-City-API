package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.Role;
import com.lennertsoffers.pokemon_city_api.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    void saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    User getUser(String username);
    List<User> getUsers();
}
