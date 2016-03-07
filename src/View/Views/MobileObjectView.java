package View.Views;

import Model.GameObject.MobileObjects.Entities.Entity;
import Model.GameObject.MobileObjects.ViewLocation;
import Model.Location;
import Utilities.*;
import View.ViewUtilities.Renderable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Joshua Kegley on 3/5/2016.
 */
public class MobileObjectView implements Renderable, Observer {

    private Entity entity;
    private ArrayList<BufferedImage> sprites;
    private Location location;

    private int movement;
    private int active = 0;
    private int width;
    private int height;


    private ViewLocation viewLocation;
    private float goalX, goalY;

    public MobileObjectView(Entity entity, ArrayList<BufferedImage> sprites) {
        entity.addObserver(this);
        goalX = Utilities.calculateTileCenterXLocation(entity.getX(), entity.getY());
        goalY = Utilities.calculateTileCenterYLocation(entity.getX(), entity.getY());
        this.entity = entity;
        this.sprites = sprites;
        this.location = entity.getLocation();
        this.viewLocation = entity.getViewLocation();
        this.movement = entity.getMovement();
        this.width = sprites.get(0).getWidth();
        this.height = sprites.get(0).getHeight();
        System.out.println(sprites.size());
        this.active = 0;
        this.movement = 10;
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

    public void tween() {
        if(goalX == viewLocation.getX() && goalY == viewLocation.getY()) {
            entity.setCanMove(true);
            return;
        }
        entity.setCanMove(false);
        if(goalX != viewLocation.getX()) {
            if(goalX > viewLocation.getX()){
                viewLocation.setX(Math.min(viewLocation.getX() + movement, goalX));
            }
            if(goalX < viewLocation.getX()){
                viewLocation.setX(Math.max(viewLocation.getX() - movement, goalX));
            }
        }
        if(goalY != viewLocation.getY()) {
            if(goalY > viewLocation.getY()){
                viewLocation.setY(Math.min(viewLocation.getY() + movement, goalY));
            }
            if(goalY < viewLocation.getY()){
                viewLocation.setY(Math.max(viewLocation.getY() - movement, goalY));
            }
        }
    }

    public void render(Graphics g, int cameraXOffset, int cameraYOffset) {
        tween();
        g.drawImage(sprites.get(active),
                (int)viewLocation.getX() - cameraXOffset - (Settings.TILEWIDTH / (2*2)),
                (int)viewLocation.getY() - cameraYOffset - (Settings.TILEHEIGHT / (2*2)),
                Settings.PLAYERWIDTH,
                Settings.PLAYERHEIGHT,
                null
        );
    }

    @Override
    public void update() {
            goalX = Utilities.calculateTileCenterXLocation(entity.getX(), entity.getY());
            goalY = Utilities.calculateTileCenterYLocation(entity.getX(), entity.getY());
    }

    @Override
    public void remove() {

    }
}

//Move from oldX, OldY (Starts at center of a tile),