package View.Views;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Interactable;
import Model.GameObject.Item.Items.Obstacle;
import Model.GameObject.Item.Items.Takables.Money;
import Model.GameObject.MobileObjects.ViewLocation;
import Model.Location;
import Model.Map.Tile;
import State.State;
import Utilities.AreaEffectUtilities.AreaEffectFactory;
import Utilities.ItemUtilities.ItemFactory;
import Utilities.ItemUtilities.ItemsEnum;
import Utilities.Observer;
import Utilities.Settings;
import Utilities.Utilities;
import View.ViewUtilities.Graphics.Assets;
import View.ViewUtilities.Renderable;
import View.ViewUtilities.ViewSettings;
import com.sun.xml.internal.fastinfoset.tools.StAX2SAXReader;

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

    public BufferedImage setSprite() {
        return sprite;
    }

    @Override
    public void update() {
        if (tile.hasItems()) {
            Item item;
            System.out.println(tile.getItems());
            if (tile.amountOfItems() > 1) {
                itemView = ItemFactory.makeAsset(ItemsEnum.BAGOFITEMS, tile.getItems().get(0));
                for (Item i : tile.getItems()) {
                    if (i instanceof Obstacle) {//IF THE ITEM IS AN OBSTACLE, DRAW THAT ONLY
                        item = i;
                        itemView = ItemFactory.makeAsset(ItemsEnum.values()[item.getId()], tile.getItems().get(0));
                    }
                }
            } else {
<<<<<<< HEAD
                item = tile.getItems().get(0);//IF THERE IS ONLY, USE THE ONLY ONE
                if(item instanceof Money){
                    System.out.println("Moneyyyyy");
                    return;
                }
                itemView = ItemFactory.makeAsset(ItemsEnum.values()[item.getId()], tile.getItems().get(0));


=======
                item = tile.getItems().get(0);
                if(item instanceof Interactable) {
                    if (((Interactable) item).getState()) {
                        System.out.println("toggleState!");
                        itemView = ItemFactory.makeAsset(ItemsEnum.values()[item.getId()], tile.getItems().get(0));
                        itemView.adjustView();
                    } else {
                        itemView = ItemFactory.makeAsset(ItemsEnum.values()[item.getId()], tile.getItems().get(0));
                    }
                }
                else{
                    itemView = ItemFactory.makeAsset(ItemsEnum.values()[item.getId()], tile.getItems().get(0));
                }
>>>>>>> c1ca20ca836cdaa0dadae3115de7c2bb8439f43a
            }

        } else {
            itemView = null;
        }


        //initialize decalView
        if (tile.getHasAreaEffect()) {
            decalView = AreaEffectFactory.makeAsset(tile.getAreaEffectEnum(), tile.getAreaEffect());
        }

        if(tile.getHasTeleportAreaEffect()){
            decalView = AreaEffectFactory.makeAsset(tile.getTeleportAreaEffect());
        }

        if (tile.hasObject()) {
            if (State.getCurrentState() == State.GAMESTATE) {
                mobileObjectView = State.GAMESTATE.getMobileObjectView(tile.getObject());
            }
        } else {
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
        if (decalView != null) {
            decalView.render(g, xOffset, yOffset);
        }
        if (itemView != null) {
            itemView.render(g, xOffset, yOffset);
            //itemView.render(g);
        }
    }

    public void mobileObjects(Graphics g, int xOffset, int yOffset, Location playerLocation) {
        if (tile.hasObject() && mobileObjectView == null && !Utilities.outOfSite(new ViewLocation(playerLocation.getX(), playerLocation.getY()), this.viewLocation, ViewSettings.SIGHT)) {
            mobileObjectView = State.GAMESTATE.getMobileObjectView(tile.getObject());
        } else if (!tile.hasObject()) {
            mobileObjectView = null;
        }
        if (mobileObjectView != null) {
            mobileObjectView.render(g, State.GAMESTATE.getCamera().getxOffset(), State.GAMESTATE.getCamera().getyOffset());
        }
    }

    public void renderFog(Graphics g, int xOffset, int yOffset, Location playerLocation) {
        if (Utilities.outOfSite(new ViewLocation(playerLocation.getX(), playerLocation.getY()), this.viewLocation, ViewSettings.SIGHT)) {//tile.visited
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
