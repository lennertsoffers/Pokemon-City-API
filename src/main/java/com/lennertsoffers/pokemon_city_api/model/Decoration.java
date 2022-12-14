package com.lennertsoffers.pokemon_city_api.model;

import com.lennertsoffers.pokemon_city_api.model.type.BuildableTypeEnum;
import com.lennertsoffers.pokemon_city_api.model.type.DecorationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

/**
 * <b>Represents a decoration in the city</b>
 * <p>Decorations provide positive satisfaction</p>
 * @see Buildable
 */
@Entity
@DiscriminatorValue("Decoration")
@Getter
@Setter
@NoArgsConstructor
public class Decoration extends Buildable {
    public Decoration(Location location, DecorationType decorationType) {
        this.setLocation(location);
        this.setDecorationType(decorationType);
    }

    @Enumerated
    private DecorationType decorationType;

    @Enumerated
    private BuildableTypeEnum buildableTypeEnum = BuildableTypeEnum.DECORATION;

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

    @Override
    public SpritesheetLocation getSpritesheetLocation() {
        return this.decorationType.getSpritesheetLocation();
    }
}
