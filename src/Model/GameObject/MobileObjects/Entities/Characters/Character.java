package Model.GameObject.MobileObjects.Entities.Characters;

import Model.Abilities.Abilities;
import Model.Abilities.CommandsEnum;
import Model.Effects.Effect;
import Model.Effects.EquipmentModification;
import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Interactable;
import Model.GameObject.Item.Items.OneShot;
import Model.GameObject.Item.Items.Takable;
import Model.GameObject.Item.Items.Takables.Equippable.Armor;
import Model.GameObject.Item.Items.Takables.Equippable.Weapon;
import Model.GameObject.Item.Items.Takables.Money;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.GameObject.MobileObjects.Entities.Entity;
import Model.Inventory.EquipmentSlotEnum;
import Model.Inventory.Inventory;
import Model.Inventory.Pack;
import Model.Location;
import Model.Stats.CharacterStats;
import Utilities.Observer;
import View.Views.MessageBox.DisplayMessage;
import View.Views.MessageBox.GameMessage;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by broskj on 3/6/16.
 */
public abstract class Character extends Entity implements Observer{
    protected Inventory inventory;
    protected Abilities attack;
    protected Abilities ability1;
    protected Abilities ability2;
    protected Abilities ability3;


    public Character() {
        super();
        this.inventory = new Inventory();
        getStats().addObserver(this);
    } // end default constructor

    public Character(Location location, int id, Occupation occupation, Inventory inventory) {
        super(location, id, occupation.getStats(), occupation);
        this.inventory = inventory;
        attack = occupation.getBasicAttack();
        System.out.println(attack);
        getStats().addObserver(this);

    } // end constructor

    public ArrayList<Item> takeItems(ArrayList<Item> items) {
        //System.out.println("Here");
        ArrayList<Item> tempItems = new ArrayList<>(items);
        Iterator<Item> it = tempItems.iterator();
        while (it.hasNext()) {
            Item i = it.next();
            if (i instanceof Takable) {//if its takable
                if (pickup(i)) {//and i was able to pick it up
                    if(i instanceof Money){
                        DisplayMessage.addMessage(new GameMessage("You picked up: " + ((Money)i).getQuantity() + " CatNips", 3));
                    }else {
                        DisplayMessage.addMessage(new GameMessage("You picked up: " + i.getName(), 3));
                    }
                    items.remove(i); //remove it from the items
                }
            }
        }
        return items;
    }

    public void interact(Item item) {
        if (item instanceof Interactable) {
            //HUH?
        } else if (item instanceof OneShot) {
            getStats().applyEffect(((OneShot) item).getEffect());

        }
    } // end interact

    public void equip(Weapon weapon) {
        inventory.equip(weapon);
        ((CharacterStats) getStats()).applyEquipmentModification(weapon.getEquipmentModification());
    } // end equip

    public void equip(Armor armor) {
        inventory.equip(armor);
        ((CharacterStats) getStats()).applyEquipmentModification(armor.getEquipmentModification());
    } // end equip

    public void unequip(EquipmentSlotEnum slot) {
        inventory.unequip(slot);
        ((CharacterStats) getStats()).removeEquipmentModification((EquipmentModification) inventory.getSlot(slot).getEffect());
    } // end unequip

    public void unmount() {
        //getStats().resetMovement();
        // change sprite
    } // end unmount


    public void attack(Abilities a) {
        System.out.println("Executing ability: " + a);
        if (a == null) {
            System.out.println("Ability not set");
            return;
        }
        getTile().sendAttack(this, a);
    }

    public void receiveAttack(Character attacker, Abilities ability) {
        //Calculate Damage done based on Offensive Rating and Defensive Rating
        //But for now, just apply effect
        System.out.println(ability.getEffects().toString());
        this.applyEffect(ability.getEffects());
    }

    public boolean pickup(Item item) {

        if (inventory.getPackSpaceLeft() > 0) {
            inventory.place(item);
            return true;
        }
        return false;
    } // end pickup

    public void emptyPack() {
        DisplayMessage.addMessage(new GameMessage("You emptied your Pack", 3));
        getTile().addItems(inventory.emptyPack());
    } // end emptyPack

    public void drop(int index) {
        DisplayMessage.addMessage(new GameMessage("You dropped your " + inventory.get(index), 3));
        getTile().addItem(inventory.remove(index));
    }

    public void applyEffect(Effect... e) {
        ((CharacterStats)getStats()).applyEffect(e);
    } // end applyEffect

    public void setInitialLevel(int level) {
        for (int i = 0; i < level; i++) {
            getStats().levelUp();
        }
    } // end setInitialLevel

    public void execute(CommandsEnum e) {
        switch (e) {
            case interact:
                interactWithTile();
                break;
            case drop:
                emptyPack();
                break;
            case attack:
                System.out.println("Attacking with: " + attack);
                attack(this.attack);
                break;
            case ability1:
                attack(ability1);
                break;
            case ability2:
                attack(ability2);
                break;
            case ability3:
                attack(ability3);
                break;
        }

    }

    public CharacterStats getStats() {
        return (CharacterStats) stats;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Pack getPack() {
        return inventory.getPack();
    }

    public boolean isDead() {
        return !getStats().isAlive();
    }

    @Override
    public void update() {
        if(!getStats().isAlive()) {
            emptyPack();
            getStats().revive();
        }
    } // end update

    @Override
    public void remove() {}

    @Override
    public void tick() {
        if (!isDead()) {
            getStats().tick();
            //respawn eventually
        }
    }

} // end class Character
