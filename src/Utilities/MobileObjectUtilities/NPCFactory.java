package Utilities.MobileObjectUtilities;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Takables.Equippable.Weapon;
import Model.GameObject.MobileObjects.Entities.AI.NPCController;
import Model.GameObject.MobileObjects.Entities.AI.PetController;
import Model.Map.Map;
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

import State.State;
import State.States.GameState.GameState;
import Utilities.ItemUtilities.ItemFactory;
import Utilities.ItemUtilities.ItemsEnum;
import View.ViewUtilities.Graphics.Assets;
import View.Views.MobileObjectView;

import java.util.HashMap;

/**
 * Created by broskj on 3/6/16.
 */

public class NPCFactory {

    public static HashMap<MobileObject, MobileObjectView> Init(Map map){

        HashMap<MobileObject, MobileObjectView> objects = new HashMap<>();
        // pet
        //MobileObject pet = makeNPC(MobileObjectEnum.DAVE_PET, new Location(3, 3), new Pack(), map);
        //objects.put(pet, makeAsset(MobileObjectEnum.DAVE_PET, pet));

        // Enemy zero
        NPC enemy = (NPC)makeNPC(MobileObjectEnum.KITTEN, new Location(8, 3), map);
        enemy.getController().setBaseLoc(new Location(0,0));
        objects.put(enemy, makeAsset(MobileObjectEnum.KITTEN, enemy));

        // Enemy one
        NPC enemy1 = (NPC)makeNPC(MobileObjectEnum.BLUE, new Location(6, 3), map);
        enemy1.getController().setBaseLoc(new Location(4,5));
        objects.put(enemy1, makeAsset(MobileObjectEnum.BLUE, enemy1));

        //enemy.getController().setMobileObject(player);
        //enemy.getController().setBaseLoc(new Location(0,0));
        //enemy1.getController().setBaseLoc(new Location(0,0));

        if(GameState.getPlayer() != null) {
            //enemy1.getController().setMobileObject(State.GAMESTATE.getPlayer());
        }

        return objects;
    }

    public static MobileObject makeNPC(MobileObjectEnum npcEnum, Location location, Map map) {
        switch (npcEnum) {
            case KITTEN:
                return new NPC(location,
                        new Smasher(),
                        new Inventory(
                                new Pack(
                                        new Item[] {ItemFactory.makeItem(ItemsEnum.HEALTH_POTION, location)},
                                        10),
                                new Equipment()),
                        new NPCController(map));
            case SMALL_CAT:
            case BLUE:
                return new NPC(location, new Smasher(), new Inventory(), new NPCController(map));
            case FAT_CAT:
            case CORGI_SHOPKEEPER:
            case WOLF_SHOPKEEPER:
            case DAVE_PET:
               return new Pet(new PetController(map), location, new PetStats(), new Pack(), false);
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
        Player player = new Player(new Location(0,1), new Smasher(), new Inventory());;
        player.equip((Weapon) ItemFactory.makeItem(ItemsEnum.SWORDFISH_DAGGER, player.getLocation()));
        return player;
    }

    public static MobileObjectView makeAsset(MobileObjectEnum npcEnum, MobileObject mobileObject) {
        switch (npcEnum) {
            case KITTEN:
               return new MobileObjectView(mobileObject, Assets.CAT_NPC);
            case BLUE:
                return new MobileObjectView(mobileObject, Assets.BLUE_NPC);
            case SMALL_CAT:
            case FAT_CAT:
            case PLAYER:
                return new MobileObjectView(mobileObject, Assets.PLAYER);
            case CORGI_SHOPKEEPER:
            case WOLF_SHOPKEEPER:
            case DAVE_PET:
                return new MobileObjectView(mobileObject, Assets.PET);
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
