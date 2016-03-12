package View.Views;

import Model.GameObject.MobileObjects.Entities.Characters.Character;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.GameObject.MobileObjects.Entities.Pet;
import Model.GameObject.MobileObjects.MobileObject;
import Model.GameObject.MobileObjects.ViewLocation;
import Model.Location;
import Utilities.*;
import Utilities.MobileObjectUtilities.MobileObjectEnum;
import View.ViewUtilities.Renderable;
import View.ViewUtilities.ViewModules.ViewModule;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Joshua Kegley on 3/5/2016.
 */
public class MobileObjectView implements Renderable, Observer {

    private MobileObject entity;
    private ArrayList<BufferedImage> sprites;
    private Location location;

    private int movement;
    private int active;
    private int width;
    private int height;


    private ViewLocation viewLocation;
    private float goalX, goalY;

    public MobileObjectView(MobileObject entity, BufferedImage sprite) {
        ArrayList<BufferedImage> temp = new ArrayList<>();
        temp.add(sprite);
        entity.addObserver(this);
        this.entity = entity;
        goalX = Utilities.calculateTileCenterXLocation(entity.getX(), entity.getY());
        goalY = Utilities.calculateTileCenterYLocation(entity.getX(), entity.getY());
        this.sprites = temp;
        this.location = entity.getLocation();
        this.viewLocation = entity.getViewLocation();
        this.movement = entity.getMovement();
        this.width = sprites.get(0).getWidth();
        this.height = sprites.get(0).getHeight();
        System.out.println(sprites.size());
        this.active = 3;
        this.movement = entity.getMovement();
    } // end constructor

    public MobileObjectView(MobileObject entity, ArrayList<BufferedImage> sprites) {
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
        this.active = 3;
        this.movement = entity.getMovement();
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
       if(entity instanceof Character && ((Character)entity).isDead()) {
           return;
       }
        tween();
        g.drawImage(sprites.get(active),
                (int)viewLocation.getX() - cameraXOffset - (Settings.TILEWIDTH / (2*2)),
                (int)viewLocation.getY() - cameraYOffset - (Settings.TILEHEIGHT / (2*2)),
                Settings.PLAYERWIDTH,
                Settings.PLAYERHEIGHT,
                null
        );
        if(!(entity instanceof Player) && !(entity instanceof Pet)){

            ViewModule.renderHealthBox(g,
                    ((Character)entity).getStats().getLife(),
                    ((Character)entity).getStats().getBaseLife(),
                    (int)viewLocation.getX() - cameraXOffset - (Settings.TILEWIDTH / (2*2)),
                    (int)viewLocation.getY() - cameraYOffset - (Settings.TILEWIDTH / (2*2))
            );
        }
    }

    @Override
    public void update() {
            setDirection(entity.getDir());
            goalX = Utilities.calculateTileCenterXLocation(entity.getX(), entity.getY());
            goalY = Utilities.calculateTileCenterYLocation(entity.getX(), entity.getY());
    }

    @Override
    public void remove() {

    }

    public void setDirection(int degrees){
        if(degrees == Settings.NORTH){
            this.active = 0;
        }
        else if(degrees == Settings.NE){
            this.active = 1;
        }
        else if(degrees == Settings.SE){
            this.active = 2;
        }
        else if(degrees == Settings.SOUTH){
            this.active = 3;
        }
        else if(degrees == Settings.SW){
            this.active = 4;
        }
        else if(degrees == Settings.NW){
            this.active = 5;
        }
    }
}

//Move from oldX, OldY (Starts at center of a tile),