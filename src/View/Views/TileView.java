package View.Views;

import Model.GameObject.AreaEffect.AreaEffectEnum;
import Model.GameObject.Decal.Decal;
import Model.GameObject.Decal.DecalEnum;
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
    private DecalView decalView;

    public TileView(Tile tile, BufferedImage sprite) {
        this.tile = tile;
        tile.addObserver(this);
        this.sprite = sprite;
        this.location = tile.getLocation();
        if(tile.getHasAreaEffect()){
            if(tile.getAreaEffectEnum().equals(AreaEffectEnum.DAMAGE))
            {
                decalView = new DecalView(new Decal(location, DecalEnum.FIRE), Assets.HEALTH_POTION);
            }
            else if(tile.getAreaEffectEnum().equals(AreaEffectEnum.HEAL))
            {
                decalView = new DecalView(new Decal(location, DecalEnum.REDCROSS), Assets.HEALTH_POTION);
            }
            else if(tile.getAreaEffectEnum().equals(AreaEffectEnum.DEATH))
            {
                decalView = new DecalView(new Decal(location, DecalEnum.SKULLANDCROSSBONES),Assets.HEALTH_POTION );
            }
            else if(tile.getAreaEffectEnum().equals(AreaEffectEnum.LEVELUP))
            {
                decalView = new DecalView(new Decal(location, DecalEnum.GOLDSTAR),Assets.HEALTH_POTION );
            }
        }
    }

    public BufferedImage getSprite(){ return sprite; }

    @Override
    public void update() {

    }

    @Override
    public void remove() {

    }

    @Override
    public void render(Graphics g) {

    }

    public void render(Graphics g, int xOffset, int yOffset, Location playerLocation) {

        g.drawImage(sprite, xOffset, yOffset, Settings.TILEWIDTH, Settings.TILEHEIGHT, null);

        if(Utilities.outOfSite(playerLocation, this.location)) {//tile.visited
            //g.drawImage(Assets.FOGTILE, xOffset, yOffset, Settings.TILEWIDTH, Settings.TILEHEIGHT, null);
        }

        if(true){
            decalView.render(g, xOffset, yOffset);
        }

    }

}
