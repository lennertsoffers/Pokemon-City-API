package com.lennertsoffers.pokemon_city_api.model.type;

public interface BuildableType {
    String getName();
    int getSatisfactionModifier();
    int getXpWhenFinished();
    int getPrice();
    int getUnlockedAtLevel();
    int getHeight();
    int getWidth();
}
