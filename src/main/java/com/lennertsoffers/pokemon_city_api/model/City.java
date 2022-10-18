package com.lennertsoffers.pokemon_city_api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class City {
    private static final int MIN_SATISFACTION = -100;
    private static final int MAX_SATISFACTION = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private LocalDate dateCreated;

    @OneToOne
    @JsonManagedReference
    private User user;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Buildable> buildables;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Citizen> citizens;

    public double getSatisfaction() {
        int satisfactionSum = this.getBuildables().stream().mapToInt(Buildable::getSatisfactionModifier).sum();

        satisfactionSum = Math.min(MAX_SATISFACTION, Math.max(MIN_SATISFACTION, satisfactionSum));

        return satisfactionSum / 100F;
    }

    public int getAmountOfCitizens() {
        return this.getBuildables()
                .stream()
                .mapToInt(buildable -> {
                    if (buildable instanceof House house) return house.getNumberOfCitizens();
                    return 0;
                })
                .sum();
    }

    public int getAmountOfEmployedCitizens() {
        return (int) this.getCitizens()
                .stream()
                .filter(citizen -> citizen.getCompany() != null)
                .count();
    }
}
