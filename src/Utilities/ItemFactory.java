package Utilities;

import Model.Effects.Effect;
import Model.Effects.EquipmentModification;
import Model.GameObject.Item.Item;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.*;
import Model.Location;
import Model.Requirement;
import Model.Stats.StatStructure;
import Model.Stats.StatsEnum;
import View.ViewUtilities.Graphics.Assets;
import View.Views.ItemView;
import Utilities.ItemUtilities.CreateItem;

/**
 * Created by broskj on 3/5/16.
 *
 * A factory class to generate items and their assets.
 * Each item ID corresponds to its index in the items enumeration.
 */
public class ItemFactory {
    public static Item makeItem(ItemsEnum itemsEnum, Location location) {
        int id = itemsEnum.ordinal();
        switch(itemsEnum) {
            case HEALTH_POTION:
                return CreateItem.addUsable(id,
                        "Health potion",
                        "A potion that restores health",
                        location, new Requirement(),
                        new Effect(new StatStructure(StatsEnum.LIFE, 10)));
            case MANA_POTION:
                return CreateItem.addUsable(id,
                        "Mana potion",
                        "A potion that restores mana",
                        location, new Requirement(),
                        new Effect(new StatStructure(StatsEnum.MANA, 10)));
            case STRENGTH_POTION:
                return CreateItem.addUsable(id,
                        "Strength potion",
                        "A potion that boosts strength",
                        location, new Requirement(),
                        new Effect(new StatStructure(StatsEnum.STRENGTH, 10)));
            case AGILITY_POTION:
                return CreateItem.addUsable(id,
                        "Agility potion",
                        "A potion that boosts agility",
                        location, new Requirement(),
                        new Effect(new StatStructure(StatsEnum.AGILITY, 10)));
            case INTELLECT_POTION:
                return CreateItem.addUsable(id,
                        "Intellect potion",
                        "A potion that boosts intellect",
                        location, new Requirement(),
                        new Effect(new StatStructure(StatsEnum.INTELLECT, 10)));
            case HARDINESS_POTION:
                return CreateItem.addUsable(id,
                        "Hardiness potion",
                        "A potion that boosts hardiness",
                        location, new Requirement(),
                        new Effect(new StatStructure(StatsEnum.HARDINESS, 10)));
            case EXPERIENCE_POTION:
                return CreateItem.addUsable(id,
                        "Experience potion",
                        "A potion that raises experience",
                        location, new Requirement(),
                        new Effect(new StatStructure(StatsEnum.EXPERIENCE, 10)));
            case MOVEMENT_POTION:
                return CreateItem.addUsable(id,
                        "Movement potion",
                        "A potion that raises movement",
                        location, new Requirement(),
                        new Effect(new StatStructure(StatsEnum.MOVEMENT, 10)));
            case STICK_SWORD:
                return CreateItem.addWeapon(id,
                        "Stick Sword",
                        "A sword made from a stick",
                        location,
                        new Requirement(new Smasher()),
                        new EquipmentModification(new StatStructure(StatsEnum.OFFENSIVE_RATING, 3)),
                        1);
            case STICK_GREATSWORD:
                return CreateItem.addWeapon(id,
                        "Stick Greatsword",
                        "A sword made from a big stick",
                        location,
                        new Requirement(new Smasher()),
                        new EquipmentModification(new StatStructure(StatsEnum.OFFENSIVE_RATING, 6)),
                        2);
            case SWORDFISH_DAGGER:
                return CreateItem.addWeapon(id,
                        "Swordfish dagger",
                        "A dagger made from a swordfish bill",
                        location,
                        new Requirement(5, new Smasher()),
                        new EquipmentModification(new StatStructure(StatsEnum.OFFENSIVE_RATING, 5)),
                        1);
            case SWORDFISH_LANCE:
                return CreateItem.addWeapon(id,
                        "Swordfish lance",
                        "A large lance made from a swordfish bill",
                        location,
                        new Requirement(5, new Smasher()),
                        new EquipmentModification(new StatStructure(StatsEnum.OFFENSIVE_RATING, 10)),
                        2);
            case PUFFER_FISH_MACE:
                return CreateItem.addWeapon(id,
                        "Puffer fish mace",
                        "A mace made from a deadly puffer fish",
                        location,
                        new Requirement(15, new Smasher()),
                        new EquipmentModification(new StatStructure(StatsEnum.OFFENSIVE_RATING, 9)),
                        1);
            case PUFFER_FISH_FLAIL:
                return CreateItem.addWeapon(id,
                        "Puffer fish mace",
                        "A heavy mace made from a deadly puffer fish",
                        location,
                        new Requirement(15, new Smasher()),
                        new EquipmentModification(new StatStructure(StatsEnum.OFFENSIVE_RATING, 18)),
                        2);
            case MOUSE_ON_A_STRING_WAND:
                return CreateItem.addWeapon(id,
                        "Mouse on a string wand",
                        "A magical stick with a mouse attached",
                        location,
                        new Requirement(new Summoner()),
                        new EquipmentModification(new StatStructure(StatsEnum.OFFENSIVE_RATING, 3)),
                        1);
            case CATNIP_STAFF:
                return CreateItem.addWeapon(id,
                        "Catnip staff",
                        "A staff imbued with the magic of catnip",
                        location,
                        new Requirement(new Summoner()),
                        new EquipmentModification(new StatStructure(StatsEnum.OFFENSIVE_RATING, 3)),
                        2);
            case HAIRBALL:
                return CreateItem.addWeapon(id,
                        "Hairball",
                        "A hairball projectile",
                        location,
                        new Requirement(new Sneak()),
                        new EquipmentModification(new StatStructure(StatsEnum.OFFENSIVE_RATING, 4)),
                        2);
            case LASER_POINTER:
                return CreateItem.addWeapon(id,
                        "Laser pointer",
                        "A powerful laser pointer",
                        location,
                        new Requirement(5, new Sneak()),
                        new EquipmentModification(new StatStructure(StatsEnum.OFFENSIVE_RATING, 8)),
                        2);
            case FISH_BOOMERANG:
                return CreateItem.addWeapon(id,
                        "Fish boomerang",
                        "A sharp fish carcass boomerang",
                        location,
                        new Requirement(15, new Sneak()),
                        new EquipmentModification(new StatStructure(StatsEnum.OFFENSIVE_RATING, 14)),
                        2);
            case CHEST_KEY:
                return CreateItem.addQuest(id,
                        "Chest key",
                        "A key to a chest",
                        location,
                        new Requirement(),
                        new Effect());
            case OPEN_TREASURE_CHEST:
                return CreateItem.addInteractable(id,
                        "Unlocked chest",
                        "An unlocked chest",
                        location,
                        new Requirement());
            case CLOSED_TREASURE_CHEST:
                return CreateItem.addInteractable(id,
                        "Closed chest",
                        "A locked chest",
                        location,
                        new Requirement(makeItem(ItemsEnum.CHEST_KEY, null)));
            case DOOR_KEY:
                return CreateItem.addQuest(id,
                        "Door key",
                        "A key to a door",
                        location,
                        new Requirement(),
                        new Effect());
            case OPEN_DOOR:
                return CreateItem.addInteractable(id,
                        "Open door",
                        "An open door",
                        location,
                        new Requirement());
            case CLOSED_DOOR:
                return CreateItem.addInteractable(id,
                        "Closed door",
                        "A locked door",
                        location,
                        new Requirement(makeItem(ItemsEnum.DOOR_KEY, null)));
            case HELMET:
                return CreateItem.addArmor(id,
                        "Helmet",
                        "To protect your head",
                        location,
                        new Requirement(),
                        new EquipmentModification(new StatStructure(StatsEnum.ARMOR_RATING, 3)));
            case CHESTPLATE:
                return CreateItem.addArmor(id,
                        "Chestplate",
                        "A sturdy piece of chest armor",
                        location,
                        new Requirement(),
                        new EquipmentModification(new StatStructure(StatsEnum.ARMOR_RATING, 5)));
            case PLATELEGS:
                return CreateItem.addArmor(id,
                        "Platelegs",
                        "A set of armored leggings",
                        location,
                        new Requirement(),
                        new EquipmentModification(new StatStructure(StatsEnum.ARMOR_RATING, 4)));
            case GAUNTLETS:
                return CreateItem.addArmor(id,
                        "Gauntlets",
                        "Made to protect your hands",
                        location,
                        new Requirement(),
                        new EquipmentModification(new StatStructure(StatsEnum.ARMOR_RATING, 2)));
            case BOOTS:
                return CreateItem.addArmor(id,
                        "Boots",
                        "Foot protection",
                        location,
                        new Requirement(),
                        new EquipmentModification(new StatStructure(StatsEnum.ARMOR_RATING, 2)));
            case SHIELD:
                return CreateItem.addArmor(id,
                        "Shield",
                        "A thick shield",
                        location,
                        new Requirement(),
                        new EquipmentModification(new StatStructure(StatsEnum.ARMOR_RATING, 4)));
            default:
                return null;
        }
    } // end makeItem

    public static ItemView makeAsset(ItemsEnum itemsEnumm, Item item) {
        switch(itemsEnumm) {
            case HEALTH_POTION:
            case MANA_POTION:
            case STRENGTH_POTION:
            case AGILITY_POTION:
            case INTELLECT_POTION:
            case HARDINESS_POTION:
            case EXPERIENCE_POTION:
            case MOVEMENT_POTION:
            case STICK_SWORD:
            case STICK_GREATSWORD:
            case SWORDFISH_DAGGER:
            case SWORDFISH_LANCE:
            case PUFFER_FISH_MACE:
            case PUFFER_FISH_FLAIL:
            case MOUSE_ON_A_STRING_WAND:
            case CATNIP_STAFF:
            case HAIRBALL:
            case LASER_POINTER:
            case FISH_BOOMERANG:
            case CHEST_KEY:
            case OPEN_TREASURE_CHEST:
            case CLOSED_TREASURE_CHEST:
            case DOOR_KEY:
            case OPEN_DOOR:
            case CLOSED_DOOR:
            case HELMET:
            case CHESTPLATE:
            case PLATELEGS:
            case GAUNTLETS:
            case BOOTS:
            case SHIELD:
            default:
                return new ItemView(item, Assets.POTION);
        }
    } // end makeAsset
} // end class ItemFactory
