package Utilities.MobileObjectUtilities;

import Model.GameObject.Item.Item;
import Model.GameObject.MobileObjects.Entities.AI.NPCController;
import Model.GameObject.MobileObjects.Entities.Characters.NPC;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Smasher;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Inventory.Equipment;
import Model.Inventory.Inventory;
import Model.Inventory.Pack;
import Model.Location;
import Utilities.ItemUtilities.ItemFactory;
import Utilities.ItemUtilities.ItemsEnum;
import View.ViewUtilities.Graphics.Assets;
import View.Views.MobileObjectView;

/**
 * Created by broskj on 3/6/16.
 */
public class NPCFactory {
    public static MobileObject makeNPC(MobileObjectEnum npcEnum, Location location) {
        switch (npcEnum) {
            case KITTEN:
                return new NPC(location,
                        new Smasher(),
                        new Inventory(
                                new Pack(
                                        new Item[] {ItemFactory.makeItem(ItemsEnum.HEALTH_POTION, location)},
                                        10),
                                new Equipment()),
                        new NPCController());
            case SMALL_CAT:
            case FAT_CAT:
            case CORGI_SHOPKEEPER:
            case WOLF_SHOPKEEPER:
            case DAVE_PET:
               return new();
            case SHEEP_VEHICLE:
            case LOW_RIDER:
            case SADDLED_DOG:
            case LASER:
            case BOOMERANG:
            default:
                break; /* return null; */
        }
        return null;
    } // end makeNPC

    public static MobileObjectView makeAsset(MobileObjectEnum npcEnum, MobileObject mobileObject) {
        switch (npcEnum) {
            case KITTEN:
                return new MobileObjectView(mobileObject, Assets.PLAYER);
            case SMALL_CAT:
            case FAT_CAT:
            case CORGI_SHOPKEEPER:
            case WOLF_SHOPKEEPER:
            case DAVE_PET:
            case SHEEP_VEHICLE:
            case LOW_RIDER:
            case SADDLED_DOG:
            case LASER:
            case BOOMERANG:
            default:
                break;
        }
        return null;
    } // end makeAsset
} // end class NPCFactory
