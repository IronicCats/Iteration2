package View.Views;

import Model.GameObject.Item.Item;
import Model.Location;
import Utilities.Observer;
import Utilities.Settings;
import View.ViewUtilities.Renderable;
import Utilities.Utilities;

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
                Utilities.calculateHexXLocation(location) - cameraXOffset + (Settings.TILEWIDTH/2 - sprite.getWidth()/2),
                Utilities.calculateHexYLocation(location) - cameraYOffset + (Settings.TILEHEIGHT/2 - sprite.getHeight()/2),
                sprite.getWidth(),
                sprite.getHeight(),
                null
        );
    }

}
