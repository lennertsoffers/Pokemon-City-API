package com.lennertsoffers.pokemon_city_api.util;

public class LevelUtils {
    public static int getLevelFromXp(int xp) {
        return (int) Math.floor((500 + Math.sqrt(250000 + 2000 * xp)) / 1000);
    }
}
