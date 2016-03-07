package View.ViewUtilities;

import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.Location;
import Model.Map.Map;
import Model.Map.Tile;
import Utilities.Observer;
import Utilities.Settings;

/**
 * Created by Aidan on 3/2/2016.
 */
public class Camera implements Observer {
    private int gameWidth, gameHeight;
    private int xOffset, yOffset;
    private Map map;
    private Location Playerlocation; //This will keep track of the players location.
                                     // The Camera will also need a Player object. 

    public Camera(int gameWidth, int gameHeight, Map map) {
        this.gameHeight = gameHeight;
        this.gameWidth = gameWidth;
        this.map = map;
        yOffset = 0;
        xOffset = 0;
    }
    //This makes sure that the map doesn't go off the screen and start showing blankspace
    public void keepCameraonMap() {
        if (xOffset < 0) {
            setxOffset(0);
        } else if (xOffset > (map.getWidth() / 2 * (Settings.TILEWIDTH) - gameWidth)) {
            xOffset = map.getWidth() / 2 * (Settings.TILEWIDTH) - gameWidth;
        }
        if (yOffset < 0) {
            yOffset = 0;
        }
        else if (yOffset > ((map.getHeight() / 2 * Settings.TILEHEIGHT) - gameHeight)) {
            yOffset = (map.getHeight() / 2 * Settings.TILEHEIGHT) - gameHeight;

        }
    }

    public void centerOnPlayer(Player player) {
        xOffset = player.getLocation().getX() * Settings.TILEWIDTH - gameWidth/2 + Settings.PLAYERWIDTH/2;
        yOffset = player.getLocation().getY() * Settings.TILEHEIGHT - gameHeight/2 + Settings.PLAYERHEIGHT/2;
        keepCameraonMap();
    }

    //Every time you change the offset of x or y keepcameronMap makes sure you didnt cross the boundary
    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
        keepCameraonMap();
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
        keepCameraonMap();
    }

    @Override
    public void update() {
        //set the location to the player's location so the camera can be updated of player position
    }

    @Override
    public void remove() {

    }
}
