package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.User;
import com.lennertsoffers.pokemon_city_api.model.dto.UserDataDto;
import com.lennertsoffers.pokemon_city_api.security.RoleType;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    User addRoleToUser(String username, RoleType roleType);

    User getUser(String username);
    User getAuthUser();
    UserDataDto getUserData();
    List<User> getUsers();
}
