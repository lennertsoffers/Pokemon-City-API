package com.lennertsoffers.pokemon_city_api.model.dto;

public record DecorationDataDto (
        String name,
        int satisfactionModifier,
        int xpWhenFinished,
        int price,
        int unlockedAtLevel,
        int width,
        int height
) {}
