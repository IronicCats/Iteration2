package Model.GameObject.MobileObjects.Entities.Characters;

import Model.Abilities.Abilities;
import Model.Abilities.CommandsEnum;
import Model.Effects.Effect;
import Model.Effects.EquipmentModification;
import Model.Effects.ModificationEnum;
import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Interactable;
import Model.GameObject.Item.Items.OneShot;
import Model.GameObject.Item.Items.Takable;
import Model.GameObject.Item.Items.Takables.Equippable.Armor;
import Model.GameObject.Item.Items.Takables.Equippable.Weapon;
import Model.GameObject.Item.Items.Takables.Money;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.SkillsEnum;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Sneak;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Summoner;
import Model.GameObject.MobileObjects.Entities.Entity;
import Model.GameObject.MobileObjects.Vehicle;
import Model.Inventory.*;
import Model.Location;
import Model.Requirement;
import Model.Stats.CharacterStats;
import Model.Stats.StatsEnum;
import Utilities.MobileObjectUtilities.RespawnQueue;
import Utilities.Observer;
import Utilities.Utilities;
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
    protected Abilities bindWounds;
    public Abilities ability1;
    public Abilities ability2;
    public Abilities ability3;


    public Character() {
        super();
        this.inventory = new Inventory();
        getStats().addObserver(this);
    } // end default constructor

    public Character(Location location, int id, Occupation occupation, Inventory inventory) {
        super(location, id, occupation.getStats(), occupation);
        this.inventory = inventory;
        attack = occupation.getBasicAttack();
        bindWounds = occupation.getBindWounds();
        if(occupation instanceof Summoner){
            ability1 = occupation.getAbilityAt(0);
            ability2 = occupation.getAbilityAt(3);
            ability3 = occupation.getAbilityAt(5);
        }
        if((occupation instanceof Sneak)){
            ability1 = occupation.getAbilityAt(0);
            ability2 = occupation.getAbilityAt(2);
        }
        //System.out.println(attack);
        getStats().addObserver(this);
        this.addObserver(occupation);

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
            }else if(i instanceof OneShot) {
                interact(i);
                items.remove(i);
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
        getStats().applyEquipmentModification(weapon.getEquipmentModification());

        switch (weapon.getType()) {
            case ONE_HANDED:
            case SHIELD:
                getStats().modifyStat(StatsEnum.MOVEMENT, ModificationEnum.PERCENT, -25);
                break;
            case TWO_HANDED:
                getStats().modifyStat(StatsEnum.MOVEMENT, ModificationEnum.PERCENT, -50);
                break;
            default:
                break;
        }
    } // end equip

    public void equip(Armor armor) {
        inventory.equip(armor);
        getStats().applyEquipmentModification(armor.getEquipmentModification());
    } // end equip

    public void unequip(EquipmentSlotEnum slot) {
        if(inventory.getSlot(slot) == null)
            return;

        if(inventory.getSlot(slot) instanceof Weapon) {
            System.out.println("it's a weapon");
            switch (((Weapon) inventory.getSlot(slot)).getType()) {
                case ONE_HANDED:
                case SHIELD:
                    getStats().modifyStat(StatsEnum.MOVEMENT, ModificationEnum.PERCENT, 25);
                    break;
                case TWO_HANDED:
                    getStats().modifyStat(StatsEnum.MOVEMENT, ModificationEnum.PERCENT, 25);
                    break;
                default:
                    break;
            }
        }

        getStats().removeEquipmentModification((EquipmentModification) inventory.getSlot(slot).getEffect());
        inventory.unequip(slot);
    } // end unequip

    public void mount(Vehicle vehicle){

        this.getStats().setMovement(vehicle.getMovement());
        //vehicle.interact();

    }


    public void unmount() {
        //getStats().resetMovement();
        // change sprite
    } // end unmount


    public void attack(Abilities a) {
        occupation.setWeaponType(getEquippedWeaponInSlot(EquipmentSlotEnum.MAINHAND));
        if (a == null) {
            System.out.println("Ability not set");
            return;
        }
        getTile().sendAttack(this, a);

    }

    public void receiveAttack(Character c,Abilities ability) {
        //Calculate Damage done based on Offensive Rating and Defensive Rating
        //But for now, just apply effect
        this.applyEffect(ability.getEffects());
    }

    public void receiveProjectileAttack(Effect e){
        this.applyEffect(e);
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
        //System.out.println("emptying pack at location " + getTile().getLocation());
        getTile().addItems(inventory.emptyPack(location));
    } // end emptyPack

    public void drop(int index) {
        DisplayMessage.addMessage(new GameMessage("You dropped your " + inventory.get(index), 3));
        getTile().addItem(inventory.remove(index));
    } // end drop

    public void equip(int index) {
        Item item = inventory.get(index);
        if(meetsRequirement(((Takable)item).getRequirements())) {
            DisplayMessage.addMessage(new GameMessage("You equipped  " + inventory.get(index), 3));
            if(inventory.get(index) instanceof Weapon) {
                equip((Weapon) inventory.remove(index));
            }
            else if(inventory.get(index) instanceof Armor) {
                equip((Armor) inventory.remove(index));
            }
        } else {
            System.out.println("don't meet requirements");
        }
    } // end equip

    public void applyEffect(Effect... e) {
        ((CharacterStats)getStats()).applyEffect(e);
    } // end applyEffect

    public void setInitialLevel(int level) {
        for (int i = 0; i < level-1; i++) {
            getStats().levelUp();
        }
    } // end setInitialLevel

    public boolean meetsRequirement(Requirement requirement) {
        return requirement.meetsRequirements(getStats().getLevel(), getPack(), getOccupation());
    } // end meetsRequirement

    public void execute(CommandsEnum e) {
        switch (e) {
            case interact:
                interactWithTile();
                break;
            case drop:
                emptyPack();
                break;
            case attack:
                System.out.println("Attacking with: " + attack.getName());
                attack(this.attack);
                break;
            case bindWounds:
                System.out.println("Binding Wounds");
                attack(this.bindWounds);
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

    public ArrayList<Abilities> getAbilities(){
        ArrayList<Abilities> abilities = new ArrayList<Abilities>();
        if(attack != null){
            abilities.add(attack);
        }
        if(ability1 != null){
            abilities.add(ability1);
        }
        if (ability2 != null){
            abilities.add(ability2);
        }
        if(ability3 != null){
            abilities.add(ability3);
        }
        return abilities;
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

    public boolean revive() {
        location.setX(getBaseLocation().getX());
        location.setY(getBaseLocation().getY());
        updateViewLocation(Utilities.calculateTileCenterXLocation(getX(), getY()), Utilities.calculateTileCenterYLocation(getX(), getY()));
        if(map.getTile(location).hasObject()){
            System.out.println("Uh Oh, something is already on your base location");
            return false;
        }
        registerTile(location);
        alert();
        getStats().revive();
        return true;
    }


    public boolean isDead() {
        return !getStats().isAlive();
    }
    public int getMoney(){
        return getPack().getMoney();
    }

    public void setMoney(int money){
        getPack().setMoney(money);
    }

    public void modifyMoney(int money){
        getPack().modifyMoney(money);
    }

    public Equipment getEquipment(){
        return inventory.getEquipment();
    }

    public Takable getWeaponInSlot(EquipmentSlotEnum s){
        return getEquipment().getSlot(s);
    }

    public EquipmentTypeEnum getEquippedWeaponInSlot(EquipmentSlotEnum s){
        if(getWeaponInSlot(s) != null)
            return ((Weapon)(getWeaponInSlot(s))).getType();
        else
            return null;
    }

    public int getBasicSkillsValue(SkillsEnum s){
        return occupation.getBasicSkillValue(s);
    }

    @Override
    public void update() {

    } // end update

    @Override
    public void remove() {}

    @Override
    public void tick() {
        if(this instanceof Player) {

        }
        if (isDead()) {
            //respawn eventually
            die();
            moveToRespawnQueue();
        }else {
            getStats().tick();
        }

    } // end tick

    public void die() {
        if(getTile() != null) {
            emptyPack();
            deregister();
        }
    }

    public void moveToRespawnQueue() {
        if(RespawnQueue.isInQueue(this)){
            return;
        }else {
            RespawnQueue.addCharacter(this);
            System.out.println("You Ded " + this);
        }
    }


} // end class Character