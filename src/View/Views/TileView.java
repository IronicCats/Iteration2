package View.Views;

import Model.GameObject.AreaEffect.AreaEffectEnum;
import Model.GameObject.MobileObjects.ViewLocation;
import Model.Location;
import Model.Map.Tile;
import Utilities.Observer;
import Utilities.Settings;
import Utilities.Utilities;
import View.ViewUtilities.Graphics.Assets;
import View.ViewUtilities.Renderable;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Aidan on 3/1/2016.
 */
public class TileView implements Observer, Renderable {

    private Tile tile;
    private BufferedImage sprite;
    Location location;
    private ViewLocation viewLocation;
    private DecalView decalView;

    public TileView(Tile tile, BufferedImage sprite) {
        this.tile = tile;
        tile.addObserver(this);
        this.sprite = sprite;
        this.location = tile.getLocation();
        this.viewLocation = new ViewLocation(location.getX(), location.getY());
        if(tile.getHasAreaEffect()){
            if(tile.getAreaEffectEnum() == AreaEffectEnum.LEVELUP){
                decalView = new DecalView(tile.getAreaEffect(), Assets.STAR);
            }
            else if(tile.getAreaEffectEnum() == AreaEffectEnum.DEATH){
                decalView = new DecalView(tile.getAreaEffect(), Assets.SKULL);
            }
            else if(tile.getAreaEffectEnum() == AreaEffectEnum.DEATH){
                decalView = new DecalView(tile.getAreaEffect(), Assets.SKULL);
            }
        }

    }

    public BufferedImage getSprite(){ return sprite; }

    @Override
    public void update() {
        System.out.println("Updating");
    }

    @Override
    public void remove() {

    }

    @Override
    public void render(Graphics g) {

    }

    public void render(Graphics g, int xOffset, int yOffset, Location playerLocation) {

        g.drawImage(sprite, xOffset, yOffset, Settings.TILEWIDTH, Settings.TILEHEIGHT, null);

        /*if(Utilities.outOfSite(new ViewLocation(playerLocation.getX(), playerLocation.getY()), this.viewLocation)) {//tile.visited
            //System.out.print("Here");
            g.drawImage(Assets.FOGTILE, xOffset, yOffset, Settings.TILEWIDTH, Settings.TILEHEIGHT, null);
        }*/

        System.out.println(decalView);
        if(tile.getHasAreaEffect() && decalView != null){
            decalView.render(g, xOffset, yOffset);
        }


    }

}
