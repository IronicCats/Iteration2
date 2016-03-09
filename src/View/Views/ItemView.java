package View.Views;

import Model.GameObject.Item.Item;
import Model.Location;
import Utilities.Observer;
import Utilities.Settings;
import View.ViewUtilities.Renderable;

import java.awt.*;
import java.awt.image.BufferedImage;


/**
 * Created by Joshua Kegley on 2/29/2016.
 */
public class ItemView implements Observer, Renderable {

    private Item item;
    private BufferedImage sprite;
    private Location location;


    public ItemView(Item item, BufferedImage sprite) {
        this.item = item;
        this.sprite = sprite;
        this.location = item.getLocation();
        item.addObserver(this);
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    @Override
    public void update() {
        location = item.getLocation();
    }

    @Override
    public void remove() {
        //delete me
    }


    @Override
    public void render(Graphics g) {
        //draw it
        g.drawImage(sprite, location.getX(), location.getY(), 50, 50, null);
    }


    public void render(Graphics g, int cameraXOffset, int cameraYOffset) {
        //draw it
        g.drawImage(sprite,
                cameraXOffset + (Settings.TILEWIDTH/ 2 - Settings.DEFAULTITEMWIDTH /2),
                cameraYOffset + (Settings.TILEHEIGHT/ 2 - Settings.DEFAULTITEMHEIGHT/2),
                Settings.DEFAULTITEMWIDTH,
                Settings.DEFAULTITEMHEIGHT,
                null
        );
    }

}
