package com.lennertsoffers.pokemon_city_api.model.type;

import com.lennertsoffers.pokemon_city_api.model.SpritesheetLocation;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Static company data
 */
@AllArgsConstructor
@Getter
public enum CompanyType implements BuildableType {
    TEA_HOUSE("Tea House", -3, 1600, 8100, 1, 2, 2, 2, SpecialisationType.COOKING, 1, new SpritesheetLocation(32, 50)),
    COFFEE_TRUCK("Coffee Truck", -6, 1600, 8100, 1, 3, 1, 2, SpecialisationType.COOKING, 1, new SpritesheetLocation(3, 13)),
    ICE_CREAM_TRUCK("Ice Cream Truck", -6, 1600, 8100, 1, 3, 1, 2, SpecialisationType.COOKING, 1, new SpritesheetLocation(16, 26)),
    BURGER_TRUCK("Burger Truck", -6, 1600, 8100, 1, 3, 1, 2, SpecialisationType.COOKING, 1, new SpritesheetLocation(0, 10)),
    BAKERY("Bakery", -8, 6500, 32400, 2, 4, 2, 4, SpecialisationType.COOKING, 2, new SpritesheetLocation(35, 62)),
    TACO_TRUCK("Taco Truck", -6, 3200, 16200, 2, 3, 1, 4, SpecialisationType.COOKING, 1, new SpritesheetLocation(19, 29)),
    SIMPLE_RESTAURANT("Simple Restaurant", -10, 19400, 97200, 4, 6, 4, 8, SpecialisationType.COOKING, 3, new SpritesheetLocation(112, 157)),
    BISTRO("Bistro", -12, 24300, 121500, 5, 6, 5, 10, SpecialisationType.COOKING, 3, new SpritesheetLocation(64, 109)),
    LIGHTHOUSE_RESTAURANT("Lighthouse Restaurant", -15, 48600, 243000, 10, 7, 5, 20, SpecialisationType.COOKING, 3, new SpritesheetLocation(160, 246)),
    LUXURIOUS_RESTAURANT("Luxurious Restaurant", -50, 810000, 4050000, 50, 16, 15, 100, SpecialisationType.COOKING, 10, new SpritesheetLocation(248, 551, true)),
    SUPERMARKET("Supermarket", -5, 1600, 8100, 1, 3, 2, 2, SpecialisationType.SELLING, 1, new SpritesheetLocation(101, 119)),
    FLOWER_SHOP("Flower Shop", -1, 1600, 8100, 1, 3, 2, 2, SpecialisationType.SELLING, 1, new SpritesheetLocation(149, 167)),
    STORE("Store", -6, 3200, 16200, 2, 3, 2, 4, SpecialisationType.SELLING, 1, new SpritesheetLocation(125, 143)),
    BIKE_STORE("Bike Store", -6, 6500, 32400, 2, 4, 3, 4, SpecialisationType.SELLING, 2, new SpritesheetLocation(12, 47)),
    BOOK_STORE("Book Store", -7, 9700, 48600, 3, 4, 3, 6, SpecialisationType.SELLING, 2, new SpritesheetLocation(0, 43)),
    WATERSPORT_STORE("Watersport Store", -9, 19400, 97200, 4, 5, 3, 8, SpecialisationType.SELLING, 3, new SpritesheetLocation(152, 196)),
    SMALL_SHOPPING_CENTRE("Small Shopping Centre", -9, 24300, 121500, 5, 5, 3, 10, SpecialisationType.SELLING, 3, new SpritesheetLocation(96, 148)),
    PAWN_SHOP("Pawn Shop", -10, 24300, 121500, 5, 6, 4, 10, SpecialisationType.SELLING, 3, new SpritesheetLocation(48, 93)),
    MEDIUM_SHOPPING_CENTRE("Medium Shopping Centre", -15, 64800, 324000, 10, 7, 5, 20, SpecialisationType.SELLING, 4, new SpritesheetLocation(200, 294)),
    LUXURIOUS_SHOPPING_CENTRE("Luxurious Shopping Centre", -20, 194400, 972000, 20, 8, 5, 40, SpecialisationType.SELLING, 6, new SpritesheetLocation(296, 399)),
    POKEMON_CENTRE("Pokemon Centre", -5, 1600, 8100, 1, 4, 3, 2, SpecialisationType.SERVICE, 1, new SpritesheetLocation(0, 27)),
    POST_OFFICE("Post Office", -6, 3200, 16200, 2, 4, 3, 4, SpecialisationType.SERVICE, 1, new SpritesheetLocation(36, 71)),
    DAYCARE("Daycare", -7, 3200, 16200, 2, 4, 3, 4, SpecialisationType.SERVICE, 1, new SpritesheetLocation(32, 59)),
    LUXURIOUS_POKEMON_CENTRE("Luxurious Pokemon Centre", -7, 13000, 64800, 4, 4, 3, 8, SpecialisationType.SERVICE, 2, new SpritesheetLocation(4, 31)),
    POLICE_OFFICE("Police Office", -8, 13000, 64800, 4, 5, 4, 8, SpecialisationType.SERVICE, 2, new SpritesheetLocation(72, 116)),
    RADIO_TOWER("Radio Tower", -9, 16200, 81000, 5, 3, 3, 10, SpecialisationType.SERVICE, 2, new SpritesheetLocation(320, 372)),
    RESEARCH_CENTRE("Research Centre", -10, 24300, 121500, 5, 7, 4, 10, SpecialisationType.SERVICE, 3, new SpritesheetLocation(120, 174)),
    BANK("Bank", -11, 29200, 145800, 6, 7, 5, 12, SpecialisationType.SERVICE, 3, new SpritesheetLocation(176, 230)),
    ARCHAEOLOGICAL_CENTRE("Archaeological Centre", -12, 38900, 194400, 8, 6, 4, 16, SpecialisationType.SERVICE, 3, new SpritesheetLocation(376, 421)),
    LIBRARY("Library", -14, 77800, 388800, 12, 7, 5, 24, SpecialisationType.SERVICE, 4, new SpritesheetLocation(424, 478)),
    CLOCK_TOWER("Clock Tower", -16, 113400, 567000, 14, 5, 4, 28, SpecialisationType.SERVICE, 5, new SpritesheetLocation(232, 316)),
    PHARMACY("Pharmacy", -22, 145800, 729000, 18, 6, 5, 36, SpecialisationType.SERVICE, 5, new SpritesheetLocation(480, 549)),
    HOSPITAL("Hospital", -25, 283500, 1417500, 25, 14, 8, 50, SpecialisationType.SERVICE, 7, new SpritesheetLocation(553, 726, true)),
    BASIC_GYM("Basic Gym", -5, 1600, 8100, 1, 4, 2, 2, SpecialisationType.SOCIAL, 1, new SpritesheetLocation(0, 19)),
    TENT("Tent", -4, 1600, 8100, 1, 3, 1, 2, SpecialisationType.SOCIAL, 1, new SpritesheetLocation(261, 287)),
    CHALLENGING_GYM("Challenging Gym", -6, 3200, 16200, 2, 4, 2, 4, SpecialisationType.SOCIAL, 1, new SpritesheetLocation(4, 23)),
    CASINO("Casino", -6, 9700, 48600, 3, 4, 1, 6, SpecialisationType.SOCIAL, 2, new SpritesheetLocation(24, 43)),
    MUSEUM("Museum", -7, 16200, 81000, 5, 5, 3, 10, SpecialisationType.SOCIAL, 2, new SpritesheetLocation(256, 300)),
    CHURCH("Church", -8, 16200, 81000, 5, 4, 5, 10, SpecialisationType.SOCIAL, 2, new SpritesheetLocation(736, 796)),
    UNDEFEATED_GYM("Undefeated Gym", -9, 16200, 81000, 5, 4, 2, 10, SpecialisationType.SOCIAL, 2, new SpritesheetLocation(28, 47)),
    CHATEAU_HOTEL("Chateau Hotel", -10, 22700, 113400, 7, 6, 3, 14, SpecialisationType.SOCIAL, 2, new SpritesheetLocation(48, 93)),
    SMALL_HOTEL("Small Hotel", -11, 48600, 243000, 10, 6, 5, 20, SpecialisationType.SOCIAL, 3, new SpritesheetLocation(96, 181)),
    MAYA_TEMPLE("Maya Temple", -15, 90700, 453600, 14, 8, 6, 28, SpecialisationType.SOCIAL, 4, new SpritesheetLocation(184, 255)),
    LUXURIOUS_HOTEL("Luxurious Hotel", -20, 162000, 810000, 20, 8, 5, 40, SpecialisationType.SOCIAL, 5, new SpritesheetLocation(432, 511)),
    THEATRE("Theatre", -40, 453600, 2268000, 35, 8, 3, 70, SpecialisationType.SOCIAL, 8, new SpritesheetLocation(360, 431)),
    ENORMOUS_HOTEL("Enormous Hotel", -45, 583200, 2916000, 40, 12, 6, 80, SpecialisationType.SOCIAL, 9, new SpritesheetLocation(514, 733, true));

    private final String name;
    private final int satisfactionModifier;
    private final int xpWhenFinished;
    private final int price;
    private final int unlockedAtLevel;
    private final int width;
    private final int height;
    private final int profitPerMinute;
    private final SpecialisationType specialisationType;
    private final int maxAssignedCitizens;
    private final SpritesheetLocation spritesheetLocation;
}
