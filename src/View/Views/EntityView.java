package View.Views;

import Model.GameObject.MobileObjects.Entities.Entity;
import Model.Location;
import Utilities.*;
import View.ViewUtilities.Renderable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Joshua Kegley on 3/5/2016.
 */
public class EntityView implements Renderable, Observer {

    private Entity entity;
    private ArrayList<BufferedImage> sprites;
    private Location location;
    private int movement;
    private int active = 0;
    private int width;
    private int height;
    public EntityView(Entity entity, ArrayList<BufferedImage> sprites) {
        entity.addObserver(this);
        this.entity = entity;
        this.sprites = sprites;
        this.location = entity.getLocation();
        this.movement = entity.getMovement();
        this.width = sprites.get(0).getWidth();
        this.height = sprites.get(0).getHeight();
        System.out.println(sprites.size());
        this.active = 0;
    }

    public void pause() {
        active = 0;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(sprites.get(active),
                width,
                height,
                0,
                0,
                null
        );
    }


    public void render(Graphics g, int cameraXOffset, int cameraYOffset) {
        g.drawImage(sprites.get(active),
                Utilities.calculateHexXLocation(location) - cameraXOffset + (Settings.TILEWIDTH/2 - (Settings.TILEWIDTH / (2*2))),
                Utilities.calculateHexYLocation(location) - cameraYOffset + (Settings.TILEHEIGHT/2 -(Settings.TILEHEIGHT / (2*2))),
                Settings.PLAYERWIDTH,
                Settings.PLAYERHEIGHT,
                null
        );
    }

    @Override
    public void update() {
        location = entity.getLocation();
    }

    @Override
    public void remove() {

    }
}

