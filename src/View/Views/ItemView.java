package View.Views;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Interactable;
import Model.GameObject.Item.Items.Obstacle;
import Model.Inventory.Pack;
import Model.Location;
import Utilities.Observer;
import Utilities.Settings;
import View.ViewUtilities.Renderable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


/**
 * Created by Joshua Kegley on 2/29/2016.
 */
public class ItemView implements Observer, Renderable {

    private Item item;
    private BufferedImage sprite;
    private ArrayList<BufferedImage> sprites;
    private Location location;
    private BufferedImage active;


    public ItemView(Item item, BufferedImage sprite) {
        this.item = item;
        this.sprite = sprite;
        this.location = item.getLocation();
        item.addObserver(this);
    }

    public ItemView(Item item, ArrayList<BufferedImage> sprites) {
        this.item = item;
        this.sprites = sprites;
        this.location = item.getLocation();
        active = this.sprites.get(0);
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
        if(item instanceof Obstacle) {
            g.drawImage(sprite,
                    cameraXOffset + (Settings.TILEWIDTH / 2 - Settings.DEFAULTITEMWIDTH),
                    cameraYOffset + (Settings.TILEHEIGHT / 2 - Settings.DEFAULTITEMHEIGHT),
                    Settings.DEFAULTITEMWIDTH * 2,
                    Settings.DEFAULTITEMHEIGHT * 2,
                    null
            );
            return;
        }

        if(item instanceof Interactable) {
            g.drawImage(active,
                    cameraXOffset + (Settings.TILEWIDTH / 2 - Settings.DEFAULTITEMWIDTH),
                    cameraYOffset + (Settings.TILEHEIGHT / 2 - Settings.DEFAULTITEMHEIGHT),
                    Settings.DEFAULTITEMWIDTH * 2,
                    Settings.DEFAULTITEMHEIGHT * 2,
                    null
            );
            return;
        }

        g.drawImage(sprite,
                cameraXOffset + (Settings.TILEWIDTH / 2 - Settings.DEFAULTITEMWIDTH / 2),
                cameraYOffset + (Settings.TILEHEIGHT / 2 - Settings.DEFAULTITEMHEIGHT / 2),
                Settings.DEFAULTITEMWIDTH,
                Settings.DEFAULTITEMHEIGHT,
                null
        );
    }

    public void render(Graphics g, int placeX, int placeY, int sizeX, int sizeY) {
        //draw it
        g.drawImage(sprite, placeX, placeY, sizeX, sizeY, null);
    }

    public void adjustView(){
        active = sprites.get(1);
    }

}
