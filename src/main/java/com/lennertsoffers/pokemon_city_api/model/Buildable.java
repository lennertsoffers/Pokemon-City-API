package com.lennertsoffers.pokemon_city_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "buildable_type", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Buildable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private Location location;

    @ManyToOne
    private City city;

    public int getX() {
        return location.getX();
    }

    public int getY() {
        return location.getY();
    }

    public abstract String getName();
    public abstract int getSatisfactionModifier();
    public abstract int getXpWhenFinished();
    public abstract int getPrice();
    public abstract int getUnlockedAtLevel();
    public abstract int getHeight();
    public abstract int getWidth();
}
