package Model.GameObject.MobileObjects.Entities;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Takable;
import Model.GameObject.MobileObjects.Entities.AI.PetController;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.Inventory.Pack;
import Model.Requirement;
import Model.Stats.PetStats;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;
import Model.Tickable;
import Utilities.ItemUtilities.ItemsEnum;

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

    public Pet(PetController controller) {

        base = new Location(0, 0);
        this.controller = controller;
        stats = new PetStats();
        pack = new Pack();
        owned = false;
        stats.setMovement(6);
        controller.setAI(this);
    } // end default constructor

    public Pet(PetController controller, Location location, int id, PetStats stats, Pack pack, Player player, ItemsEnum reqItem) {
        super(location, id, stats);
        base = location;
        this.controller = controller;
        this.stats = stats;
        this.pack = pack;
        stats.setMovement(6);
        this.requirement = new Requirement(reqItem);
        this.player = player;
        this.controller.setBaseLoc(new Location(10,10));
        controller.setAI(this);
    } // end constructor

    @Override
    public void tick() {
        if(this.getController().targetinView() && this.requirement.hasRequiredItem(player.getPack())){
            setOwnership();
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
        this.owned = true;
        this.getController().setPlayer(this.player);
        this.getController().setBaseLoc(this.player.getLocation());
        for(int i = 0; i < player.getPack().getCount(); i++){

        }
    }

    public void pickup(Item item) {
        pack.place(item);
    } // end pickup
} // end