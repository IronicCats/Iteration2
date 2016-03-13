package Utilities.MobileObjectUtilities;

import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Takables.Equippable.Weapon;
import Model.GameObject.MobileObjects.Entities.AI.EnemyController;
import Model.GameObject.MobileObjects.Entities.AI.FriendlyController;
import Model.GameObject.MobileObjects.Entities.AI.PetController;
import Model.GameObject.MobileObjects.Entities.Characters.HostileNPC;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Smasher;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.GameObject.MobileObjects.Entities.Characters.Shopkeeper;
import Model.GameObject.MobileObjects.Entities.Pet;
import Model.GameObject.MobileObjects.MobileObject;
import Model.GameObject.MobileObjects.Vehicle;
import Model.Inventory.Equipment;
import Model.Inventory.Inventory;
import Model.Inventory.Pack;
import Model.Location;
import Model.Map.Map;
import Model.Stats.PetStats;
import Model.Stats.Stats;
import State.States.GameState.GameState;
import Utilities.ItemUtilities.ItemFactory;
import Utilities.ItemUtilities.ItemsEnum;
import View.ViewUtilities.Graphics.Assets;
import View.Views.MobileObjectView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by broskj on 3/6/16.
 */

public class MobileObjectFactory {

    public static HashMap<MobileObject, MobileObjectView> Init(Map map, Player player) {

        HashMap<MobileObject, MobileObjectView> objects = new HashMap<>();

        // Enemy zero
        HostileNPC enemy = (HostileNPC)makeNPC(MobileObjectEnum.KITTEN, new Location(8, 3), map, player);
        enemy.getController().setBaseLoc(new Location(0,0));
        enemy.getController().setEnemy(enemy);
        enemy.getController().setTarget(player);
        enemy.getStats().setLife(2);
        objects.put(enemy, makeAsset(MobileObjectEnum.KITTEN, enemy));

        // Enemy one
        HostileNPC enemy1 = (HostileNPC)makeNPC(MobileObjectEnum.BLUE, new Location(6, 3), map, player);
        enemy1.getController().setTarget(player);
        enemy1.getController().setEnemy(enemy1);
        enemy1.getController().setBaseLoc(new Location(4,5));
        objects.put(enemy1, makeAsset(MobileObjectEnum.BLUE, enemy1));

        // Shopkeeper one
        Shopkeeper shopkeeper1 = (Shopkeeper) makeNPC(MobileObjectEnum.CORGI_SHOPKEEPER, new Location(2, 3), map, player);
        shopkeeper1.getController().setBaseLoc(new Location(2, 3));
        enemy.getController().setTarget(player);
        objects.put(shopkeeper1, makeAsset(MobileObjectEnum.CORGI_SHOPKEEPER, shopkeeper1));
        enemy.getStats().setLife(2);

        // pet
        Pet davePet = (Pet)makeNPC(MobileObjectEnum.DAVE_PET, new Location(13,13), map, player);
        davePet.getController().setTarget(player);
        objects.put(davePet, makeAsset(MobileObjectEnum.DAVE_PET, davePet));

        //vehicle
        Vehicle roomba = (Vehicle) makeNPC(MobileObjectEnum.ROOMBA, new Location(1,1, 45), map, player);
        objects.put(roomba, makeAsset(MobileObjectEnum.ROOMBA,roomba));
        roomba.getStats().setMovement(12);

        return objects;
    }

    public static MobileObject makeNPC(MobileObjectEnum npcEnum, Location location, Map map, Player player) {
        int id = npcEnum.ordinal();
        switch (npcEnum) {
            case KITTEN:
                return new HostileNPC(location,
                        id,
                        new Smasher(),
                        new Inventory(
                                new Pack(
                                        ItemFactory.makeRandomItems(location),
                                        (int) (Math.random() * 10) + 1),
                                new Equipment()),
                        new EnemyController(map));
            case SMALL_CAT:
            case BLUE:
                return new HostileNPC(location,
                        id,
                        new Smasher(),
                        new Inventory(
                                new Pack(
                                        ItemFactory.makeRandomItems(location),
                                        (int) (Math.random() * 25) + 1),
                                new Equipment()),
                        new EnemyController(map));
            case FAT_CAT:
                return new HostileNPC(location,
                        id,
                        new Smasher(),
                        new Inventory(
                                new Pack(
                                        ItemFactory.makeRandomItems(location),
                                        (int) (Math.random() * 50) + 1),
                                new Equipment()),
                        new EnemyController(map));
            case CORGI_SHOPKEEPER:
                return new Shopkeeper(location,
                        id,
                        new Smasher(),
                        new Inventory(
                                new Pack(new Item[]{
                                        ItemFactory.makeItem(ItemsEnum.HEALTH_POTION, location),
                                        ItemFactory.makeItem(ItemsEnum.HEALTH_POTION, location),
                                        ItemFactory.makeItem(ItemsEnum.HEALTH_POTION, location),
                                        ItemFactory.makeItem(ItemsEnum.HEALTH_POTION, location),
                                        ItemFactory.makeItem(ItemsEnum.MANA_POTION, location),
                                        ItemFactory.makeItem(ItemsEnum.MANA_POTION, location),
                                }, 500),
                                new Equipment()
                        ),
                        new FriendlyController(map),
                        new ArrayList<String>() {{
                            add("Corgi stuff for sale");
                            add("More corgi stuff for sale");
                            add("hello world");
                            add("foo");
                            add("bar");
                        }});
            case WOLF_SHOPKEEPER:
                return new Shopkeeper(location,
                        id,
                        new Smasher(),
                        new Inventory(
                                new Pack(new Item[]{
                                        ItemFactory.makeItem(ItemsEnum.STICK_SWORD, location),
                                        ItemFactory.makeItem(ItemsEnum.STICK_GREATSWORD, location),
                                        ItemFactory.makeItem(ItemsEnum.MOUSE_ON_A_STRING_WAND, location),
                                        ItemFactory.makeItem(ItemsEnum.HAIRBALL, location)
                                }, 500),
                                new Equipment()
                        ),
                        new FriendlyController(map),
                        new ArrayList<String>() {{
                            add("Wolf stuff for sale");
                            add("More wolf stuff for sale");
                            add("dlrow olleh");
                            add("oof");
                            add("rab");
                        }});
            case DAVE_PET:
               return new Pet(new PetController(map), location, 0, new PetStats(), new Pack(), player, ItemsEnum.SUSHI);
            case ROOMBA:
                return new Vehicle(location,0,new Stats(50));
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

    public static Player Player() {
        // player
        Player player = new Player(new Location(0, 1), 2, new Smasher(), new Inventory());
        //player.equip((Weapon) ItemFactory.makeItem(ItemsEnum.SWORDFISH_DAGGER, player.getLocation()));
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
                return new MobileObjectView(mobileObject, Assets.BLUE_NPC);
            case WOLF_SHOPKEEPER:
            case DAVE_PET:
                return new MobileObjectView(mobileObject, Assets.DAVE_PET);
            case ROOMBA:
                return new MobileObjectView(mobileObject, Assets.ROOMBA);
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
} // end class MobileObjectFactory
