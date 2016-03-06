package View.Views;

import Model.Map.Map;
import Model.Map.Tile;
import Utilities.Settings;
import Utilities.Utilities;
import View.ViewUtilities.Camera;
import View.ViewUtilities.Renderable;

import java.awt.*;
import java.util.Set;

import Utilities.Observer;

/**
 * Created by Aidan on 3/2/2016.
 */

public class MapView implements Observer, Renderable {

    private Map map;
    private TileView tileViews[][];

    public MapView(Map map,TileView tileViews[][]){
        this.map = map;
        this.tileViews = tileViews;
        map.addObserver(this);
    }

    @Override
    public void render(Graphics g) {

    }

    public void render(Graphics g, int xOffset, int yOffset) {
        for(int y = 0; y <  map.getHeight(); ++y){
            for (int x = 0; x < map.getWidth(); ++x) {  //you have to shiftover all tiles to the left
                tileViews[x][y].render(g,
                        ((Utilities.calculateHexXLocation(x, y)) - xOffset),
                        (Utilities.calculateHexYLocation(x, y) - yOffset)
                );
                g.setColor(Color.WHITE);
                g.drawString(x + "," + y,
                        ((Utilities.calculateHexXLocation(x, y)) - xOffset) + Settings.TILEWIDTH/2,
                        ((Utilities.calculateHexYLocation(x, y) - yOffset)  + Settings.TILEHEIGHT/2)
                );
            }
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void remove() {

    }

}
