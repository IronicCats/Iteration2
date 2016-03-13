package View.Views;

import Model.GameObject.AreaEffect.AreaEffect;
import Model.GameObject.AreaEffect.TeleportAreaEffect;
import Model.Location;
import Utilities.Observer;
import Utilities.Settings;
import View.ViewUtilities.Renderable;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by mazumderm on 3/5/2016.
 */
public class DecalView implements Observer, Renderable {
    private AreaEffect areaEffect;
    private TeleportAreaEffect teleportAreaEffect;
    private BufferedImage sprite;
    private Location location;


    public DecalView(AreaEffect areaEffect, BufferedImage sprite) {
        this.areaEffect = areaEffect;
        this.teleportAreaEffect = null;
        this.sprite = sprite;
        this.location = areaEffect.getLocation();
        areaEffect.addObserver(this);
    }

    public DecalView(TeleportAreaEffect teleportAreaEffect, BufferedImage sprite){
        this.areaEffect = null;
        this.teleportAreaEffect = teleportAreaEffect;
        this.sprite = sprite;
        this.location = teleportAreaEffect.getLocation();
        teleportAreaEffect.addObserver(this);
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    @Override
    public void update() {
        if(areaEffect != null){
            location = areaEffect.getLocation();
        }
        else if(teleportAreaEffect != null){
            location = teleportAreaEffect.getLocation();
        }
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
                cameraXOffset + (Settings.TILEWIDTH / 2 - Settings.DEFAULTITEMWIDTH / 2),
                cameraYOffset + (Settings.TILEHEIGHT / 2 - Settings.DEFAULTITEMHEIGHT / 2),
                Settings.DEFAULTITEMWIDTH,
                Settings.DEFAULTITEMHEIGHT,
                null
        );
    }
}
