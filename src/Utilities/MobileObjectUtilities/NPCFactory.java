package Utilities.MobileObjectUtilities;

import Model.GameObject.MobileObjects.Entities.Entity;
import View.Views.MobileObjectView;

/**
 * Created by broskj on 3/6/16.
 */
public class NPCFactory {
    public static void /*Entity*/ makeNPC(MobileObjectEnum npcEnum) {
        switch (npcEnum) {
            case KITTEN:
            case SMALL_CAT:
            case FAT_CAT:
            case CORGI_SHOPKEEPER:
            case WOLF_SHOPKEEPER:
            case DAVE_PET:
            default:
                break; /* return null; */
        }
    } // end makeNPC

    public static MobileObjectView makeAsset(MobileObjectEnum npcEnum, Entity entity) {
        switch (npcEnum) {
            case KITTEN:
            case SMALL_CAT:
            case FAT_CAT:
            case CORGI_SHOPKEEPER:
            case WOLF_SHOPKEEPER:
            case DAVE_PET:
            default:
                break;
        }
        return null;
    } // end makeAsset
} // end class NPCFactory
