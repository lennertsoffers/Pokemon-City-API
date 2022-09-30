package com.lennertsoffers.pokemon_city_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @OneToOne(mappedBy = "citizen")
    private SpecialisationData specialisationData;

    @OneToOne(mappedBy = "citizen")
    private SpecialisationData maxSpecialisationData;

    @ManyToOne
    @JsonBackReference
    private City city;
}
