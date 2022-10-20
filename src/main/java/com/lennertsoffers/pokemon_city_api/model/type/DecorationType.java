package com.lennertsoffers.pokemon_city_api.model.type;

import com.lennertsoffers.pokemon_city_api.model.SpritesheetLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Static decoration data
 */
@AllArgsConstructor
@Getter
public enum DecorationType implements BuildableType {
    NORMAL_TREE("Normal Tree", 1, 100, 50, 1, 3, 2, new SpritesheetLocation(3, 21)),
    SMALL_ROCK("Small Rock", 2, 200, 110, 3, 1, 1, new SpritesheetLocation(71, 71)),
    SIGN("Sign", 3, 300, 180, 4, 1, 1, new SpritesheetLocation(111, 119)),
    DEAD_TREE("Dead Tree", 4, 400, 260, 5, 1, 1, new SpritesheetLocation(76, 84)),
    STONE_LANTERN("Stone Lantern", 5, 500, 350, 6, 1, 1, new SpritesheetLocation(23, 31)),
    SPRUCE_TREE("Spruce Tree", 6, 600, 450, 7, 3, 2, new SpritesheetLocation(27, 45)),
    PINK_HEDGE("Pink Hedge", 7, 700, 560, 8, 1, 1, new SpritesheetLocation(181, 189)),
    WIDE_TREE("Wide Tree", 8, 800, 680, 9, 3, 2, new SpritesheetLocation(24, 42)),
    BALLOONS("Balloons", 9, 900, 810, 10, 1, 1, new SpritesheetLocation(39, 47)),
    MAILBOX("Mailbox", 10, 1000, 950, 11, 1, 1, new SpritesheetLocation(127, 135)),
    BABY_TREE("Baby Tree", 11, 1100, 1100, 12, 1, 1, new SpritesheetLocation(38, 46)),
    ORANGE_HEDGE("Orange Hedge", 12, 1200, 1260, 13, 1, 1, new SpritesheetLocation(171, 179)),
    METEORITE("Meteorite", 13, 1300, 1430, 14, 1, 1, new SpritesheetLocation(106, 106)),
    SPRING_TREE("Spring Tree", 14, 1400, 1610, 15, 3, 2, new SpritesheetLocation(48, 66)),
    STEM("Stem", 15, 1500, 1800, 16, 1, 1, new SpritesheetLocation(187, 187)),
    SMALL_LANTERN("Small Lantern", 16, 1600, 2000, 17, 1, 1, new SpritesheetLocation(22, 30)),
    BANNER("Banner", 17, 1700, 2210, 18, 1, 1, new SpritesheetLocation(54, 70)),
    SMALL_CACTUS("Small Cactus", 18, 1800, 2430, 19, 1, 1, new SpritesheetLocation(143, 143)),
    LARGE_ROCK("Large Rock", 19, 1900, 2660, 20, 2, 2, new SpritesheetLocation(91, 100)),
    MODERN_LANTERN("Modern Lantern", 20, 2000, 2900, 21, 1, 1, new SpritesheetLocation(7, 15)),
    PALM_TREE("Palm Tree", 21, 2100, 3150, 22, 2, 1, new SpritesheetLocation(154, 163)),
    APPLE_TREE("Apple Tree", 22, 2200, 3410, 23, 2, 1, new SpritesheetLocation(72, 89)),
    EXOTIC_FLOWERS("Exotic Flowers", 23, 2300, 3680, 24, 2, 1, new SpritesheetLocation(96, 97)),
    FLUFFY_HEDGE("Fluffy Hedge", 24, 2400, 3960, 25, 1, 1, new SpritesheetLocation(147, 157)),
    LARGE_MOSSY_ROCK("Large Mossy Rock", 25, 2500, 4250, 26, 2, 2, new SpritesheetLocation(152, 161)),
    TORCH("Torch", 26, 2600, 4550, 27, 1, 1, new SpritesheetLocation(90, 98)),
    DARK_OAK_TREE("Dark Oak Tree", 27, 2700, 4860, 28, 3, 1, new SpritesheetLocation(51, 69)),
    SUNFLOWERS("Sunflowers", 28, 2800, 5180, 29, 2, 2, new SpritesheetLocation(196, 205)),
    POKEBALL_STATUE("Pokeball Statue", 29, 2900, 5510, 30, 2, 2, new SpritesheetLocation(148, 173)),
    FLOWER_BED("Flower Bed", 30, 3000, 5850, 31, 1, 2, new SpritesheetLocation(55, 63)),
    MAINTAINED_TREE("Maintained Tree", 31, 3100, 6200, 32, 3, 2, new SpritesheetLocation(0, 18)),
    EXOTIC_BLUE_FLOWERS("Exotic Blue Flowers", 32, 3200, 6560, 33, 2, 1, new SpritesheetLocation(104, 105)),
    PURPLE_HEDGE("Purple Hedge", 33, 3300, 6930, 34, 1, 1, new SpritesheetLocation(180, 188)),
    BELL("Bell", 34, 3400, 7310, 35, 2, 1, new SpritesheetLocation(82, 83)),
    POKEBALL_TROPHY("Pokeball Trophy", 35, 3500, 7700, 36, 2, 2, new SpritesheetLocation(198, 207)),
    LARGE_FLOWER_BED("Large Flower Bed", 36, 3600, 8100, 37, 3, 2, new SpritesheetLocation(168, 178)),
    POKEMON_STATUE("Pokemon Statue", 37, 3700, 8510, 38, 2, 2, new SpritesheetLocation(150, 175)),
    ENORMOUS_ROCK("Enormous Rock", 38, 3800, 8930, 39, 3, 2, new SpritesheetLocation(77, 103)),
    LARGE_CACTUS("Large Cactus", 39, 3900, 9360, 40, 2, 1, new SpritesheetLocation(182, 191)),
    SLOPOKE_WELL("Slopoke Well", 40, 4000, 9800, 41, 2, 3, new SpritesheetLocation(192, 209)),
    AESTHETIC_LANTERN("Aesthetic Lantern", 41, 4100, 10250, 42, 1, 1, new SpritesheetLocation(6, 14)),
    TRAINER_STATUE("Trainer Statue", 42, 4200, 10710, 43, 3, 2, new SpritesheetLocation(112, 146)),
    JUNGLE_TREE("Jungle Tree", 43, 4300, 11180, 44, 2, 1, new SpritesheetLocation(107, 142)),
    TEMPLE("Temple", 200, 4400, 53000, 50, 6, 3, new SpritesheetLocation(216, 293));

    private final String name;
    private final int satisfactionModifier;
    private final int xpWhenFinished;
    private final int price;
    private final int unlockedAtLevel;
    private final int width;
    private final int height;
    private final SpritesheetLocation spritesheetLocation;
}
