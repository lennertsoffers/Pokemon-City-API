package com.lennertsoffers.pokemon_city_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.config.ConfigDataLocationNotFoundException;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    private int x;
    private int y;

    public Location copy() {
        return new Location(this.getX(), this.getY());
    }

    public Location add(int x, int y) {
        this.setX(this.getX() + x);
        this.setY(this.getY() + y);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x && y == location.y;
    }
}
