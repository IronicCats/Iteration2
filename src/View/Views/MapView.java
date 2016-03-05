package View.Views;

import Model.Map.Map;
import Model.Map.Tile;
import Utilities.Settings;
import View.ViewUtilities.Camera;
import View.ViewUtilities.Renderable;

import java.awt.*;
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
            int totaloffsetX = 0;                          //adds total offset because anytime you shiftover left
            for (int x = 0; x < map.getWidth(); ++x) {  //you have to shiftover all tiles to the left
                int offsetX = Settings.TILEWIDTH / 4; //a quarter of a tile
                int offsetY = Settings.TILEHEIGHT / 2; //a half of a tile
                if(x%2 == 0) {
                    offsetY = 0;  //dont offset if x is even
                    offsetX = 0;
                    if(x != 0)
                        totaloffsetX += Settings.TILEWIDTH / 2; //offset if x is odd
                }
                System.out.print(x + ": " + (x * Settings.TILEWIDTH - offsetX - totaloffsetX));
                System.out.println(", " + y + ": " + (y * Settings.TILEHEIGHT + offsetY));

                tileViews[x][y].render(g,
                        (x * Settings.TILEWIDTH - xOffset - offsetX - totaloffsetX),
                        (y * Settings.TILEHEIGHT - yOffset + offsetY)
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
