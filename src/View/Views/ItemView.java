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
        System.out.println("Drawing Item");
        g.drawImage(sprite, Settings.GAMEWIDTH /2 + 25, Settings.GAMEHEIGHT /2 + 25, 50, 50, null);
    }


}
