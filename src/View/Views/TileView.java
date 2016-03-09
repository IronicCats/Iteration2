package View.Views;

import Model.GameObject.AreaEffect.AreaEffect;
import Model.GameObject.AreaEffect.AreaEffectEnum;
import Model.GameObject.Decal.Decal;
import Model.GameObject.Decal.DecalEnum;
import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Obstacle;
import Model.GameObject.MobileObjects.ViewLocation;
import Model.Location;
import Model.Map.Tile;
import Utilities.AreaEffectUtilities.AreaEffectFactory;
import Utilities.ItemUtilities.ItemFactory;
import Utilities.ItemUtilities.ItemsEnum;
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
    private ItemView itemView;

    public TileView(Tile tile, BufferedImage sprite) {
        this.tile = tile;
        this.itemView = null;
        tile.addObserver(this);
        this.sprite = sprite;
        this.location = tile.getLocation();
        this.viewLocation = new ViewLocation(location.getX(), location.getY());

    }

    public BufferedImage getSprite(){ return sprite; }

    @Override
    public void update() {
        if (tile.hasItems()) {
            Item item;
            if(tile.amountOfItems() > 1) {
                item = tile.getItems().get(0);//IF THERE IS ONLY, USE THE ONLY ONE
                for(Item i: tile.getItems()){
                    if(i instanceof Obstacle){//IF THE ITEM IS AN OBSTACLE, DRAW THAT ONLY
                        item = i;
                    }
                }
                itemView = ItemFactory.makeAsset(ItemsEnum.values()[item.getId()],tile.getItems().get(0));
            }else{
                item = tile.getItems().get(0);//IF THERE IS ONLY, USE THE ONLY ONE

            }
            itemView = ItemFactory.makeAsset(ItemsEnum.values()[item.getId()],tile.getItems().get(0));

        }

        if(tile.getHasAreaEffect()){
            decalView = AreaEffectFactory.makeAsset(new Decal(new Location(1,1),DecalEnum.GOLDSTAR));
        }
    }

    @Override
    public void remove() {

    }

    @Override
    public void render(Graphics g) {

    }

    public void render(Graphics g, int xOffset, int yOffset, Location playerLocation) {



        if(Utilities.outOfSite(new ViewLocation(playerLocation.getX(), playerLocation.getY()), this.viewLocation)) {//tile.visited
            //System.out.print("Here");
            if (tile.isVisited()) {
                g.drawImage(sprite, xOffset, yOffset, Settings.TILEWIDTH, Settings.TILEHEIGHT, null);
                if(itemView != null) {
                    itemView.render(g, xOffset, yOffset);
                    //itemView.render(g);
                }
                g.drawImage(Assets.HALFFOGTILE, xOffset, yOffset, Settings.TILEWIDTH, Settings.TILEHEIGHT, null);

            } else {
                //g.drawImage(Assets.FOGTILE, xOffset, yOffset, Settings.TILEWIDTH, Settings.TILEHEIGHT, null);

            }
        }else {
            g.drawImage(sprite, xOffset, yOffset, Settings.TILEWIDTH, Settings.TILEHEIGHT, null);
            if(decalView != null){
                decalView.render(g, xOffset, yOffset);
            }
            if(itemView != null) {
                itemView.render(g, xOffset, yOffset);
                //itemView.render(g);
            }
        }
    }

}
