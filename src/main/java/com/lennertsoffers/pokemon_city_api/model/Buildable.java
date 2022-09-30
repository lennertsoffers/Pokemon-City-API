package com.lennertsoffers.pokemon_city_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lennertsoffers.pokemon_city_api.model.type.BuildableTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Buildable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private Location location;

    @Enumerated
    private BuildableTypeEnum buildableTypeEnum;

    @ManyToOne
    @JsonBackReference
    private City city;

    public abstract String getName();
    public abstract int getSatisfactionModifier();
    public abstract int getXpWhenFinished();
    public abstract int getPrice();
    public abstract int getUnlockedAtLevel();
    public abstract int getHeight();
    public abstract int getWidth();
}
