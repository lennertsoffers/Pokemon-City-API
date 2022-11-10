package com.lennertsoffers.pokemon_city_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lennertsoffers.pokemon_city_api.model.type.SpecialisationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * <b>Represents a users statistics keeping track of his/her game progress</b>
 */
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

    /**
     * <p>Calculates the total value of the user</p>
     * <p>The total value is the sum of the value of all the users buildables his/her money</p>
     * @return The users total value
     */
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

    /**
     * Calculates the income that a user gets per minute
     * @return The income a user gets per minute
     */
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

    /**
     * Calculates the amount of citizens the user has who have a statistic where the value equals the max value for the statistic for that citizen
     * @return The amount of maxed citizens of the user
     */
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

    /**
     * Calculates the score of the user
     * Score is calculating to multiply all significant statistics from the user
     * The score is at least one
     * @return The score of the user
     */
    public long getScore() {
        return Math.round(Math.max(1, this.getTotalValue()) * Math.max(1, this.getIncomePerMinute()) * Math.max(1, this.getMaxedCitizens()) * Math.max(1, this.getBuildingsBuilt()) / 1000F);
    }

    public void updateTimePlayed(int amount) {
        this.timePlayed += amount;
    }

    public void updateBuildingsBuilt(int amount) {
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
