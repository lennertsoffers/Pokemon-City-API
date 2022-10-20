package com.lennertsoffers.pokemon_city_api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Represents the city of a user
 */
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

    /**
     * <p>Calculates the total satisfaction in the city</p>
     * <ul>
     *     <li>The total satisfaction is the sum of the satisfactionModifiers of all buildings in the city.</li>
     *     <li>This total is bound by the 'MIN_SATISFACTION' and the 'MAX_SATISFACTION'</li>
     *     <li>This bound total is than divided by 100</li>
     * </ul>
     * @return The total amount of satisfaction in the city
     */
    public double getSatisfaction() {
        // Calculate the sum of the satisfactionModifiers of all buildings in the city
        int satisfactionSum = this.getBuildables().stream().mapToInt(Buildable::getSatisfactionModifier).sum();

        // Bound this sum between a min and max
        satisfactionSum = Math.min(MAX_SATISFACTION, Math.max(MIN_SATISFACTION, satisfactionSum));

        // Return this bound sum divided by 100
        return satisfactionSum / 100F;
    }

    /**
     * <p>Calculates true amount of citizens that can and should live in the city</p>
     * <p>This is calculated by calculating the sum of citizens that houses hold</p>
     * <p>This is a safer way to calculate the true amount of citizens, since 'numberOfCitizens' is a static value unlike counting the amount of citizen instances</p>
     * @return The amount of citizens living in the city
     */
    public int getAmountOfCitizens() {
        return this.getBuildables()
                .stream()
                .mapToInt(buildable -> {
                    if (buildable instanceof House house) return house.getNumberOfCitizens();
                    return 0;
                })
                .sum();
    }

    /**
     * <p>Calculates the amount of citizens in the city that are assigned to a company</p>
     * @return The amount of citizens that are assigned to a company
     */
    public int getAmountOfEmployedCitizens() {
        return (int) this.getCitizens()
                .stream()
                .filter(citizen -> citizen.getCompany() != null)
                .count();
    }
}
