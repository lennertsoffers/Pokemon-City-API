package com.lennertsoffers.pokemon_city_api.model;

import com.lennertsoffers.pokemon_city_api.model.type.DecorationType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("Decoration")
public class Decoration extends Buildable {
    @Enumerated
    private DecorationType decorationType;

    @Override
    public String getName() {
        return this.decorationType.getName();
    }

    @Override
    public int getSatisfactionModifier() {
        return this.decorationType.getSatisfactionModifier();
    }

    @Override
    public int getXpWhenFinished() {
        return this.decorationType.getXpWhenFinished();
    }

    @Override
    public int getPrice() {
        return this.decorationType.getPrice();
    }

    @Override
    public int getUnlockedAtLevel() {
        return this.decorationType.getUnlockedAtLevel();
    }

    @Override
    public int getHeight() {
        return this.decorationType.getHeight();
    }

    @Override
    public int getWidth() {
        return this.decorationType.getWidth();
    }
}
