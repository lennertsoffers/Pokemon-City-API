package com.lennertsoffers.pokemon_city_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lennertsoffers.pokemon_city_api.model.type.SpecialisationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int timePlayed = 0;
    private int buildingsBuilt = 0;
    private int buildingsDemolished = 0;
    private int moneySpent = 0;
    private int moneyCollected = 0;

    @OneToOne
    @JsonBackReference
    private User user;

    public int getTotalValue() {
        int buildableValue = this
                .getUser()
                .getCity()
                .getBuildables()
                .stream()
                .mapToInt(Buildable::getPrice)
                .sum();

        return buildableValue + this.getUser().getMoney();
    }

    public double getIncomePerMinute() {
        return this.getUser()
                .getCity()
                .getBuildables()
                .stream()
                .filter(buildable -> buildable instanceof IncomeBuilding)
                .map(IncomeBuilding.class::cast)
                .mapToDouble(IncomeBuilding::getIncomePerMinute)
                .sum();
    }

    public int getMaxedCitizens() {
        return (int) this.getUser()
                .getCity()
                .getCitizens()
                .stream()
                .filter(citizen -> {
                    for (SpecialisationType specialisationType : SpecialisationType.values()) {
                        int dataValue = citizen.getSpecialisationData().get(specialisationType);
                        int maxValue = citizen.getMaxSpecialisationData().get(specialisationType);

                        if (dataValue == maxValue) return true;
                    }

                    return false;
                })
                .count();
    }

    public void updateTimePlayed(int amount) {
        this.timePlayed += amount;
    }

    public void updateBuildingsBuild(int amount) {
        this.buildingsBuilt += amount;
    }

    public void updateBuildingsDemolished(int amount) {
        this.buildingsDemolished += amount;
    }

    public void updateMoneySpent(int amount) {
        this.moneySpent += amount;
    }

    public void updateMoneyCollected(int amount) {
        this.moneyCollected += amount;
    }
}
