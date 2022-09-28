package com.lennertsoffers.pokemon_city_api.util;

public class TimeUtils {
    public static int minToMilliseconds(int min) {
        return min * 60 * 1000;
    }

    public static int daysToMilliseconds(int days) {
        return days * 24 * 60 * 60 * 1000;
    }
}
