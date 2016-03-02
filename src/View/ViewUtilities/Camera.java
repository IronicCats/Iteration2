package View.ViewUtilities;

import Model.Location;
import Model.Map.Map;
import Model.Map.Tile;
import Utilities.Observer;

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

    public void keepCameraonMap() {
        if (xOffset < 0) {
            setxOffset(0);
        } else if (xOffset > (map.getWidth() / 2 * (Tile.TILEWIDTH) - gameWidth)) {
            xOffset = map.getWidth() / 2 * (Tile.TILEWIDTH) - gameWidth;
        }
        if (yOffset < 0) {
            yOffset = 0;
        }
        else if (yOffset > ((map.getHeight() / 2 * Tile.TILEHEIGHT) - gameHeight) + 20) {
            yOffset = (map.getHeight() / 2 * Tile.TILEHEIGHT) - gameHeight + 20;

        }
    }

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
