package com.lennertsoffers.pokemon_city_api.util;

/**
 * Collection of Util methods for time
 */
public class TimeUtils {
    /**
     * Converts time in minutes to time in milliseconds
     * @param min Amount of minutes
     * @return The converted time in milliseconds
     */
    public static int minToMilliseconds(int min) {
        return min * 60 * 1000;
    }

    /**
     * Converts time in days to time in milliseconds
     * @param days Amount of days
     * @return The converted time in milliseconds
     */
    public static int daysToMilliseconds(int days) {
        return days * 24 * 60 * 60 * 1000;
    }
}
