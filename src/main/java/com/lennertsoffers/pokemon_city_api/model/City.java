package com.lennertsoffers.pokemon_city_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate dateCreated;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "city")
    private List<Buildable> buildables;

    @OneToMany(mappedBy = "city")
    private List<Citizen> citizens;
}
