package View.Views;

import Model.Location;
import Model.Map.Tile;
import Utilities.Observer;
import Utilities.Settings;
import View.ViewUtilities.Renderable;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Aidan on 3/1/2016.
 */
public class TileView implements Observer, Renderable {

    private Tile tile;
    private BufferedImage sprite;
    Location location;

    public TileView(Tile tile, BufferedImage sprite) {
        this.tile = tile;
        tile.addObserver(this);
        this.sprite = sprite;
        this.location = tile.getLocation();
    }

    public BufferedImage getSprite(){ return sprite; }

    @Override
    public void update() {

    }

    @Override
    public void remove() {

    }

    @Override
    public void render(Graphics g) {

    }

    public void render(Graphics g, int xOffset, int yOffset) {
        g.drawImage(sprite, xOffset, yOffset, Settings.TILEWIDTH, Settings.TILEHEIGHT, null);
    }

}
