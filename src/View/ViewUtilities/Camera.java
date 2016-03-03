package View.ViewUtilities;

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
    private float xOffset, yOffset;
    private Map map;
    private Location Playerlocation; //This will keep track of the players location.
                                     // The Camera will also need a Player object. 

    public Camera(int gameWidth, int gameHeight, Map map) {
        this.gameHeight = gameHeight;
        this.gameWidth = gameWidth;
        this.map = map;
        yOffset = 100;
        xOffset = 100;
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
        else if (yOffset > ((map.getHeight() / 2 * Settings.TILEHEIGHT) - gameHeight) + 20) {
            yOffset = (map.getHeight() / 2 * Settings.TILEHEIGHT) - gameHeight + 20;

        }
    }

    //once we make the player we need to uncomment the center on player method

    /*public void centerOnPlayer(Player player) {
        xOffset = player.getLocation().getPixelX() - gameWidth/2 + player.getWidth()/2;
        yOffset = player.getLocation().getPixelY() - gameHeight/2 + player.getHeight()/2;
        keepCameraonMap();
    }*/

    //Every time you change the offset of x or y keepcameronMap makes sure you didnt cross the boundary
    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
        keepCameraonMap();
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
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
