package View.ViewUtilities;

import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.GameObject.MobileObjects.ViewLocation;
import Model.Location;
import Model.Map.Map;
import Utilities.Observer;
import Utilities.Settings;
import Utilities.Utilities;

/**
 * Created by Aidan on 3/2/2016.
 */
public class Camera implements Observer {
    private int gameWidth, gameHeight;
    private int xOffset, yOffset;
    private Map map;
    private int originalXOffset, getOriginalYOffset;
                                     // The Camera will also need a Player object.

    public Camera(int gameWidth, int gameHeight, Map map) {
        this.gameHeight = gameHeight;
        this.gameWidth = gameWidth;
        this.map = map;
        yOffset = 0;
        xOffset = 0;
    }
    //This makes sure that the map doesn't go off the screen and start showing blankspace
    public void keepCameraOnMap() {
        //System.out.println(xOffset);
        //System.out.println(((map.getWidth() - (gameWidth/Settings.TILEWIDTH)) * Settings.TILEWIDTH )-1250);

        if (xOffset < 0) {
            xOffset = 0;
        } else if (xOffset >  ((map.getWidth() - (gameWidth/Settings.TILEWIDTH)) * Settings.TILEWIDTH )-1250){
            xOffset = ((map.getWidth() - (gameWidth/Settings.TILEWIDTH)) * Settings.TILEWIDTH )-1250;
        }
        if (yOffset < 0) {
            yOffset = 0;
        }
        else if (yOffset > (((map.getHeight() + 1) * Settings.TILEHEIGHT) - gameHeight)) {
            yOffset = ((map.getHeight()+1) * Settings.TILEHEIGHT) - gameHeight;

        }
    }

    public void centerOnPlayer(Player player) {
        gameWidth = Settings.GAMEWIDTH;
        gameHeight = Settings.GAMEHEIGHT;
        xOffset = (int)player.getViewLocation().getX() - gameWidth/2 + Settings.PLAYERWIDTH/2;
        yOffset = (int)player.getViewLocation().getY() - gameHeight/2 + Settings.PLAYERHEIGHT/2;
        keepCameraOnMap();
    }

    public void move(int degrees){
        switch(degrees){
            case 45:
                xOffset += Settings.TILEWIDTH/4;
                yOffset -= Settings.TILEHEIGHT/2;
                break;
            case 90:
                yOffset -= Settings.TILEHEIGHT;
                break;
            case 135:
                xOffset -= Settings.TILEWIDTH/4;
                yOffset -= Settings.TILEHEIGHT/2;
                break;
            case 225:
                xOffset -= Settings.TILEWIDTH/4;
                yOffset += Settings.TILEHEIGHT/2;
                break;
            case 270:
                yOffset += Settings.TILEHEIGHT;
                break;
            case 315:
                xOffset += Settings.TILEWIDTH/4;
                yOffset += Settings.TILEHEIGHT/2;
                break;
            default:
                break;
        }
    }

    //Every time you change the offset of x or y keepcameronMap makes sure you didnt cross the boundary
    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
        keepCameraOnMap();
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
        keepCameraOnMap();
    }

    public int getOriginalXOffset() {
        return originalXOffset;
    }

    public void setOriginalXOffset(int originalXOffset) {
        this.originalXOffset = originalXOffset;
    }

    public int getGetOriginalYOffset() {
        return getOriginalYOffset;
    }

    public void setGetOriginalYOffset(int getOriginalYOffset) {
        this.getOriginalYOffset = getOriginalYOffset;
    }

    @Override
    public void update() {
        //set the location to the player's location so the camera can be updated of player position
    }

    @Override
    public void remove() {

    }

    @Override
    public String toString() {
        return "Camera{" +
                "yOffset=" + yOffset +
                ", xOffset=" + xOffset +
                ", gameHeight=" + gameHeight +
                ", gameWidth=" + gameWidth +
                '}';
    }
}
