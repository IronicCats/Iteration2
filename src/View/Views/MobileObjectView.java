package View.Views;

import Model.GameObject.MobileObjects.Entities.Characters.Character;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.GameObject.MobileObjects.Entities.Entity;
import Model.GameObject.MobileObjects.Entities.Pet;
import Model.GameObject.MobileObjects.MobileObject;
import Model.GameObject.MobileObjects.Projectile;
import Model.GameObject.MobileObjects.Vehicle;
import Model.GameObject.MobileObjects.ViewLocation;
import Model.Location;
import Utilities.Observer;
import Utilities.Settings;
import Utilities.Utilities;
import View.ViewUtilities.Graphics.Assets;
import View.ViewUtilities.Renderable;
import View.ViewUtilities.ViewModules.ViewModule;

import javax.sound.midi.SysexMessage;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Joshua Kegley on 3/5/2016.
 */
public class MobileObjectView implements Renderable, Observer {

    private MobileObject entity;
    private ArrayList<BufferedImage> sprites;
    private Location location;

    private int movement;
    private int active;
    private int current;
    private boolean incrementing = true;
    private int width;
    private int height;


    private ViewLocation viewLocation;
    private float goalX, goalY;

    private ArrayList<ArrayList<BufferedImage>> playerAnimations;

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
        this.active = 3;
        this.current = 0;
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
        this.active = 3;
        this.current = 0;
        this.movement = entity.getMovement();
        if(entity instanceof Player) {
            if(((Player) entity).getOccupation().getName() == "Smasher"){
                playerAnimations = Assets.SMASHERSEQUENCE;

            }else if(((Player) entity).getOccupation().getName() == "Summoner") {
                playerAnimations = Assets.SUMMONERSEQUENCE;

            }else {
                playerAnimations = Assets.SNEAKERSEQUENCE;
            }
        }
    }

    public void pause() {
        current = 0;
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
        if (goalX == viewLocation.getX() && goalY == viewLocation.getY()) {
            entity.setCanMove(true);
            pause();
            if(entity instanceof Projectile){
                entity.deregister();
                entity.getMap().getMobileObjects().remove(entity);
            }
            return;
        }

        entity.setCanMove(false);
        if (entity instanceof Player){
            //System.out.println("Movement Displayed" + sinWave());
        }
        if (goalX != viewLocation.getX()) {
            if (goalX > viewLocation.getX()) {
                viewLocation.setX(Math.min(viewLocation.getX() + (int)(movement*(7/4d)), goalX));
            }
            if (goalX < viewLocation.getX()) {
                viewLocation.setX(Math.max(viewLocation.getX() - (int)(movement*(7/4d)), goalX));
            }
        }
        if(goalX == viewLocation.getX()) {
            if (goalY != viewLocation.getY()) {
                if (goalY > viewLocation.getY()) {
                    viewLocation.setY(Math.min(viewLocation.getY() + (int)(movement*(7/4d)), goalY));
                }
                if (goalY < viewLocation.getY()) {
                    viewLocation.setY(Math.max(viewLocation.getY() - (int)(movement*(7/4d)), goalY));
                }
            }
        }else {
            if (goalY != viewLocation.getY()) {
                if (goalY > viewLocation.getY()) {
                    viewLocation.setY(Math.min(viewLocation.getY() + movement, goalY));
                }
                if (goalY < viewLocation.getY()) {
                    viewLocation.setY(Math.max(viewLocation.getY() - movement, goalY));
                }
            }
        }
    }

    public void render(Graphics g, int cameraXOffset, int cameraYOffset) {
        tween();
        //RENDER THE VEHICLE BIGGER
        if(entity instanceof Vehicle) {
            g.drawImage(sprites.get(active),
                    (int) viewLocation.getX() - cameraXOffset - (Settings.TILEWIDTH / 2 ),
                    (int) viewLocation.getY() - cameraYOffset - (Settings.TILEHEIGHT / 2 ),
                    Settings.TILEWIDTH,
                    Settings.TILEWIDTH,
                    null
            );

        }else if(entity instanceof Player){
            g.drawImage(sprites.get(sinWave()),
                    (int) viewLocation.getX() - cameraXOffset - (Settings.TILEWIDTH / (2 * 2)),
                    (int) viewLocation.getY() - cameraYOffset - (Settings.TILEHEIGHT / (2 * 2)),
                    Settings.PLAYERWIDTH,
                    Settings.PLAYERHEIGHT,
                    null
            );
        }else {
            g.drawImage(sprites.get(active),
                    (int) viewLocation.getX() - cameraXOffset - (Settings.TILEWIDTH / (2 * 2)),
                    (int) viewLocation.getY() - cameraYOffset - (Settings.TILEHEIGHT / (2 * 2)),
                    Settings.PLAYERWIDTH,
                    Settings.PLAYERHEIGHT,
                    null
            );
        }


        if (!(entity instanceof Player) && !(entity instanceof Pet) && !(entity instanceof Vehicle) && !(entity instanceof Projectile)) {

            ViewModule.renderHealthBox(g,
                    ((Character) entity).getStats().getLife(),
                    ((Character) entity).getStats().getBaseLife(),
                    (int) viewLocation.getX() - cameraXOffset - (Settings.TILEWIDTH / (2 * 2)),
                    (int) viewLocation.getY() - cameraYOffset - (Settings.TILEWIDTH / (2 * 2))
            );
        }
    }

    @Override
    public void update() {
        movement = entity.getMovement();
        setDirection(entity.getDir());
        goalX = Utilities.calculateTileCenterXLocation(entity.getX(), entity.getY());
        goalY = Utilities.calculateTileCenterYLocation(entity.getX(), entity.getY());
    }

    @Override
    public void remove() {

    }

    public void setDirection(int degrees) {
        if (degrees == Settings.NORTH) {
            this.active = 1;
        } else if (degrees == Settings.NE) {
            this.active = 0;

        } else if (degrees == Settings.SE) {
            this.active = 5;

        } else if (degrees == Settings.SOUTH) {
            this.active = 4;

        } else if (degrees == Settings.SW) {
            this.active = 3;
        } else if (degrees == Settings.NW) {
            this.active = 2;
        }
        if(playerAnimations != null) {
            sprites = playerAnimations.get(active);
        }
    }

    public int sinWave() {
        if(incrementing && current < 3){
            ++current;
        }else if(!incrementing && current > 0) {
            --current;
        }else if (current == 3 || current == 0) {
            incrementing = !incrementing;
        }
        return current;
    }



}


//Move from oldX, OldY (Starts at center of a tile),