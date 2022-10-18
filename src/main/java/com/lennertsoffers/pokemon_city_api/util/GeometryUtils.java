package com.lennertsoffers.pokemon_city_api.util;

import com.lennertsoffers.pokemon_city_api.model.Buildable;

public class GeometryUtils {
    public static boolean collidesWith(Buildable buildable, int x, int y, int width, int height) {
        int a_x1 = buildable.getLocation().getX() - buildable.getWidth() + 1;
        int a_x2 = buildable.getLocation().getX();
        int a_y1 = buildable.getLocation().getY() - buildable.getHeight() + 1;
        int a_y2 = buildable.getLocation().getY();

        int b_x1 = x - width + 1;
        int b_y1 = y - height + 1;

        boolean x1Collision = a_x1 <= b_x1 && a_x2 >= b_x1;
        boolean x2Collision = a_x1 <= x && a_x2 >= x;
        boolean xCollision = x1Collision || x2Collision;

        boolean y1Collision = a_y1 <= b_y1 && a_y2 >= b_y1;
        boolean y2Collision = a_y1 <= y && a_y2 >= y;
        boolean yCollision = y1Collision || y2Collision;

        return xCollision && yCollision;
    }
}
