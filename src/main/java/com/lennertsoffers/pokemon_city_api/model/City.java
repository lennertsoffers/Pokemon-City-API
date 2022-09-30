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
    private static final int SATISFACTION_RANGE = 50;

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

        return (satisfactionSum + SATISFACTION_RANGE) / (SATISFACTION_RANGE * 2F) + 0.5;
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
}
