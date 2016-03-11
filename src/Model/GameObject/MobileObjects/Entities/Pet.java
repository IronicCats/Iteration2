package Model.GameObject.MobileObjects.Entities;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Takable;
import Model.GameObject.MobileObjects.Entities.AI.PetController;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.Inventory.Pack;
import Model.Stats.PetStats;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;
import Model.Tickable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Wimberley on 3/3/16.
 */

/* Setting up packages

 */
public class Pet extends MobileObject implements Tickable{

    PetController controller;
    PetStats stats;
    Location base;
    Pack pack;
    boolean owned;
    private Player player;

    public Pet(PetController controller) {

        base = new Location(0, 0);
        this.controller = controller;
        stats = new PetStats();
        pack = new Pack();
        owned = false;
        stats.setMovement(6);
        controller.setAI(this);
    } // end default constructor

    public Pet(PetController controller, Location location, int id, PetStats stats, Pack pack, boolean owned) {
        super(location, id, stats);
        base = location;
        this.controller = controller;
        this.stats = stats;
        this.pack = pack;
        this.owned = owned;

        controller.setAI(this);
    } // end constructor

    @Override
    public void tick() {
        if(controller != null) {
            controller.tick();
        }
    } // end tick

    public PetStats getStats() { return stats; }
    public Pack getPack() { return pack; }
    public boolean getOwned() { return owned; }

    public ArrayList<Item> takeItems(ArrayList<Item> items) {
        ArrayList<Item> tempItems = new ArrayList<>(items);
        Iterator<Item> it = tempItems.iterator();
        while(it.hasNext()){
            Item i = it.next();
            if(i instanceof Takable && pack.getSizeLeft() > 0) {
                pickup(i);
                items.remove(i);
            }
        }
        return items;
    }

    public void setOwnership(Player player){
        this.owned = true;
        this.player = player;
    }

    public void pickup(Item item) {
        pack.place(item);
    } // end pickup
} // end
