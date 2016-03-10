package Utilities.MobileObjectUtilities;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Takables.Equippable.Weapon;
import Model.GameObject.MobileObjects.Entities.AI.NPCController;
import Model.GameObject.MobileObjects.Entities.AI.PetController;

import Model.GameObject.MobileObjects.Entities.Characters.NPC;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Smasher;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.GameObject.MobileObjects.Entities.Pet;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Inventory.Equipment;
import Model.Inventory.Inventory;
import Model.Inventory.Pack;
import Model.Location;
import Model.Stats.PetStats;

import Utilities.ItemUtilities.ItemFactory;
import Utilities.ItemUtilities.ItemsEnum;
import View.ViewUtilities.Graphics.Assets;
import View.Views.MobileObjectView;

import java.util.HashMap;

/**
 * Created by broskj on 3/6/16.
 */

public class NPCFactory {

    public static HashMap<MobileObject, MobileObjectView> Init(){

        HashMap<MobileObject, MobileObjectView> objects = new HashMap<>();

        // pet
        MobileObject pet = makeNPC(MobileObjectEnum.DAVE_PET, new Location(3, 3), new Pack());
        objects.put(pet, makeAsset(MobileObjectEnum.DAVE_PET, pet));

        // Enemy one
        MobileObject enemy1 = makeNPC(MobileObjectEnum.KITTEN, new Location(8, 3), new Pack());
        objects.put(enemy1, makeAsset(MobileObjectEnum.KITTEN, enemy1));


        return objects;
    }

    public static MobileObject makeNPC(MobileObjectEnum npcEnum, Location location, Pack pack) {
        switch (npcEnum) {
            case PLAYER:
                return new Player(location, new Smasher(), new Inventory());
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
               return new Pet(new PetController(), location, new PetStats(), pack, false);
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

    public static Player Player(){
        // player
        Player player = (Player)makeNPC(MobileObjectEnum.PLAYER, new Location(1, 0), new Pack());
        player.equip((Weapon) ItemFactory.makeItem(ItemsEnum.SWORDFISH_DAGGER, player.getLocation()));
        return player;
    }

    public static MobileObjectView makeAsset(MobileObjectEnum npcEnum, MobileObject mobileObject) {
        switch (npcEnum) {
            case KITTEN:
               return new MobileObjectView(mobileObject, Assets.NPC.get(0));
            case SMALL_CAT:
            case FAT_CAT:
            case CORGI_SHOPKEEPER:
            case WOLF_SHOPKEEPER:
            case DAVE_PET:
                return new MobileObjectView(mobileObject, Assets.PET.get(0));
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
