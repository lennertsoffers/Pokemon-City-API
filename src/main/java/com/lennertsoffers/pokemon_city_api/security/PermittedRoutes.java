package com.lennertsoffers.pokemon_city_api.security;

import java.util.List;

/**
 * <p>Holds the routes that are publicly available, where you don't have to provide any authorization headers</p>
 */
public class PermittedRoutes {
    private static final List<String> permittedRoutes = List.of(
            "/auth/login",
            "/auth/register",
            "/auth/refreshToken",
            "/api/buildables/data",
            "/swagger-ui/*",
            "/swagger-resources/configuration/ui",
            "/swagger-resources/configuration/security",
            "/swagger-resources",
            "/v2/api-docs"
    );

    /**
     * Get the regex matching the different permitted routes
     * @return The regex matching the routes
     */
    public static String toRegex() {
        return "(" + String.join("|", permittedRoutes) + ")";
    }

    /**
     * Returns a string array from the permitted routes List
     * @return A String[] containing the same values as the permitted routes List
     */
    public static String[] toArray() {
        return permittedRoutes.toArray(new String[0]);
    }
}
