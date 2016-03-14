package Model.GameObject.MobileObjects.Entities;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Takable;
import Model.GameObject.MobileObjects.Entities.AI.PetController;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.Inventory.Pack;
import Model.Map.Tile;
import Model.Requirement;
import Model.Stats.PetStats;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;
import Model.Tickable;
import Utilities.ItemUtilities.ItemsEnum;
import View.Views.MessageBox.*;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Wimberley on 3/3/16.
 */

/* Setting up packages

 */
public class Pet extends MobileObject implements Tickable {

    PetController controller;
    PetStats stats;
    Location base;
    Pack pack;
    boolean owned;
    private Player player;
    private Requirement requirement;

    public Pet(PetController controller, Location location, int id, PetStats stats, Pack pack, Player player, ItemsEnum reqItem) {
        super(location, id, stats);
        base = location;
        this.controller = controller;
        this.stats = stats;
        this.pack = pack;
        stats.setMovement(8);
        this.requirement = new Requirement(reqItem);
        this.player = player;
        this.controller.setBaseLoc(new Location(13,13));
        controller.setAI(this);
    } // end constructor

    @Override
    public void tick() {
        if(!owned) {
            if (controller.targetinView() && requirement.hasRequiredItem(player.getPack())) {
                setOwnership();
            }
        }
        if(controller != null) {
            controller.tick();
        }
    } // end tick

    public PetStats getStats() {
        return stats;
    }

    public Pack getPack() {
        return pack;
    }

    public boolean getOwned() {
        return owned;
    }

    public ArrayList<Item> takeItems(ArrayList<Item> items) {
        ArrayList<Item> tempItems = new ArrayList<>(items);
        Iterator<Item> it = tempItems.iterator();
        while (it.hasNext()) {
            Item i = it.next();
            if (i instanceof Takable && pack.getSizeLeft() > 0) {
                pickup(i);
                items.remove(i);
            }
        }
        return items;
    }

    public PetController getController() {
        return controller;
    }

    public void setOwnership() {
        owned = true;
        controller.setPlayer(player);
        controller.setBaseLoc(player.getLocation());
        player.getPack().remove(ItemsEnum.SUSHI);
        requirement = new Requirement();
        DisplayMessage.addMessage(new GameMessage("MHHMMMMM! I love me some SUSH", 4));
    }

    public Tile dismountOntoTile(Location location) {
        tile = map.register(this);
        return tile;
    }

    public void pickup(Item item) {
        pack.place(item);
    } // end pickup
} // end