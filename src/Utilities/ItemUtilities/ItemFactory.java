package Utilities.ItemUtilities;

import Model.Effects.Effect;
import Model.Effects.EquipmentModification;
import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Interactable;
import Model.GameObject.Item.Items.Obstacle;
import Model.GameObject.Item.Items.Takables.Equippable.Armor;
import Model.GameObject.Item.Items.Takables.Equippable.Weapon;
import Model.GameObject.Item.Items.Takables.Money;
import Model.GameObject.Item.Items.Takables.Quest;
import Model.GameObject.Item.Items.Takables.Usable;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Smasher;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Sneak;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Summoner;
import Model.Inventory.EquipmentTypeEnum;
import Model.Location;
import Model.Requirement;
import Model.Stats.StatStructure;
import Model.Stats.StatsEnum;
import View.ViewUtilities.Graphics.Assets;
import View.Views.ItemView;

import java.util.HashMap;

/**
 * Created by broskj on 3/5/16.
 * <p>
 * A factory class to generate items and their assets.
 * Each item ID corresponds to its index in the items enumeration.
 */
public class ItemFactory {

    public static HashMap<Item, ItemView> initMainMap() {

        HashMap<Item, ItemView> initItems = new HashMap<>();

        // health potion
        Item hp = ItemFactory.makeItem(ItemsEnum.HEALTH_POTION, new Location(0, 0));
        initItems.put(hp, ItemFactory.makeAsset(ItemsEnum.HEALTH_POTION, hp));

        // closed treasure chest
        Item closedTreasureChest = ItemFactory.makeItem(ItemsEnum.CLOSED_TREASURE_CHEST, new Location(5, 2));
        initItems.put(closedTreasureChest, ItemFactory.makeAsset(ItemsEnum.CLOSED_TREASURE_CHEST, closedTreasureChest));

        // laser pointer
        Item laserPointer = ItemFactory.makeItem(ItemsEnum.LASER_POINTER, new Location(3, 4));
        initItems.put(laserPointer, ItemFactory.makeAsset(ItemsEnum.LASER_POINTER, laserPointer));

        // stick sword
        Item stickSword = ItemFactory.makeItem(ItemsEnum.STICK_SWORD, new Location(4, 2));
        initItems.put(stickSword, ItemFactory.makeAsset(ItemsEnum.STICK_SWORD, stickSword));

        // helmet
        Item helmet = ItemFactory.makeItem(ItemsEnum.HELMET, new Location(4, 5));
        initItems.put(helmet, ItemFactory.makeAsset(ItemsEnum.HELMET, helmet));

        // chest armor
        Item chestPlate = ItemFactory.makeItem(ItemsEnum.CHESTPLATE, new Location(5, 5));
        initItems.put(chestPlate, ItemFactory.makeAsset(ItemsEnum.CHESTPLATE, chestPlate));

        // pants
        Item pants = ItemFactory.makeItem(ItemsEnum.PANTS, new Location(6, 5));
        initItems.put(pants, ItemFactory.makeAsset(ItemsEnum.PANTS, pants));

        // guantlets
        Item gauntlets = ItemFactory.makeItem(ItemsEnum.GAUNTLETS, new Location(7, 5));
        initItems.put(gauntlets, ItemFactory.makeAsset(ItemsEnum.GAUNTLETS, gauntlets));

        // boots
        Item boots = ItemFactory.makeItem(ItemsEnum.BOOTS, new Location(8, 5));
        initItems.put(boots, ItemFactory.makeAsset(ItemsEnum.BOOTS, boots));

        // shield
        Item shield = ItemFactory.makeItem(ItemsEnum.SHIELD, new Location(9, 5));
        initItems.put(shield, ItemFactory.makeAsset(ItemsEnum.SHIELD, shield));

        // key
        Item key = ItemFactory.makeItem(ItemsEnum.CHEST_KEY, new Location(5, 3));
        initItems.put(key, ItemFactory.makeAsset(ItemsEnum.CHEST_KEY, key));

        // house
        Item house = ItemFactory.makeItem(ItemsEnum.HOUSE, new Location(2, 2));
        initItems.put(house, ItemFactory.makeAsset(ItemsEnum.HOUSE, house));

        // tuna
        /*Item tuna = ItemFactory.makeItem(ItemsEnum.TUNA, new Location(8, 8));
        initItems.put(tuna, ItemFactory.makeAsset(ItemsEnum.TUNA, tuna));*/

        // tuna
        Item sushi = ItemFactory.makeItem(ItemsEnum.SUSHI, new Location(8, 8));
        initItems.put(sushi, ItemFactory.makeAsset(ItemsEnum.SUSHI, sushi));

        //coin stacks
        Item money1 = ItemFactory.makeItem(ItemsEnum.SMALL_COIN_STACK, new Location(1, 2));
        Item money2 = ItemFactory.makeItem(ItemsEnum.MEDIUM_COIN_STACK, new Location(2, 4));
        Item money3 = ItemFactory.makeItem(ItemsEnum.LARGE_COIN_STACK, new Location(3, 5));
        initItems.put(money1, ItemFactory.makeAsset(ItemsEnum.SMALL_COIN_STACK, money1));
        initItems.put(money2, ItemFactory.makeAsset(ItemsEnum.MEDIUM_COIN_STACK, money2));
        initItems.put(money3, ItemFactory.makeAsset(ItemsEnum.LARGE_COIN_STACK, money3));

        return initItems;
    }

    public static Item[] makeRandomItems(Location location) {
        int amount = (int) (Math.random() * 5) + 1;          /* drop between 1 and 5 items */
        Item[] items = new Item[amount];
        for (int i = 0; i < amount; i++) {
            items[i] = ItemFactory.makeItem(ItemsEnum.values()[(int) (Math.random() * ItemsEnum.values().length)], location);
        }
        return items;
    } // end makeRandomItems


    public static Item makeItem(ItemsEnum itemsEnum, Location location) {
        int id = itemsEnum.ordinal();
        switch (itemsEnum) {
            case HEALTH_POTION:
                return new Usable(id,
                        "Health potion",
                        "A potion that restores health",
                        5,
                        location, new Requirement(),
                        new Effect(new StatStructure(StatsEnum.LIFE, 10)));
            case MANA_POTION:
                return new Usable(id,
                        "Mana potion",
                        "A potion that restores mana",
                        5,
                        location, new Requirement(),
                        new Effect(new StatStructure(StatsEnum.MANA, 10)));
            case STRENGTH_POTION:
                return new Usable(id,
                        "Strength potion",
                        "A potion that boosts strength",
                        8,
                        location, new Requirement(),
                        new Effect(new StatStructure(StatsEnum.STRENGTH, 10)));
            case AGILITY_POTION:
                return new Usable(id,
                        "Agility potion",
                        "A potion that boosts agility",
                        8,
                        location, new Requirement(),
                        new Effect(new StatStructure(StatsEnum.AGILITY, 10)));
            case INTELLECT_POTION:
                return new Usable(id,
                        "Intellect potion",
                        "A potion that boosts intellect",
                        8,
                        location, new Requirement(),
                        new Effect(new StatStructure(StatsEnum.INTELLECT, 10)));
            case HARDINESS_POTION:
                return new Usable(id,
                        "Hardiness potion",
                        "A potion that boosts hardiness",
                        8,
                        location, new Requirement(),
                        new Effect(new StatStructure(StatsEnum.HARDINESS, 10)));
            case EXPERIENCE_POTION:
                return new Usable(id,
                        "Experience potion",
                        "A potion that raises experience",
                        8,
                        location, new Requirement(),
                        new Effect(new StatStructure(StatsEnum.EXPERIENCE, 10)));
            case MOVEMENT_POTION:
                return new Usable(id,
                        "Movement potion",
                        "A potion that raises movement",
                        8,
                        location, new Requirement(),
                        new Effect(new StatStructure(StatsEnum.MOVEMENT, 10)));
            case STICK_SWORD:
                return new Weapon(id,
                        "Stick Sword",
                        "A sword made from a stick",
                        10,
                        location,
                        new Requirement(new Smasher()),
                        EquipmentTypeEnum.ONE_HANDED,
                        new EquipmentModification(new StatStructure(StatsEnum.OFFENSIVE_RATING, 3)));
            case STICK_GREATSWORD:
                return new Weapon(id,
                        "Stick Greatsword",
                        "A sword made from a big stick",
                        15,
                        location,
                        new Requirement(new Smasher()),
                        EquipmentTypeEnum.TWO_HANDED,
                        new EquipmentModification(new StatStructure(StatsEnum.OFFENSIVE_RATING, 6)));
            case SWORDFISH_DAGGER:
                return new Weapon(id,
                        "Swordfish dagger",
                        "A dagger made from a swordfish bill",
                        65,
                        location,
                        new Requirement(5, new Smasher()),
                        EquipmentTypeEnum.ONE_HANDED,
                        new EquipmentModification(new StatStructure(StatsEnum.OFFENSIVE_RATING, 5)));
            case SWORDFISH_LANCE:
                return new Weapon(id,
                        "Swordfish lance",
                        "A large lance made from a swordfish bill",
                        100,
                        location,
                        new Requirement(5, new Smasher()),
                        EquipmentTypeEnum.TWO_HANDED,
                        new EquipmentModification(new StatStructure(StatsEnum.OFFENSIVE_RATING, 10)));
            case PUFFER_FISH_MACE:
                return new Weapon(id,
                        "Puffer fish mace",
                        "A mace made from a deadly puffer fish",
                        225,
                        location,
                        new Requirement(15, new Smasher()),
                        EquipmentTypeEnum.ONE_HANDED,
                        new EquipmentModification(new StatStructure(StatsEnum.OFFENSIVE_RATING, 9)));
            case PUFFER_FISH_FLAIL:
                return new Weapon(id,
                        "Puffer fish mace",
                        "A heavy mace made from a deadly puffer fish",
                        350,
                        location,
                        new Requirement(15, new Smasher()),
                        EquipmentTypeEnum.TWO_HANDED,
                        new EquipmentModification(new StatStructure(StatsEnum.OFFENSIVE_RATING, 18)));
            case MOUSE_ON_A_STRING_WAND:
                return new Weapon(id,
                        "Mouse on a string wand",
                        "A magical stick with a mouse attached",
                        30,
                        location,
                        new Requirement(new Summoner()),
                        EquipmentTypeEnum.ONE_HANDED,
                        new EquipmentModification(new StatStructure(StatsEnum.OFFENSIVE_RATING, 2)));
            case CATNIP_STAFF:
                return new Weapon(id,
                        "Catnip staff",
                        "A staff imbued with the magic of catnip",
                        45,
                        location,
                        new Requirement(new Summoner()),
                        EquipmentTypeEnum.TWO_HANDED,
                        new EquipmentModification(new StatStructure(StatsEnum.OFFENSIVE_RATING, 4)));
            case HAIRBALL:
                return new Weapon(id,
                        "Hairball",
                        "A hairball projectile",
                        12,
                        location,
                        new Requirement(new Sneak()),
                        EquipmentTypeEnum.TWO_HANDED,
                        new EquipmentModification(new StatStructure(StatsEnum.OFFENSIVE_RATING, 4)));
            case LASER_POINTER:
                return new Weapon(id,
                        "Laser pointer",
                        "A powerful laser pointer",
                        80,
                        location,
                        new Requirement(5, new Sneak()),
                        EquipmentTypeEnum.TWO_HANDED,
                        new EquipmentModification(new StatStructure(StatsEnum.OFFENSIVE_RATING, 8)));
            case FISH_BOOMERANG:
                return new Weapon(id,
                        "Fish boomerang",
                        "A sharp fish carcass boomerang",
                        300,
                        location,
                        new Requirement(15, new Sneak()),
                        EquipmentTypeEnum.TWO_HANDED,
                        new EquipmentModification(new StatStructure(StatsEnum.OFFENSIVE_RATING, 14)));
            case CHEST_KEY:
                return new Quest(id,
                        "Chest key",
                        "A key to a chest",
                        25,
                        location,
                        new Requirement(),
                        new Effect());
            case OPEN_TREASURE_CHEST:
                return new Interactable(id,
                        "Unlocked chest",
                        "An unlocked chest",
                        -1,
                        location,
                        new Requirement());
            case CLOSED_TREASURE_CHEST:
                return new Interactable(id,
                        "Closed chest",
                        "A locked chest",
                        -1,
                        location,
                        new Requirement(ItemsEnum.CHEST_KEY));
            case DOOR_KEY:
                return new Quest(id,
                        "Door key",
                        "A key to a door",
                        25,
                        location,
                        new Requirement(),
                        new Effect());
            case OPEN_DOOR:
                return new Interactable(id,
                        "Open door",
                        "An open door",
                        -1,
                        location,
                        new Requirement());
            case CLOSED_DOOR:
                return new Interactable(id,
                        "Closed door",
                        "A locked door",
                        -1,
                        location,
                        new Requirement(ItemsEnum.DOOR_KEY));
            case HELMET:
                return new Armor(id,
                        "Helmet",
                        "To protect your head",
                        30,
                        location,
                        new Requirement(),
                        EquipmentTypeEnum.HEAD,
                        new EquipmentModification(new StatStructure(StatsEnum.ARMOR_RATING, 3)));
            case CHESTPLATE:
                return new Armor(id,
                        "Chestplate",
                        "A sturdy piece of chest armor",
                        50,
                        location,
                        new Requirement(),
                        EquipmentTypeEnum.CHEST,
                        new EquipmentModification(new StatStructure(StatsEnum.ARMOR_RATING, 5)));
            case PANTS:
                return new Armor(id,
                        "Pants",
                        "A set of leather leggings",
                        10,
                        location,
                        new Requirement(),
                        EquipmentTypeEnum.LEGS,
                        new EquipmentModification(new StatStructure(StatsEnum.ARMOR_RATING, 1)));
            case PLATELEGS:
                return new Armor(id,
                        "Platelegs",
                        "A set of armored leggings",
                        40,
                        location,
                        new Requirement(),
                        EquipmentTypeEnum.LEGS,
                        new EquipmentModification(new StatStructure(StatsEnum.ARMOR_RATING, 4)));
            case GAUNTLETS:
                return new Armor(id,
                        "Gauntlets",
                        "Made to protect your hands",
                        20,
                        location,
                        new Requirement(),
                        EquipmentTypeEnum.GLOVES,
                        new EquipmentModification(new StatStructure(StatsEnum.ARMOR_RATING, 2)));
            case BOOTS:
                return new Armor(id,
                        "Boots",
                        "Foot protection",
                        20,
                        location,
                        new Requirement(),
                        EquipmentTypeEnum.BOOTS,
                        new EquipmentModification(new StatStructure(StatsEnum.ARMOR_RATING, 2)));
            case SHIELD:
                return new Armor(id,
                        "Shield",
                        "A thick shield",
                        40,
                        location,
                        new Requirement(),
                        EquipmentTypeEnum.SHIELD,
                        new EquipmentModification(new StatStructure(StatsEnum.ARMOR_RATING, 4)));
            case MILDLY_COOL_RING:
                return new Armor(id,
                        "Mildly cool ring",
                        "A mildly cool imbued ring",
                        125,
                        location,
                        new Requirement(),
                        EquipmentTypeEnum.ACCESSORY,
                        new EquipmentModification(new StatStructure(
                                new StatsEnum[]{StatsEnum.AGILITY, StatsEnum.INTELLECT},
                                new int[]{2, 2})));
            case DOPE_RING:
                return new Armor(id,
                        "Dope ring",
                        "A dope imbued ring",
                        250,
                        location,
                        new Requirement(),
                        EquipmentTypeEnum.ACCESSORY,
                        new EquipmentModification(new StatStructure(
                                new StatsEnum[]{StatsEnum.STRENGTH, StatsEnum.AGILITY, StatsEnum.INTELLECT,
                                        StatsEnum.HARDINESS},
                                new int[]{4, 4, 4, 4})));
            case HOUSE:
                return new Obstacle(id,
                        "House",
                        "Old farmer Joe's house",
                        -1,
                        location);
            case SMALL_COIN_STACK:
                return new Money(id,
                        "Small coin stack",
                        "A small amount of money",
                        1,
                        location,
                        new Requirement(),
                        new Effect(),
                        (int) (Math.random() * 10) + 1);         /* random amount between 1 and 10 */
            case MEDIUM_COIN_STACK:
                return new Money(id,
                        "Medium coin stack",
                        "A medium amount of money",
                        1,
                        location,
                        new Requirement(),
                        new Effect(),
                        (int) (Math.random() * 25) + 1);         /* random amount between 1 and 25 */
            case LARGE_COIN_STACK:
                return new Money(id,
                        "Large coin stack",
                        "A Large amount of money",
                        1,
                        location,
                        new Requirement(),
                        new Effect(),
                        (int)(Math.random() * 50) + 1);         /* random amount between 1 and 50 */
            case TUNA:
                return new Quest(id,
                        "Can of Tuna",
                        "???",
                        5,
                        location,
                        new Requirement(),
                        null);
            case SUSHI:
                return new Quest(id,
                        "Yummy Sushi",
                        "Could be used to attract a Dave",
                        5,
                        location,
                        new Requirement(),
                        null);
            default:
                return null;
        }
    } // end makeItem

    public static ItemView makeAsset(ItemsEnum itemsEnum, Item item) {
        switch (itemsEnum) {
            case BAGOFITEMS:
                return new ItemView(item, Assets.BAGOFITEMS);
            case HEALTH_POTION:
                return new ItemView(item, Assets.HEALTH_POTION);
            case MANA_POTION:
                return new ItemView(item, Assets.MANA_POTION);
            case STRENGTH_POTION:
            case AGILITY_POTION:
            case INTELLECT_POTION:
            case HARDINESS_POTION:
            case EXPERIENCE_POTION:
            case MOVEMENT_POTION:
            case STICK_SWORD:
                return new ItemView(item, Assets.STICK_SWORD);
            case STICK_GREATSWORD:
            case SWORDFISH_DAGGER:
            case SWORDFISH_LANCE:
            case PUFFER_FISH_MACE:
            case PUFFER_FISH_FLAIL:
            case MOUSE_ON_A_STRING_WAND:
            case CATNIP_STAFF:
            case HAIRBALL:
            case LASER_POINTER:
                return new ItemView(item, Assets.LASER_POINTER);
            case FISH_BOOMERANG:
            case CHEST_KEY:
                return new ItemView(item, Assets.CHEST_KEY);
            case OPEN_TREASURE_CHEST:
                return new ItemView(item, Assets.OPEN_TREASURE_CHEST);
            case CLOSED_TREASURE_CHEST:
                return new ItemView(item, Assets.CLOSED_TREASURE_CHEST);
            case DOOR_KEY:
            case OPEN_DOOR:
            case CLOSED_DOOR:
            case HELMET:
                return new ItemView(item, Assets.HELMET);
            case CHESTPLATE:
                return new ItemView(item, Assets.CHESTPLATE);
            case PANTS:
                return new ItemView(item, Assets.PANTS);
            case PLATELEGS:
            case GAUNTLETS:
                return new ItemView(item, Assets.GAUNTLETS);
            case BOOTS:
                return new ItemView(item, Assets.BOOTS);
            case SHIELD:
                return new ItemView(item, Assets.SHIELD);
            case HOUSE:
                return new ItemView(item, Assets.HOUSE);
            case TUNA:
                return new ItemView(item, Assets.TUNA);
            case SUSHI:
                return new ItemView(item, Assets.SUSHI);
            case SMALL_COIN_STACK:
            case MEDIUM_COIN_STACK:
            case LARGE_COIN_STACK:
                return new ItemView(item, Assets.CATNIP);
            default:
                return null;
        }
    } // end makeAsset
} // end class ItemFactory
