package Model.Inventory;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Takable;
import Model.GameObject.Item.Items.Takables.Equippable.Armor;
import Model.GameObject.Item.Items.Takables.Equippable.Weapon;

/**
 * Created by broskj on 3/6/16.
 * <p>
 * Handles equipped armor and weapons.
 * <p>
 * A character can wield one 2-handed weapon, two 1-handed weapons, or one 1-handed weapons and one shield.
 * A character can only wear one of each armor type.
 * <p>
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

    public Equipment() {
        mainhand = offhand = null;
        head = chest = legs = gloves = boots = shield = accessory1 = accessory2 = null;
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
    } // end constructor

    /*
    equip methods need to set slot equal to the item being passed in
        if a slot already contains an item, unequip that item first
     */
    public void equip(Weapon weapon) {
        switch (weapon.getType()) {
            case ONE_HANDED:
                if (mainhand != null) {
                    unequip(EquipmentSlotEnum.MAINHAND);
                }
                mainhand = weapon;
                break;
            case TWO_HANDED:
            case DOUBLE_HANDED:
                if (mainhand != null) {
                    unequip(EquipmentSlotEnum.MAINHAND);
                }
                if (offhand != null) {
                    unequip(EquipmentSlotEnum.OFFHAND);
                }
                if (shield != null) {
                    unequip(EquipmentSlotEnum.SHIELD);
                }
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
                if (head != null) {
                    unequip(EquipmentSlotEnum.HEAD);
                }
                head = armor;
                break;
            case CHEST:
                if (chest != null) {
                    unequip(EquipmentSlotEnum.CHEST);
                }
                chest = armor;
                break;
            case LEGS:
                if (legs != null) {
                    unequip(EquipmentSlotEnum.LEGS);
                }
                legs = armor;
                break;
            case GLOVES:
                if (gloves != null) {
                    unequip(EquipmentSlotEnum.GLOVES);
                }
                gloves = armor;
                break;
            case BOOTS:
                if (boots != null) {
                    unequip(EquipmentSlotEnum.BOOTS);
                }
                boots = armor;
                break;
            case SHIELD:
                /*
                shield occupies offhand slot, so unequip 2h or offhand weapon
                 */
                if (shield != null) {
                    unequip(EquipmentSlotEnum.SHIELD);
                }
                if (mainhand.getType().equals(EquipmentTypeEnum.TWO_HANDED)) {
                    unequip(EquipmentSlotEnum.MAINHAND);
                }
                if (offhand != null) {
                    unequip(EquipmentSlotEnum.OFFHAND);
                }
                shield = armor;
                break;
            case ACCESSORY:
                if (accessory1 != null && accessory2 != null) {
                    unequip(EquipmentSlotEnum.ACCESSORY2);
                }
                if (accessory1 == null) {
                    accessory1 = armor;
                } else if (accessory2 == null) {
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
    public Item unequip(EquipmentSlotEnum slot) {
        Item item;
        switch (slot) {
            case MAINHAND:
                item = mainhand;
                mainhand = null;
                return item;
            case OFFHAND:
                item = offhand;
                offhand = null;
                return item;
            case HEAD:
                item = head;
                head = null;
                return item;
            case CHEST:
                item = chest;
                chest = null;
                return item;
            case LEGS:
                item = legs;
                legs = null;
                return item;
            case GLOVES:
                item = gloves;
                gloves = null;
                return item;
            case BOOTS:
                item = boots;
                boots = null;
                return item;
            case SHIELD:
                item = shield;
                shield = null;
                return item;
            case ACCESSORY1:
                item = accessory1;
                accessory1 = null;
                return item;
            case ACCESSORY2:
                item = accessory2;
                accessory2 = null;
                return item;
            default:
                System.out.println("What the hell sort of circumstances led you to seeing this message?  Freak.");
                break;
        }
        // should never be reached
        return null;
    } // end unequip

    public Takable getSlot(EquipmentSlotEnum slot) {
        switch (slot) {
            case MAINHAND:
                return mainhand;
            case OFFHAND:
                return offhand;
            case HEAD:
                return head;
            case CHEST:
                return chest;
            case LEGS:
                return legs;
            case GLOVES:
                return gloves;
            case BOOTS:
                return boots;
            case SHIELD:
                return shield;
            case ACCESSORY1:
                return accessory1;
            case ACCESSORY2:
                return accessory1;
            default:
                return null;
        }
    } // end getSlot
} // end class Equipment
