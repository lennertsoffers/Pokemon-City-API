package com.lennertsoffers.pokemon_city_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @Embedded
    private SpecialisationData specialisationData;

    @Embedded
    private SpecialisationData maxSpecialisationData;

    @ManyToOne
    private City city;
}
