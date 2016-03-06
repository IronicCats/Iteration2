package Utilities;

import Model.Location;

import java.awt.*;
import java.util.Set;

/**
 * Created by Aidan on 3/1/2016.
 */
public class Utilities {
    public static int getFontWidth(Graphics g, Font font, String text) {
        FontMetrics fontMetrics = g.getFontMetrics(font);
        //...
        return fontMetrics.stringWidth(text);
    }

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

    public static int calculateTileCenterXLocation(Location location) {
        int x = location.getX();
        int y = location.getY();
        int pixelX = (x/2) * (Settings.TILEWIDTH / 2);
        return ((x * Settings.TILEWIDTH) - (x % 2 == 0 ? 0:Settings.TILEWIDTH / 4) - pixelX) + Settings.TILEWIDTH/2;

    }
    public static int calculateTileCenterYLocation(Location location) {
        int x = location.getX();
        int y = location.getY();
        return (y * Settings.TILEHEIGHT + (x % 2 == 0 ? 0:Settings.TILEHEIGHT / 2)) + Settings.TILEHEIGHT/2;
    }

}
