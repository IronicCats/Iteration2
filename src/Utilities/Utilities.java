package Utilities;

import Model.GameObject.MobileObjects.ViewLocation;
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
        return y * Settings.TILEHEIGHT + (x % 2 == 0 ? 0:Settings.TILEHEIGHT / 2) + Settings.TILEHEIGHT/2;
    }

    public static int calculateTileCenterXLocation(int x, int y) {
        int pixelX = (x/2) * (Settings.TILEWIDTH / 2);
        return ((x * Settings.TILEWIDTH) - (x % 2 == 0 ? 0:Settings.TILEWIDTH / 4) - pixelX) + Settings.TILEWIDTH/2;

    }
    public static int calculateTileCenterYLocation(int x, int y) {
        return y * Settings.TILEHEIGHT + (x % 2 == 0 ? 0:Settings.TILEHEIGHT / 2) + Settings.TILEHEIGHT/2;
    }

    public static double calculateSight(int sight, int playX, int tileX){
        int pixelX = (sight/2) * (Settings.TILEWIDTH / 2);
        return (((sight * Settings.TILEWIDTH) - (playX == tileX ? 0:Settings.TILEWIDTH / 4) - pixelX) + Settings.TILEHEIGHT/2);
    }

    public static boolean outOfSite(Location playerLocation, Location tileLocation) {
        int sight = 1;
        if(Math.abs(tileLocation.getX() - playerLocation.getX()) > sight ){
            return true;
        }
        if(Math.abs(tileLocation.getY() - playerLocation.getY()) > sight ){
            return true;
        }
        return false;
    }

    public static boolean outOfSite(ViewLocation playerLocation, ViewLocation tileLocation, int playX, int tileX) {
        double sight = calculateSight(Settings.SIGHT, playX, tileX);
        if(Math.abs(tileLocation.getX() - playerLocation.getX()) > sight ){
            return true;
        }
        if(Math.abs(tileLocation.getY() - playerLocation.getY()) > sight){
            return true;
        }
        return false;
    }

}
