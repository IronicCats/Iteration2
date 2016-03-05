package Utilities;

import Model.Location;

import java.util.Set;

/**
 * Created by Aidan on 3/1/2016.
 */
public class Utilities {
    public static int parseInt(String string){
        try {
            return Integer.parseInt(string);
        }catch(NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int calculateHexXLocation(int x, int y) {
        int pixelX = (x/2) * (Settings.TILEWIDTH / 2);
        return (x * Settings.TILEWIDTH) - (x % 2 == 0 ? 0:Settings.TILEWIDTH / 4) - pixelX;
    }

    public static int calculateHexYLocation(int x, int y) {
        return y * Settings.TILEHEIGHT + (x % 2 == 0 ? 0:Settings.TILEHEIGHT / 2);
    }

    public static int calculateHexXLocation(Location location) {
        int x = location.getX();
        int y = location.getY();
        int pixelX = (x/2) * (Settings.TILEWIDTH / 2);
        return (x * Settings.TILEWIDTH) - (x % 2 == 0 ? 0:Settings.TILEWIDTH / 4) - pixelX;
    }

    public static int calculateHexYLocation(Location location) {
        int x = location.getX();
        int y = location.getY();
        return y * Settings.TILEHEIGHT + (x % 2 == 0 ? 0:Settings.TILEHEIGHT / 2);
    }
//  (x * Settings.TILEWIDTH - xOffset - offsetX - ((x/2)* (Settings.TILEWIDTH / 2)))
//  (y * Settings.TILEHEIGHT - yOffset + offsetY)

}
