package com.lennertsoffers.pokemon_city_api.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CompanyType implements BuildableType {
    THEE_HOUSE("Thee House", -2, 100, 100, 0, 2, 2, 100, SpecialisationType.COOKING, 1),
    COFFEE_TRUCK("Coffee Truck", -4, 200, 200, 1, 3, 1, 200, SpecialisationType.COOKING, 2),
    ICE_CREAM_TRUCK("Ice Cream Truck", -4, 300, 300, 2, 3, 1, 300, SpecialisationType.COOKING, 3),
    BURGER_TRUCK("Burger Truck", -4, 400, 400, 3, 3, 1, 400, SpecialisationType.COOKING, 4),
    BAKERY("Bakery", -6, 500, 500, 4, 4, 2, 500, SpecialisationType.COOKING, 5),
    TACO_TRUCK("Taco Truck", -4, 600, 600, 5, 3, 1, 600, SpecialisationType.COOKING, 6),
    SIMPLE_RESTAURANT("Simple Restaurant", -10, 700, 700, 6, 6, 4, 700, SpecialisationType.COOKING, 7),
    BISTRO("Bistro", -10, 800, 800, 7, 6, 5, 800, SpecialisationType.COOKING, 8),
    LIGHTHOUSE_RESTAURANT("Lighthouse Restaurant", -15, 900, 900, 8, 7, 5, 900, SpecialisationType.COOKING, 9),
    LUXURIOUS_RESTAURANT("Luxurious Restaurant", -20, 1000, 1000, 9, 16, 15, 1000, SpecialisationType.COOKING, 10),

    SUPERMARKET("Supermarket", -2, 100, 100, 0, 3, 2, 100, SpecialisationType.SELLING, 1),
    FLOWER_SHOP("Flower Shop", -2, 200, 200, 1, 3, 2, 200, SpecialisationType.SELLING, 2),
    STORE("Store", -2, 300, 300, 2, 3, 2, 300, SpecialisationType.SELLING, 3),
    BIKE_STORE("Bike Store", -4, 400, 400, 3, 4, 3, 400, SpecialisationType.SELLING, 4),
    BOOK_STORE("Book Store", -5, 500, 500, 4, 4, 3, 500, SpecialisationType.SELLING, 5),
    WATERSPORT_STORE("Watersport Store", -8, 600, 600, 5, 5, 3, 600, SpecialisationType.SELLING, 6),
    SMALL_SHOPPING_CENTRE("Small Shopping Centre", -10, 700, 700, 6, 5, 3, 700, SpecialisationType.SELLING, 7),
    PAWN_SHOP("Pawn Shop", -12, 800, 800, 7, 6, 4, 800, SpecialisationType.SELLING, 8),
    MEDIUM_SHOPPING_CENTRE("Medium Shopping Centre", -15, 900, 900, 8, 7, 5, 900, SpecialisationType.SELLING, 9),
    LUXURIOUS_SHOPPING_CENTRE("Luxurious Shopping Centre", -20, 1000, 1000, 9, 8, 5, 1000, SpecialisationType.SELLING, 10),

    POKEMON_CENTRE("Pokemon Centre", -2, 100, 100, 0, 4, 3, 100, SpecialisationType.SERVICE, 1),
    POST_OFFICE("Post Office", -3, 200, 200, 1, 4, 3, 200, SpecialisationType.SERVICE, 2),
    DAYCARE("Daycare", -4, 300, 300, 2, 4, 3, 300, SpecialisationType.SERVICE, 3),
    LUXURIOUS_POKEMON_CENTRE("Luxurious Pokemon Centre", -5, 400, 400, 3, 4, 3, 400, SpecialisationType.SERVICE, 4),
    POLICE_OFFICE("Police Office", -5, 400, 400, 3, 5, 4, 400, SpecialisationType.SERVICE, 4),
    RADIO_TOWER("Radio Tower", -6, 500, 500, 4, 3, 3, 500, SpecialisationType.SERVICE, 5),
    RESEARCH_CENTRE("Research Centre", -7, 600, 600, 5, 7, 4, 600, SpecialisationType.SERVICE, 6),
    BANK("Bank", -8, 700, 700, 6, 7, 5, 700, SpecialisationType.SERVICE, 7),
    ARCHAEOLOGICAL_CENTRE("Archaeological Centre", -9, 800, 800, 7, 6, 4, 800, SpecialisationType.SERVICE, 8),
    LIBRARY("Library", -9, 800, 800, 7, 7, 5, 800, SpecialisationType.SERVICE, 8),
    CLOCK_TOWER("Clock Tower", -10, 900, 900, 8, 5, 4, 900, SpecialisationType.SERVICE, 9),
    PHARMACY("Pharmacy", -10, 900, 900, 8, 6, 5, 900, SpecialisationType.SERVICE, 9),
    HOSPITAL("HOSPITAL", -20, 1000, 1000, 9, 14, 8, 1000, SpecialisationType.SERVICE, 10),

    BASIC_GYM("Basic Gym", -1, 100, 100, 0, 4, 2, 100, SpecialisationType.SOCIAL, 1),
    TENT("Tent", -2, 200, 200, 1, 3, 1, 200, SpecialisationType.SOCIAL, 2),
    CHALLENGING_GYM("Challenging Gym", -3, 300, 300, 2, 4, 2, 300, SpecialisationType.SOCIAL, 3),
    CASINO("Casino", -4, 400, 400, 3, 4, 1, 400, SpecialisationType.SOCIAL, 4),
    MUSEUM("Museum", -5, 500, 500, 4, 5, 3, 500, SpecialisationType.SOCIAL, 5),
    CHURCH("Church", -5, 500, 500, 4, 4, 5, 500, SpecialisationType.SOCIAL, 5),
    UNDEFEATED_GYM("Undefeated Gym", -5, 500, 500, 4, 4, 2, 500, SpecialisationType.SOCIAL, 5),
    CHATEAU_HOTEL("Chateau Hotel", -6, 600, 600, 5, 6, 3, 600, SpecialisationType.SOCIAL, 6),
    SMALL_HOTEL("Small Hotel", -6, 600, 600, 5, 6, 5, 600, SpecialisationType.SOCIAL, 6),
    MAYA_TEMPLE("Maya Temple", -6, 600, 600, 5, 8, 6, 600, SpecialisationType.SOCIAL, 6),
    LUXURIOUS_HOTEL("Luxurious Hotel", -8, 700, 700, 6, 8, 5, 700, SpecialisationType.SOCIAL, 7),
    THEATRE("Theatre", -9, 800, 800, 7, 8, 3, 800, SpecialisationType.SOCIAL, 8),
    ENORMOUS_HOTEL("Enormous Hotel", -10, 900, 900, 8, 12, 6, 900, SpecialisationType.SOCIAL, 9);

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
}
