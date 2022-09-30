package com.lennertsoffers.pokemon_city_api.util;

import com.lennertsoffers.pokemon_city_api.model.Buildable;

public class GeometryUtils {
    public static boolean collidesWith(Buildable buildable, int x, int y, int width, int height) {
        int a_x1 = buildable.getLocation().getX();
        int a_x2 = buildable.getLocation().getX() + buildable.getWidth() - 1;
        int a_y1 = buildable.getLocation().getY();
        int a_y2 = buildable.getLocation().getY() + buildable.getHeight() - 1;

        int b_x2 = x + width - 1;
        int b_y2 = y + height - 1;

        boolean ax1 = a_x1 >= x && a_x1 <= b_x2;
        boolean ax2 = a_x2 >= x && a_x2 <= b_x2;

        boolean ay1 = a_y1 >= y && a_y1 <= b_y2;
        boolean ay2 = a_y2 >= y && a_y2 <= b_y2;

        return (ax1 || ax2) && (ay1 || ay2);
    }
}
