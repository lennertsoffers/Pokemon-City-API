package com.lennertsoffers.pokemon_city_api.security;

import java.util.List;

public class PermittedRoutes {
    private static final List<String> permittedRoutes = List.of(
            "/auth/login",
            "/auth/register",
            "/auth/refreshToken",
            "/api/buildables/data"
    );

    public static String toRegex() {
        return "(" + String.join("|", permittedRoutes) + ")";
    }

    public static String[] toArray() {
        return permittedRoutes.toArray(new String[0]);
    }
}
