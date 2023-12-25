package util;

import domain.Location;

public class Calculate {

    public static int getLength(Location a, Location b) {
        return (int) Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
    }
}
