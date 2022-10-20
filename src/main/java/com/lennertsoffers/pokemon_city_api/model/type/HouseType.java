package com.lennertsoffers.pokemon_city_api.model.type;

import com.lennertsoffers.pokemon_city_api.model.SpritesheetLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Static house data
 */
@Getter
@AllArgsConstructor
public enum HouseType implements BuildableType {
    SHED("Shed", -3, 500, 5000, 1, 3, 2, 1, 700, 1, new SpritesheetLocation(347, 365)),
    LOW_HOUSE("Low House", -3, 500, 10000, 1, 3, 2, 1, 1000, 1, new SpritesheetLocation(323, 341)),
    SMALL_HOUSE("Small House", -3, 1000, 10000, 2, 3,2, 1, 1000, 2, new SpritesheetLocation(144, 162)),
    MODERN_HOUSE("Modern House", -5, 1000, 15000, 3, 3, 2, 1, 1500, 2, new SpritesheetLocation(235, 253)),
    HUT("Hut", -4, 1000, 20000, 4, 3, 2, 1, 2000, 2, new SpritesheetLocation(320, 338)),
    TIPI("Tipi", -4, 1500, 25000, 5, 2, 2, 2, 2500, 3, new SpritesheetLocation(94, 111)),
    COAL_HEATED_HOUSE("Coal-Heated House", -5, 1500, 30000, 6, 3, 2, 2, 3000, 3, new SpritesheetLocation(368, 394)),
    STANDARD_HOUSE("Standard House", -5, 2000, 40000, 7, 3, 2, 2, 4000, 4, new SpritesheetLocation(147, 165)),
    FAMILY_HOUSE("Family House", -6, 2500, 45000, 8, 3, 2, 2, 4500, 5, new SpritesheetLocation(344, 362)),
    STRAW_ROOFED_HOUSE("Straw Roofed House", -6, 2500, 50000, 9, 3, 2, 3, 5000, 5, new SpritesheetLocation(288, 314)),
    AESTHETIC_HOUSE("Aesthetic House", -6, 3500, 70000, 10, 3, 2, 3, 7000, 7, new SpritesheetLocation(168, 194)),
    DUPLEX("Duplex", -7, 5000, 85000, 11, 3, 2, 3, 8500, 10, new SpritesheetLocation(501, 519)),
    LUXURIOUS_DUPLEX("Luxurious Duplex", -7, 5000, 100000, 12, 3, 2, 4, 10000, 10, new SpritesheetLocation(232, 250)),
    JAPANESE_STYLE_HOUSE("Japanese Style House", -5, 6500, 110000, 13, 3, 2, 4, 11000, 13, new SpritesheetLocation(403, 429)),
    SMALL_RANCH_HOUSE("Small Ranch House", -8, 7000, 120000, 14, 3, 2, 4, 12000, 14, new SpritesheetLocation(171, 197)),
    LARGE_RANCH_HOUSE("Large Ranch House", -8, 7000, 150000, 15, 3, 2, 4, 15000, 14, new SpritesheetLocation(56, 82)),
    BUNGALOW("Bungalow", -7, 7500, 160000, 16, 3, 2, 5, 16000, 15, new SpritesheetLocation(371, 397)),
    TWO_STORY_HOUSE("Two Story House", -8, 8500, 175000, 17, 3, 2, 5, 17500, 17, new SpritesheetLocation(400, 426)),
    CABIN("Cabin", -7, 9000, 170000, 18, 3, 2, 5, 17000, 18, new SpritesheetLocation(204, 230)),
    LUXURIOUS_TWO_STORY_HOUSE("Luxurious Two Story House", -9, 8500, 180000, 19, 3, 2, 5, 18000, 17, new SpritesheetLocation(4, 38)),
    FARMHOUSE("Farmhouse", -9, 10000, 200000, 20, 3, 3, 5, 20000, 20, new SpritesheetLocation(200, 227)),
    LARGE_CABIN("Large Cabin", -9, 11000, 210000, 21, 6, 2, 5, 21000, 22, new SpritesheetLocation(256, 285)),
    ENERGY_NEUTRAL_HOUSE("Energy-Neutral House", -0, 12500, 180000, 22, 4, 3, 8, 18000, 25, new SpritesheetLocation(432, 459)),
    CHALET("Chalet", -9, 10500, 220000, 23, 3, 3, 5, 22000, 21, new SpritesheetLocation(469, 495)),
    ANTIQUE_HOUSE("Antique House", -6, 11500, 240000, 24, 4, 2, 5, 24000, 23, new SpritesheetLocation(620, 639)),
    FORTIFIED_HOUSE("Fortified House", -10, 12000, 250000, 25, 4, 2, 6, 25000, 24, new SpritesheetLocation(616, 635)),
    HUNTERS_HOUSE("Hunter's House", -8, 13000, 260000, 26, 5, 2, 6, 26000, 26, new SpritesheetLocation(752, 780)),
    LARGE_ANTIQUE_HOUSE("Large Antique House", -6, 14000, 270000, 27, 5, 3, 6, 27000, 28, new SpritesheetLocation(584, 612)),
    MANSION("Mansion", -10, 15500, 280000, 28, 5, 3, 6, 28000, 31, new SpritesheetLocation(464, 492)),
    HUGE_MANSION("Huge Mansion", -10, 15500, 300000, 29, 5, 3, 7, 30000, 31, new SpritesheetLocation(496, 540)),
    LUXURIOUS_MANSION("Luxurious Mansion", -10, 16500, 340000, 30, 5, 3, 7, 34000, 33, new SpritesheetLocation(544, 580)),
    SMALL_APPARTEMENT("Small Appartement", -12, 17500, 370000, 31, 4, 3, 7, 37000, 35, new SpritesheetLocation(0, 51)),
    MEDIUM_APPARTEMENT("Medium Appartement", -12, 18500, 390000, 32, 4, 3, 7, 39000, 37, new SpritesheetLocation(44, 87)),
    LOW_APPARTEMENT("Low Appartement", -12, 19000, 400000, 33, 5, 3, 7, 40000, 38, new SpritesheetLocation(704, 748)),
    MODERN_APPARTEMENT("Modern Appartement", -12, 20000, 430000, 34, 6, 3, 7, 43000, 40, new SpritesheetLocation(88, 141)),
    HIGH_END_JAPANESE_STYLE_HOUSE("High-End Japanese Style House", -10, 22500, 470000, 35, 6, 2, 8, 47000, 45, new SpritesheetLocation(832, 869)),
    LARGE_APPARTEMENT("Large Appartement", -13, 25000, 510000, 36, 5, 4, 8, 51000, 50, new SpritesheetLocation(640, 700)),
    VILLA("Villa", -14, 30000, 600000, 37, 7, 4, 9, 60000, 60, new SpritesheetLocation(784, 830)),
    LUXURIOUS_APPARTEMENT("Luxurious Appartement", -14, 35000, 720000, 38, 8, 5, 10, 72000, 70, new SpritesheetLocation(872, 943)),
    LARGE_JAPANESE_STYLE_HOUSE("Large Japanese Style House", -10, 37500, 800000, 39, 8, 3, 12, 80000, 75, new SpritesheetLocation(1032, 1071)),
    JAPANESE_STYLE_SKYSCRAPER("Japanese Style Skyscraper", -12, 45000, 950000, 40, 6, 5, 15, 95000, 90, new SpritesheetLocation(944, 1029)),
    LUXURIOUS_SKYSCRAPER_COMPLEX("Luxurious Skyscraper Complex", -18, 100000, 2000000, 41, 8, 9, 20, 200000, 200, new SpritesheetLocation(1072, 1167));

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
    private final SpritesheetLocation spritesheetLocation;

}
