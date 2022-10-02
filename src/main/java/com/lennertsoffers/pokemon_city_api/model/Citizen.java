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

    public boolean assignToCompany(Company company) {
        if (!company.isAssignable()) return false;

        this.setCompany(company);
        this.setAssignedSince(LocalDateTime.now());
        this.setUpdatedOn(LocalDateTime.now());
        company.assign(this);
        return true;
    }

    public boolean unEmploy() {
        if (this.getCompany() == null) return false;

        this.getCompany().unAssing(this.getId());
        this.setAssignedSince(null);
        this.setCompany(null);

        return true;
    }

    public void update() {
        if (this.getCompany() == null) return;

        int hoursSinceLastUpdate = this.getHoursSinceLastUpdate();
        if (hoursSinceLastUpdate < UPDATES_AFTER) return;

        int timesUpdated = hoursSinceLastUpdate / UPDATES_AFTER;
        int hoursFurtherInTime = hoursSinceLastUpdate % UPDATES_AFTER;

        this.setUpdatedOn(LocalDateTime.now().minusHours(hoursFurtherInTime));

        SpecialisationType specialisationType = this.getCompany().getSpecialisationType();
        for (SpecialisationType type : SpecialisationType.values()) {
            int newValue;

            if (type.equals(specialisationType)) {
                newValue = this.getSpecialisationData().get(type) + this.getLevelSpeed() * timesUpdated;
                newValue = Math.min(this.getMaxSpecialisationData().get(type), newValue);
            } else {
                newValue = this.getSpecialisationData().get(type) - timesUpdated;
                newValue = Math.max(0, newValue);
            }

            this.getSpecialisationData().put(type, newValue);
        }
    }

    private int getHoursSinceLastUpdate() {
        LocalDateTime updatedOn = this.getUpdatedOn();
        LocalDateTime now = LocalDateTime.now();

        Duration duration = Duration.between(updatedOn, now);

        return (int) duration.toHours();
    }
}
