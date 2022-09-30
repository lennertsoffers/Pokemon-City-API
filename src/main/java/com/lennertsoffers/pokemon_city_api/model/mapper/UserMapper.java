package com.lennertsoffers.pokemon_city_api.model.mapper;

import com.lennertsoffers.pokemon_city_api.model.User;
import com.lennertsoffers.pokemon_city_api.model.dto.UserDataDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDataDto toUserDataDto(User user) {
        return new UserDataDto(
                user.getId(),
                user.getUsername(),
                user.getXp(),
                user.getLevel(),
                user.getMoney(),
                user.getStatistics()
        );
    }
}
