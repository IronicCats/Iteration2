package View.Views;

import Model.GameObject.Decal.Decal;
import Model.Location;
import Utilities.Observer;
import Utilities.Settings;
import View.ViewUtilities.Renderable;
import Utilities.Utilities;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by TARIT on 3/5/2016.
 */
public class DecalView implements Observer, Renderable {
    private Decal decal;
    private BufferedImage sprite;
    private Location location;


    public DecalView(Decal decal, BufferedImage sprite) {
        this.decal = decal;
        this.sprite = sprite;
        this.location = decal.getLocation();
        decal.addObserver(this);
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    @Override
    public void update() {
        location = decal.getLocation();
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
                Utilities.calculateHexXLocation(location) - cameraXOffset + (Settings.TILEWIDTH/2 - 16),
                Utilities.calculateHexYLocation(location) - cameraYOffset + (Settings.TILEHEIGHT/2 - 16),
                32,
                32,
                null
        );
    }
}
