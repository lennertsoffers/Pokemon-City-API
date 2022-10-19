package com.lennertsoffers.pokemon_city_api.model;

import com.lennertsoffers.pokemon_city_api.model.type.BuildableTypeEnum;
import com.lennertsoffers.pokemon_city_api.model.type.RoadType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("Road")
@Getter
@Setter
@NoArgsConstructor
public class Road extends Buildable {
    public Road(RoadType roadType, Location location) {
        this.setLocation(location);
        this.setRoadType(roadType);
    }

    @Enumerated
    private RoadType roadType;

    @Enumerated
    private BuildableTypeEnum buildableTypeEnum = BuildableTypeEnum.ROAD;

    @Override
    public String getName() {
        return this.roadType.getName();
    }

    @Override
    public int getSatisfactionModifier() {
        return this.roadType.getSatisfactionModifier();
    }

    @Override
    public int getXpWhenFinished() {
        return this.roadType.getXpWhenFinished();
    }

    @Override
    public int getPrice() {
        return this.roadType.getPrice();
    }

    @Override
    public int getUnlockedAtLevel() {
        return this.roadType.getUnlockedAtLevel();
    }

    @Override
    public int getHeight() {
        return this.roadType.getHeight();
    }

    @Override
    public int getWidth() {
        return this.roadType.getWidth();
    }

    @Override
    public SpritesheetLocation getSpritesheetLocation() {
        return this.roadType.getSpritesheetLocation();
    }
}
