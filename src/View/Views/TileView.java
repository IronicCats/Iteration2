package View.Views;

import Model.GameObject.AreaEffect.AreaEffectEnum;
import Model.GameObject.Decal.Decal;
import Model.GameObject.Decal.DecalEnum;
import Model.Location;
import Model.Map.Tile;
import Utilities.Observer;
import Utilities.Settings;
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
    private Decal decal;

    public TileView(Tile tile, BufferedImage sprite) {
        this.tile = tile;
        tile.addObserver(this);
        this.sprite = sprite;
        this.location = tile.getLocation();
        if(tile.getHasAreaEffect()){
            if(tile.getAreaEffectEnum().equals(AreaEffectEnum.DAMAGE))
            {
                decal = new Decal(location, DecalEnum.FIRE);
            }
            else if(tile.getAreaEffectEnum().equals(AreaEffectEnum.HEAL))
            {
                decal = new Decal(location, DecalEnum.REDCROSS);
            }
            else if(tile.getAreaEffectEnum().equals(AreaEffectEnum.DEATH))
            {
                decal = new Decal(location, DecalEnum.SKULLANDCROSSBONES);
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

    public void render(Graphics g, int xOffset, int yOffset) {

        g.drawImage(sprite, xOffset, yOffset, Settings.TILEWIDTH, Settings.TILEHEIGHT, null);
        /*
        if(tile is unseen && out of sight) {
            draw black hex;
        }else if( out of sight ) {
            draw transparent black over tile
        }else {
           drawTile;
        }


         */
        if(tile.getHasAreaEffect()){

        }
    }

}
