package com.lennertsoffers.pokemon_city_api.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HouseType implements BuildableType {
    SHED("Shed", -1, 100, 100, 0, 3, 2, 1, 100, 1),
    LOW_HOUSE("Low House", -2, 200, 200, 1, 3, 2, 2, 200, 2),
    SMALL_HOUSE("Small House", -3, 300, 300, 2, 3,2, 3, 300, 3),
    MODERN_HOUSE("Modern House", -4, 400, 400, 3, 3, 2, 4, 400, 4),
    HUT("Hut", -5, 500, 500, 4, 3, 2, 5, 500, 5),
    TIPI("Tipi", -6, 600, 600, 5, 2, 2, 6, 600, 6),
    COAL_HEATED_HOUSE("Coal-Heated House", -7, 700, 700, 6, 3, 2, 7, 700, 7),
    STANDARD_HOUSE("Standard House", -8, 800, 800, 7, 3, 2, 8, 800, 8),
    FAMILY_HOUSE("Family House", -9, 900, 900, 8, 3, 2, 9, 900, 9),
    STRAW_ROOFED_HOUSE("Straw Roofed House", -10, 1000, 1000, 9, 3, 2, 10, 1000, 10),
    LUXE_HOUSE("Bungalow", -11, 1100, 1100, 10, 3, 2, 11, 1100, 11),
    AESTHETIC_HOUSE("Aesthetic House", -12, 1200, 1200, 11, 3, 2, 12, 1200, 12),
    DUPLEX("Duplex", -13, 1300, 1300, 12, 3, 2, 13, 1300, 13),
    LUXURIOUS_DUPLEX("Luxurious Duplex", -14, 1400, 1400, 13, 3, 2, 14, 1400, 14),
    JAPANESE_STYLE_HOUSE("Japanese Style House", -15, 1500, 1500, 14, 3, 2, 15, 1500, 15),
    SMALL_RANCH_HOUSE("Small Ranch House", -16, 1600, 1600, 15, 3, 2, 16, 1600, 16),
    MEDIUM_RANCH_HOUSE("Medium Ranch House", -17, 1700, 1700, 16, 3, 2, 17, 1700, 17),
    LARGE_RANCH_HOUSE("Large Ranch House", -18, 1800, 1800, 17, 3, 2, 18, 1800, 18),
    MULTI_FAMILY_HOUSE("Multi Family House", -19, 1900, 1900, 18, 3, 2, 19, 1900, 19),
    BUNGALOW("Bungalow", -20, 2000, 2000, 19, 3, 2, 20, 2000, 20),
    TWO_STORY_HOUSE("Two Story House", -21, 2100, 2100, 20, 3, 2, 21, 2100, 21),
    CABIN("Cabin", -22, 2200, 2200, 21, 3, 2, 22, 2200, 22),

    LUXURIOUS_TWO_STORY_HOUSE("Luxurious Two Story House", -23, 2300, 2300, 22, 3, 2, 23, 2300, 23),
    FARMHOUSE("Farmhouse", -24, 2400, 2400, 23, 3, 3, 24, 2400, 24),
    LARGE_CABIN("Large Cabin", -25, 2500, 2500, 24, 6, 2, 25, 2500, 25),
    ENERGY_NEUTRAL_HOUSE("Energy-Neutral House", -26, 2600, 2600, 25, 4, 3, 26, 2600, 26),
    CHALET("Chalet", -27, 2700, 2700, 26, 3, 3, 27, 2700, 27),
    ANTIQUE_HOUSE("Antique House", -28, 2800, 2800, 27, 4, 2, 28, 2800, 28),
    FORTIFIED_HOUSE("Fortified House", -29, 2900, 2900, 28, 4, 2, 29, 2900, 29),
    HUNTERS_HOUSE("Hunter's House", -30, 3000, 3000, 39, 5, 2, 30, 3000, 30),
    LARGE_ANTIQUE_HOUSE("Large Antique House", -31, 3100, 3100, 30, 5, 3, 31, 3100, 31),
    MANSION("Mansion", -32, 3200, 3200, 31, 5, 3, 32, 3200, 32),
    HUGE_MANSION("Huge Mansion", -33, 3300, 3300, 32, 5, 3, 33, 3300, 33),
    LUXURIOUS_MANSION("Luxurious Mansion", -34, 3400, 3400, 33, 5, 3, 34, 3400, 34),

    SMALL_APPARTEMENT("Small Appartement", -35, 3500, 3500, 34, 4, 3, 35, 3500, 35),
    MEDIUM_APPARTEMENT("Medium Appartement", -36, 3600, 3600, 35, 4, 3, 36, 3600, 36),
    LOW_APPARTEMENT("Low Appartement", -37, 3700, 3700, 36, 5, 3, 37, 3700, 37),
    MODERN_APPARTEMENT("Modern Appartement", -38, 3800, 3800, 37, 6, 3, 38, 3800, 38),
    HIGH_END_JAPANESE_STYLE_HOUSE("High-End Japanese Style House", -39, 3900, 3900, 38, 6, 2, 39, 3900, 39),
    LARGE_APPARTEMENT("Large Appartement", -40, 4000, 4000, 39, 5, 4, 40, 4000, 40),
    VILLA("Villa", -41, 4100, 4100, 40, 7, 4, 41, 4100, 41),
    LUXURIOUS_APPARTEMENT("Luxurious Appartement", -42, 4200, 4200, 41, 8, 5, 42, 4200, 42),
    LARGE_JAPANESE_STYLE_HOUSE("Large Japanese Style House", -43, 4300, 4300, 42, 8, 3, 43, 4300, 43),
    JAPANESE_STYLE_SKYSCRAPER("Japanese Style Skyscraper", -44, 4400, 4400, 43, 6, 5, 44, 4400, 44),
    LUXURIOUS_SKYSCRAPER_COMPLEX("Luxurious Skyscraper Complex", -45, 4500, 4500, 44, 8, 9, 45, 4500, 45);

    private final String name;
    private final int satisfactionModifier;
    private final int xpWhenFinished;
    private final int price;
    private final int unlockedAtLevel;
    private final int width;
    private final int height;
    private final int numberOfCitizens;
    private final int maxRent;
    private final int rentPerMinute;
}
