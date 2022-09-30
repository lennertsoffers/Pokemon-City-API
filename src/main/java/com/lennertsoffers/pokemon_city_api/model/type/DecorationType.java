package com.lennertsoffers.pokemon_city_api.model.type;

import lombok.Getter;

@Getter
public enum DecorationType implements BuildableType {
    ;

    private String name;
    private int satisfactionModifier;
    private int xpWhenFinished;
    private int price;
    private int unlockedAtLevel;
    private int width;
    private int height;
}
