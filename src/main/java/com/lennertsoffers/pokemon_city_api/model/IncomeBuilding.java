package com.lennertsoffers.pokemon_city_api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public abstract class IncomeBuilding extends Buildable {
    private LocalDateTime lastCollected;

    public Integer collect() {
        this.setLastCollected(LocalDateTime.now());
        return 0;
    }

    protected long getRentMinutes() {
        LocalDateTime lastCollected = this.getLastCollected();
        LocalDateTime now = LocalDateTime.now();

        Duration duration = Duration.between(lastCollected, now);

        return duration.toMinutes();
    }
}
