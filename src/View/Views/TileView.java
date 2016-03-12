package View.Views;

import Model.GameObject.AreaEffect.AreaEffect;
import Model.GameObject.AreaEffect.AreaEffectEnum;
import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Obstacle;
import Model.GameObject.MobileObjects.MobileObject;
import Model.GameObject.MobileObjects.ViewLocation;
import Model.Location;
import Model.Map.Tile;
import State.State;
import State.States.GameState.GameState;
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
    private MobileObjectView mobileObjectView;


    public TileView(Tile tile, BufferedImage sprite) {
        this.tile = tile;
        this.itemView = null;
        this.mobileObjectView = null;
        this.decalView = null;
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
                itemView = ItemFactory.makeAsset(ItemsEnum.BAGOFITEMS,tile.getItems().get(0));
                for(Item i: tile.getItems()){
                    if(i instanceof Obstacle){//IF THE ITEM IS AN OBSTACLE, DRAW THAT ONLY
                        item = i;
                        itemView = ItemFactory.makeAsset(ItemsEnum.values()[item.getId()],tile.getItems().get(0));
                    }
                }
            }else{
                item = tile.getItems().get(0);//IF THERE IS ONLY, USE THE ONLY ONE
                itemView = ItemFactory.makeAsset(ItemsEnum.values()[item.getId()],tile.getItems().get(0));


            }

        }else {
            itemView = null;
        }


        //initialize decalView
        if(tile.getHasAreaEffect()){
           decalView = AreaEffectFactory.makeAsset(tile.getAreaEffectEnum(), tile.getAreaEffect());
        }

        if(tile.hasObject()){
            if(State.getCurrentState() == State.GAMESTATE) {
                mobileObjectView = State.GAMESTATE.getMobileObjectView(tile.getObject());
            }
        }else {
            mobileObjectView = null;
        }

    }

    @Override
    public void remove() {

    }

    @Override
    public void render(Graphics g) {

    }

    public void renderTile(Graphics g, int xOffset, int yOffset, Location playerLocation) {
        g.drawImage(sprite, xOffset, yOffset, Settings.TILEWIDTH, Settings.TILEHEIGHT, null);

    }

    public void renderNonMobile(Graphics g, int xOffset, int yOffset, Location playerLocation) {
        if(decalView != null){
            decalView.render(g, xOffset, yOffset);
        }
        if(itemView != null) {
            itemView.render(g, xOffset, yOffset);
            //itemView.render(g);
        }
    }

    public void mobileObjects(Graphics g, int xOffset, int yOffset, Location playerLocation) {
        if(tile.hasObject() && mobileObjectView == null && !Utilities.outOfSite(new ViewLocation(playerLocation.getX(), playerLocation.getY()), this.viewLocation)){
            mobileObjectView = State.GAMESTATE.getMobileObjectView(tile.getObject());
        }else if(!tile.hasObject()){
            mobileObjectView = null;
        }
        if(mobileObjectView != null) {
            mobileObjectView.render(g, State.GAMESTATE.getCamera().getxOffset(), State.GAMESTATE.getCamera().getyOffset());
        }
    }

    public void renderFog(Graphics g, int xOffset, int yOffset, Location playerLocation) {
        if(Utilities.outOfSite(new ViewLocation(playerLocation.getX(), playerLocation.getY()), this.viewLocation)) {//tile.visited
            if (tile.isVisited()) {
                g.drawImage(Assets.HALFFOGTILE, xOffset, yOffset, Settings.TILEWIDTH, Settings.TILEHEIGHT, null);
                return;
            } else {
                mobileObjectView = null;
                g.drawImage(Assets.FOGTILE, xOffset, yOffset, Settings.TILEWIDTH, Settings.TILEHEIGHT, null);
                return;
            }
        }
        tile.setIsVisited();

    }
}
