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
        int startX = Math.max(0,xOffset / Settings.TILEWIDTH);
        int startY = Math.max(0, yOffset / Settings.TILEHEIGHT);
        int endX = Math.min(Settings.GAMEHEIGHT, xOffset / Settings.TILEWIDTH);
        int endY = Math.min(Settings.GAMEHEIGHT, yOffset / Settings.TILEHEIGHT);
        int totaloffsetX = 0;
        int totaloffsetY = 0;
        for(int y = 0; y <  Settings.GAMEHEIGHT; ++y){
            totaloffsetX = 0;
            for (int x = 0; x < Settings.GAMEWIDTH; ++x) {
                int offsetX = 16;
                int offsetY = 32;
                if(x%2 == 0) {
                    offsetY = 0;
                    offsetX = 0;
                    if(x != 0)
                        totaloffsetX += 2 * 16;
                }
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
