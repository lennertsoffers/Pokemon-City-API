package com.lennertsoffers.pokemon_city_api.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DecorationType implements BuildableType {
    TREE("Tree", 1, 20, 20, 0, 1, 1);

    private final String name;
    private final int satisfactionModifier;
    private final int xpWhenFinished;
    private final int price;
    private final int unlockedAtLevel;
    private final int width;
    private final int height;
}
