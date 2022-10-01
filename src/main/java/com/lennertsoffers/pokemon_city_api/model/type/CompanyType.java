package com.lennertsoffers.pokemon_city_api.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CompanyType implements BuildableType {
    COFFEE_SHOP("Coffee shop", -3, 200, 400, 10, 5, 5, 1,  SpecialisationType.SOCIAL, 2);

    private final String name;
    private final int satisfactionModifier;
    private final int xpWhenFinished;
    private final int price;
    private final int unlockedAtLevel;
    private final int width;
    private final int height;
    private final int profitPerMinute;
    private final SpecialisationType specialisationType;
    private final int maxAssignedCitizens;
}
