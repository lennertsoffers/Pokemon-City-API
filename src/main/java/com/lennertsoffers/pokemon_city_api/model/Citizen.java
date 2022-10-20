package com.lennertsoffers.pokemon_city_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lennertsoffers.pokemon_city_api.model.type.SpecialisationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>Represents a citizen linked to a city</p>
 * <p>Note that houses provide citizens, but that citizens are not linked to a specific house</p>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Citizen {
    private static final int UPDATES_AFTER = 6;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private LocalDateTime assignedSince;
    private LocalDateTime updatedOn;
    private int levelSpeed;

    @ElementCollection
    @MapKeyColumn(name="specialisationType")
    @Column(name="value")
    @CollectionTable(name="specialisationData", joinColumns=@JoinColumn(name="id"))
    private Map<SpecialisationType, Integer> specialisationData;

    @ElementCollection
    @MapKeyColumn(name="specialisationType")
    @Column(name="value")
    @CollectionTable(name="maxSpecialisationData", joinColumns=@JoinColumn(name="id"))
    private Map<SpecialisationType, Integer> maxSpecialisationData;

    @ManyToOne
    @JsonBackReference
    private City city;

    @ManyToOne
    @JsonBackReference
    private Company company;

    /**
     * Assigns this citizen to a company
     * @param company The company to which the citizen should get assigned
     * @return True if the assignment was successful
     */
    public boolean assignToCompany(Company company) {
        // The assignment is unsuccessful if the company has no more free spaces to assign employees
        if (company.isFullyAssigned()) return false;

        this.setCompany(company);
        this.setAssignedSince(LocalDateTime.now());
        this.setUpdatedOn(LocalDateTime.now());
        company.assign(this);
        return true;
    }

    /**
     * Un-assigns this citizen from its company
     * @return True if the unemployment was successful
     */
    public boolean unEmploy() {
        // The unemployment is unsuccessful if the citizen isn't assigned to a company
        if (this.getCompany() == null) return false;

        this.getCompany().unAssing(this.getId());
        this.assignNull();

        return true;
    }

    /**
     * Updates the SpecialisationData of the citizen
     */
    public void update() {
        // If the citizen isn't assigned to a company, it can't be updated
        if (this.getCompany() == null) return;

        // If the citizen is already updated in the last 'UPDATES_AFTER' hours, it won't get updated again
        int hoursSinceLastUpdate = this.getHoursSinceLastUpdate();
        if (hoursSinceLastUpdate < UPDATES_AFTER) return;

        // Calculate how many updateCycles have already passed
        int updateCycles = hoursSinceLastUpdate / UPDATES_AFTER;
        // Calculate the resting time after the n amount of update cycles
        // = The time that already has passed in the current cycle
        int timeInCurrentCycle = hoursSinceLastUpdate % UPDATES_AFTER;

        // The last time the citizen was updated, is the time now - the time that has already passed in the current cycle
        LocalDateTime updatedOn = LocalDateTime.now().minusHours(timeInCurrentCycle);
        this.setUpdatedOn(updatedOn);

        // Get the specialisationType of the company the citizen is assigned to
        SpecialisationType specialisationType = this.getCompany().getSpecialisationType();
        // Foreach specialisation type that exists, update it by the amount of updateCycles that have passed
        for (SpecialisationType type : SpecialisationType.values()) {
            int newValue;

            // The specialisationType of the company has to be increased
            if (type.equals(specialisationType)) {
                // The new value is the current value + the level speed + amount of cycles that have passed since last update
                newValue = this.getSpecialisationData().get(type) + this.getLevelSpeed() * updateCycles;
                // The value cannot be higher than the citizens max specialisation for this type
                newValue = Math.min(this.getMaxSpecialisationData().get(type), newValue);
            }
            // The other specialisationTypes have to be decreased
            else {
                // The new value is the current value - amount of cycles that have passed since last update
                newValue = this.getSpecialisationData().get(type) - updateCycles;
                // The value cannot be lower than 0
                newValue = Math.max(0, newValue);
            }

            // Assign the new value to the current type in the loop
            this.getSpecialisationData().put(type, newValue);
        }
    }

    /**
     * Resets the company and the time the citizen was assigned to null
     */
    protected void assignNull() {
        this.setAssignedSince(null);
        this.setCompany(null);
    }

    /**
     * Calculates the amount of whole hours that have passed since the last time the citizen got updated
     * @return Amount of hours since the last update
     */
    private int getHoursSinceLastUpdate() {
        LocalDateTime updatedOn = this.getUpdatedOn();
        LocalDateTime now = LocalDateTime.now();

        Duration duration = Duration.between(updatedOn, now);

        return (int) duration.toHours();
    }
}
