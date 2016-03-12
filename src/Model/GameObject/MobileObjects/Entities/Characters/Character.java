package Model.GameObject.MobileObjects.Entities.Characters;

import Model.Abilities.CommandsEnum;
import Model.Effects.Effect;
import Model.Effects.EquipmentModification;
import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Interactable;
import Model.GameObject.Item.Items.OneShot;
import Model.GameObject.Item.Items.Takable;
import Model.GameObject.Item.Items.Takables.Equippable.Armor;
import Model.GameObject.Item.Items.Takables.Equippable.Weapon;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.GameObject.MobileObjects.Entities.Entity;
import Model.GameObject.MobileObjects.Vehicle;
import Model.Inventory.EquipmentSlotEnum;
import Model.Inventory.Inventory;
import Model.Inventory.Pack;
import Model.Location;
import Model.Map.Map;
import Model.Stats.CharacterStats;
import View.Views.MessageBox.DisplayMessage;
import View.Views.MessageBox.GameMessage;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by broskj on 3/6/16.
 */
public abstract class Character extends Entity {
    protected Inventory inventory;

    public Character() {
        super();
        this.inventory = new Inventory();
    } // end default constructor

    public Character(Location location, int id, Occupation occupation, Inventory inventory) {
        super(location, id, occupation.getStats(), occupation);
        this.inventory = inventory;
    } // end constructor

    public Character(Location location, int id, CharacterStats stats, Occupation occupation, Inventory inventory) {
         super(location, id, stats, occupation);
        this.inventory = inventory;
    } // end constructor

    public ArrayList<Item> takeItems(ArrayList<Item> items) {
        //System.out.println("Here");
        ArrayList<Item> tempItems = new ArrayList<>(items);
        Iterator<Item> it = tempItems.iterator();
        while(it.hasNext()){
            Item i = it.next();
            if(i instanceof Takable) {//if its takable
                if(pickup(i)) {//and i was able to pick it up
                    DisplayMessage.addMessage(new GameMessage("You picked up: " + i.getName(), 3));
                    items.remove(i); //remove it from the items
                }
            }
        }
        return items;
    }

    public void interact(Item item) {
        if (item instanceof Interactable) {
            //HUH?
        }else if (item instanceof OneShot) {
            getStats().applyEffect(((OneShot) item).getEffect());

        }
    } // end interact



    public void equip(Weapon weapon) {
        inventory.equip(weapon);
        ((CharacterStats)getStats()).applyEquipmentModification(weapon.getEquipmentModification());
    } // end equip

    public void equip(Armor armor) {
        inventory.equip(armor);
        ((CharacterStats)getStats()).applyEquipmentModification(armor.getEquipmentModification());
    } // end equip

    public void mount(Vehicle vehicle){
        //getStats().setMovement(vehicle.getMovement());
        // change sprite
    } // end mount

    public void unequip(EquipmentSlotEnum slot) {
        inventory.unequip(slot);
        ((CharacterStats)getStats()).removeEquipmentModification((EquipmentModification) inventory.getSlot(slot).getEffect());
    } // end unequip

    public void unmount(){
        //getStats().resetMovement();
        // change sprite
    } // end unmount


    public void attack() {

        getTile().recieveAttack(this);
    }

    public void recieveAttack(Character attacker) {
        System.out.print(this.getClass() + " is being attack by " + attacker.getClass());
        //this.applyEffect(attacker);
    }

    public void useAbility(CommandsEnum e) {
        //tile.useAbility(this, getAbility(e))
    }

    public boolean pickup(Item item) {

        if(inventory.getPackSpaceLeft() > 0){
            inventory.place(item);
            return true;
        }
        return false;
    } // end pickup
    public void emptyPack() {
        DisplayMessage.addMessage(new GameMessage("You emptied your Pack", 3));
        getTile().addItems(inventory.emptyPack());
    } // end emptyPack

    public void applyEffect(Effect... e) {
        ((CharacterStats)getStats()).applyEffect(e);
    } // end applyEffect

    public boolean checkForDeath() {
        return !((CharacterStats) getStats()).isDead();
    } // end checkForDeath

    public void execute(CommandsEnum e) {
        switch(e){
            case interact:
                interactWithTile();
                break;
            case drop:
                emptyPack();
                break;
            case attack:
                attack();
            case ability1:

            case ability2:
            case ability3:
                useAbility(e);
                break;
        }

    }

    public CharacterStats getStats() {
        return (CharacterStats)stats;
    }
    public Inventory getInventory(){
        return inventory;
    }

    public Pack getPack() { return inventory.getPack(); }

} // end class Character
