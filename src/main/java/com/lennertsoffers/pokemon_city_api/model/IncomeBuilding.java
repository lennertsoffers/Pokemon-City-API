package com.lennertsoffers.pokemon_city_api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * <b>Represents a building that generates income for the user</b>
 * <p>IncomeBuildings have the 'collect' method enabling users to receive the accumulated income from a building over time</p>
 * @see Buildable
 */
@Entity
@Getter
@Setter
public abstract class IncomeBuilding extends Buildable {
    private LocalDateTime lastCollected;

    public abstract double getIncomePerMinute();

    /**
     * Sets the lastCollectedTime to the current sys-time
     */
    public Integer collect() {
        this.setLastCollected(LocalDateTime.now());
        return 0;
    }

    /**
     * Calculates and returns how many minutes have passed since the last time money was collected from this building
     * @return The amount of minutes passed since the last collection
     */
    protected long getMinutesSinceLastCollection() {
        LocalDateTime lastCollected = this.getLastCollected();
        LocalDateTime now = LocalDateTime.now();

        Duration duration = Duration.between(lastCollected, now);

        return duration.toMinutes();
    }

    /**
     * Updates the MoneyCollected statistic of the user
     * @param amount The amount of money collected
     */
    protected void updateStatisticMoneyCollected(int amount) {
        this.getCity().getUser().getStatistics().updateMoneyCollected(amount);
    }
}
