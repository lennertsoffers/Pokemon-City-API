package com.lennertsoffers.pokemon_city_api.model.mapper;

import com.lennertsoffers.pokemon_city_api.model.User;
import com.lennertsoffers.pokemon_city_api.model.dto.UserDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * <p>Mapper to map User to userDataDto</p>
 */
@Component
@RequiredArgsConstructor
public class UserMapper {
    private final StatisticsMapper statisticsMapper;

    /**
     * <p>Maps a User object to a UserDataDto object</p>
     * @param user The User that has to be mapped
     * @return The UserDataDto object created from the User
     * @see UserDataDto
     */
    public UserDataDto toUserDataDto(User user) {
        return new UserDataDto(
                user.getId(),
                user.getUsername(),
                user.getXp(),
                user.getLevel(),
                user.getMoney(),
                user.getCity().getAmountOfCitizens(),
                user.getCity().getAmountOfEmployedCitizens(),
                user.getCity().getSatisfaction(),
                user.getCity().getName(),
                statisticsMapper.toStatisticsDto(user.getStatistics())
        );
    }
}
