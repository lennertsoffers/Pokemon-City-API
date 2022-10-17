package com.lennertsoffers.pokemon_city_api.model.dto;

import com.lennertsoffers.pokemon_city_api.model.SpritesheetLocation;
import com.lennertsoffers.pokemon_city_api.model.type.SpecialisationType;

public record CompanyDataDto (
        String name,
        int satisfactionModifier,
        int xpWhenFinished,
        int price,
        int unlockedAtLevel,
        int width,
        int height,
        int profitPerMinute,
        SpecialisationType specialisationType,
        int maxAssignedCitizens,
        SpritesheetLocation spritesheetLocation
) {}
