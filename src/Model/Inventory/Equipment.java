package Model.Inventory;

import Model.GameObject.Item.Items.Takables.Equippable.Armor;
import Model.GameObject.Item.Items.Takables.Equippable.Weapon;
import Utilities.Observer;

import java.util.ArrayList;

/**
 * Created by broskj on 3/6/16.
 *
 * Handles equipped armor and weapons.
 *
 * A character can wield one 2-handed weapon, two 1-handed weapons, or one 1-handed weapons and one shield.
 * A character can only wear one of each armor type.
 *
 * Any attempt to equip an item in a slot where an item already exists will unequip the existing item first.
 */
public class Equipment {
    private Weapon mainhand,
            offhand;
    private Armor head,
            chest,
            legs,
            gloves,
            boots,
            shield,
            accessory1,
            accessory2;
    private ArrayList<Observer> observers;

    public Equipment() {
        mainhand = offhand = null;
        head = chest = legs = gloves = boots = shield = accessory1 = accessory2 = null;
        observers = new ArrayList<>();
    } // end default constructor

    public Equipment(Weapon[] weapons, Armor[] armor) {
        /*
        don't pass this constructor anything illegal!  I'm preventing multiple items per slot
         by equipping each weapon and armor as it's passed, but passing three weapons or
         something stupid like that is counterproductive.
         */

        for (Weapon w : weapons) {
            equip(w);
        }
        for (Armor a : armor) {
            equip(a);
        }
        observers = new ArrayList<>();
    } // end constructor

    /*
    equip methods need to set slot equal to the item being passed in
        if a slot already contains an item, unequip that item first
     */
    public void equip(Weapon weapon) {
        switch (weapon.getType()) {
            case ONE_HANDED:
                if(!mainhand.equals(null)) {
                    unequip(mainhand);
                    mainhand = weapon;
                } else if(!offhand.equals(null)) {
                    unequip(offhand);
                    offhand = weapon;
                } else if(!shield.equals(null)) {
                    unequip(shield);
                    offhand = weapon;
                }
                break;
            case TWO_HANDED:
                if(!mainhand.equals(null)) { unequip(mainhand); }
                if(!offhand.equals(null)) { unequip(offhand); }
                if(!shield.equals(null)) { unequip(shield); }
                mainhand = weapon;
                break;
            default:
                System.out.println("Stop trying to equip a non-weapon to a weapon slot, asshole");
                break;
        }
    } // end equip

    public void equip(Armor armor) {
        switch (armor.getType()) {
            case HEAD:
                if(!head.equals(null)) { unequip(head); }
                head = armor;
                break;
            case CHEST:
                if(!chest.equals(null)) { unequip(chest); }
                chest = armor;
                break;
            case LEGS:
                if(!legs.equals(null)) { unequip(legs); }
                legs = armor;
                break;
            case GLOVES:
                if(!gloves.equals(null)) { unequip(gloves); }
                gloves = armor;
                break;
            case BOOTS:
                if(!boots.equals(null)) { unequip(boots); }
                boots = armor;
                break;
            case SHIELD:
                /*
                shield occupies offhand slot, so unequip 2h or offhand weapon
                 */
                if(!shield.equals(null)) { unequip(shield); }
                if(mainhand.getType().equals(EquipmentEnum.TWO_HANDED)) { unequip(mainhand); }
                if(!offhand.equals(null)) { unequip(offhand); }
                shield = armor;
                break;
            case ACCESSORY:
                if(!accessory1.equals(null) && !accessory2.equals(null)) { unequip(accessory2); }
                if(!accessory1.equals(null)) {
                    accessory1 = armor;
                } else if(!accessory2.equals(null)) {
                    accessory2 = armor;
                }
                break;
            default:
                System.out.println("Stop trying to equip a non-armor to an armor slot, asshole");
                break;
        }
    } // end equip

    /*
    unequip methods need to set current slot to null and move that item to the player's inventory
     */
    public Weapon unequip(Weapon weapon) {
        switch (weapon.getType()) {
            case ONE_HANDED:
                if(mainhand.equals(weapon)) { mainhand = null; }
                else if(offhand.equals(weapon)) { offhand = null; }
                return weapon;
            case TWO_HANDED:
                if(mainhand.equals(weapon)) { mainhand = null; }
                return weapon;
            default:
                System.out.println("What the hell sort of circumstances led you to seeing this message?  Freak.");
                break;
        }
        // should never be reached
        return null;
    } // end unequip

    public Armor unequip(Armor armor) {
        switch (armor.getType()) {
            case HEAD:
                head = null;
                return armor;
            case CHEST:
                chest = null;
                return armor;
            case LEGS:
                legs = null;
                return armor;
            case GLOVES:
                gloves = null;
                return armor;
            case BOOTS:
                boots = null;
                return armor;
            case SHIELD:
                shield = null;
                return armor;
            case ACCESSORY:
                if(accessory1.equals(armor)) { accessory1 = null; return armor; }
                else if(accessory2.equals(armor)) { accessory2 = null; return armor; }
                else return null;
            default:
                System.out.println("What the hell sort of circumstances led you to seeing this message?  Weirdo.");
                break;
        }
        // should never be reached
        return null;
    } // end unequip
} // end class Equipment
