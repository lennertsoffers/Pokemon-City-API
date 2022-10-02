package com.lennertsoffers.pokemon_city_api.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SpecialisationType {
    COOKING("Cooking"),
    SOCIAL("Social"),
    SERVICE("Service"),
    SELLING("Selling");

    private final String name;
}
