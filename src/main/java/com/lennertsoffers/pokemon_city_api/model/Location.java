package com.lennertsoffers.pokemon_city_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * <b>Represents a location in the city</b>
 * <p>A location has x and y coordinates</p>
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    private int x;
    private int y;

    /**
     * Returns a deep copy Location instance of this location
     * @return A copy of this location instance
     */
    public Location copy() {
        return new Location(this.getX(), this.getY());
    }

    /**
     * Adds x and y values to the current location
     * @param x The amount that has to be added to the x-value of the location
     * @param y The amount that has to be added to the y-value of the location
     * @return The same location instance with updated x and y values
     */
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
