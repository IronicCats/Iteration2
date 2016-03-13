package View.Views;

import Model.Location;
import Model.Map.Map;
import Utilities.Observer;
import Utilities.Settings;
import Utilities.Utilities;
import View.ViewUtilities.Renderable;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

import java.awt.*;

/**
 * Created by Aidan on 3/2/2016.
 */

public class MapView implements Observer, Renderable {

    private Map map;
    private TileView tileViews[][];

    public MapView(Map map, TileView tileViews[][]) {
        this.map = map;
        this.tileViews = tileViews;
        map.addObserver(this);
    }

    @Override
    public void render(Graphics g) {

    }

    public void render(Graphics g, int xOffset, int yOffset, Location playerLocation) {
        int visibleX = Settings.GAMEWIDTH/Settings.TILEWIDTH  + 2;
        int visibleY = Settings.GAMEHEIGHT/Settings.TILEHEIGHT - 2;
        for (int y = 0; y < map.getHeight(); ++y) {
            for (int x = 0; x < map.getWidth(); ++x) {  //you have to shiftover all tiles to the left
                //ONLY RENDER THINGS VISIBLE IN THE VIEW
                if((x <= playerLocation.getX() && x >= (playerLocation.getX() - (visibleX)))
                        ||
                        (x >= playerLocation.getX() && x <= (playerLocation.getX() + (visibleX)))) {
                    if((y <= playerLocation.getY() && y >= (playerLocation.getY() - (visibleY)))
                            ||
                            (y >= playerLocation.getY() && y <= (playerLocation.getY() + (visibleY)))) {
                        tileViews[x][y].renderTile(g,
                                ((Utilities.calculateHexXLocation(x, y)) - xOffset),
                                (Utilities.calculateHexYLocation(x, y) - yOffset),
                                playerLocation
                        );
                        tileViews[x][y].renderNonMobile(g,
                                ((Utilities.calculateHexXLocation(x, y)) - xOffset),
                                (Utilities.calculateHexYLocation(x, y) - yOffset),
                                playerLocation
                        );
                        g.setColor(Color.WHITE);
                        int textW = Utilities.getFontWidth(g, new Font("Arial", Font.PLAIN, 12), "0,0");

                        g.drawString(x + "," + y,
                                ((Utilities.calculateHexXLocation(x, y)) - xOffset) + Settings.TILEWIDTH / 2 - textW / 2,
                                ((Utilities.calculateHexYLocation(x, y) - yOffset)) + 10
                        );
                    }
                }
            }
        }
        for (int y = 0; y < map.getHeight(); ++y) {
            for (int x = 0; x < map.getWidth(); ++x) {  //you have to shiftover all tiles to the left
                if((x <= playerLocation.getX() && x >= (playerLocation.getX() - (visibleX)))
                        ||
                        (x >= playerLocation.getX() && x <= (playerLocation.getX() + (visibleX)))) {
                    if ((y <= playerLocation.getY() && y >= (playerLocation.getY() - (visibleY)))
                            ||
                            (y >= playerLocation.getY() && y <= (playerLocation.getY() + (visibleY)))) {

                        //DO THE RENDER
                        tileViews[x][y].mobileObjects(g,
                                ((Utilities.calculateHexXLocation(x, y)) - xOffset),
                                (Utilities.calculateHexYLocation(x, y) - yOffset),
                                playerLocation
                        );
                        if (Settings.FOG) {
                            tileViews[x][y].renderFog(g,
                                    ((Utilities.calculateHexXLocation(x, y)) - xOffset),
                                    (Utilities.calculateHexYLocation(x, y) - yOffset),
                                    playerLocation
                            );
                        }
                    }
                }
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
