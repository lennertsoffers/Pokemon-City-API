package com.lennertsoffers.pokemon_city_api.service;

import com.lennertsoffers.pokemon_city_api.model.City;
import com.lennertsoffers.pokemon_city_api.model.dto.CityChangeNameDto;
import com.lennertsoffers.pokemon_city_api.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final UserService userService;

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public String changeCityName(CityChangeNameDto cityChangeNameDto) {
        City city = userService.getAuthUser().getCity();
        city.setName(cityChangeNameDto.name());

        city = this.save(city);

        return city.getName();
    }
}
