package View.ViewUtilities.Graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Aidan on 3/1/2016.
 */
public class Assets {

    private static final int width = 32, height = 32;


    public static BufferedImage FOGTILE;
    public static BufferedImage HALFFOGTILE;
    public static BufferedImage GRASSHEXTILE;
    public static BufferedImage WATERHEXTILE;
    public static BufferedImage MOUNTAINHEXTILE;

    public static BufferedImage SMASHER;
    public static BufferedImage SUMMONER;
    public static BufferedImage SNEAK;
    //NPC's
    public static ArrayList<BufferedImage> PLAYER;
    public static ArrayList<ArrayList<BufferedImage>> PLAYERANIMATION;

    public static ArrayList<BufferedImage> PET;
    public static ArrayList<BufferedImage> BLUE_NPC;
    public static ArrayList<BufferedImage> CAT_NPC;
    public static ArrayList<BufferedImage> DAVE_PET;

    // end NPC's

    //items
    public static BufferedImage BAGOFITEMS;
    public static BufferedImage HEALTH_POTION;
    public static ArrayList<BufferedImage> TREASURE_CHEST;
    public static ArrayList<BufferedImage> WOODEN_DOOR;
    public static BufferedImage MANA_POTION;
    public static BufferedImage AGILITY_POTION;
    public static BufferedImage EXPERIENCE_POTION;
    public static BufferedImage HARDINESS_POTION;
    public static BufferedImage INTELLECT_POTION;
    public static BufferedImage MOVEMENT_POTION;
    public static BufferedImage STRENGTH_POTION;
    public static BufferedImage STICK_SWORD;
    public static BufferedImage STICK_GREATSWORD;
    public static BufferedImage WOODEN_GLOVES;
    public static BufferedImage SWORDFISH_DAGGER;
    public static BufferedImage SWORDFISH_LANCE;
    public static BufferedImage SWORDFISH_GAUNTLETS;
    public static BufferedImage PUFFER_FISH_MACE;
    public static BufferedImage PUFFER_FISH_FLAIL;
    public static BufferedImage PUFFER_FISH_KNUCKLES;
    public static BufferedImage MOUSE_ON_A_STRING_WAND;
    public static BufferedImage CATNIP_STAFF;
    public static BufferedImage FISH_BOOMERANG;
    public static BufferedImage LASER_POINTER;
    public static BufferedImage HELMET;
    public static BufferedImage CHESTPLATE;
    public static BufferedImage PANTS;
    public static BufferedImage PLATELEGS;
    public static BufferedImage GAUNTLETS;
    public static BufferedImage BOOTS;
    public static BufferedImage SHIELD;
    public static BufferedImage CHEST_KEY;
    public static BufferedImage HOUSE;
    public static BufferedImage SMALL_CATNIP;
    public static BufferedImage MEDIUM_CATNIP;
    public static BufferedImage LARGE_CATNIP;
    public static BufferedImage TUNA;
    public static BufferedImage SUSHI;
    public static BufferedImage OPEN_DOOR;
    public static BufferedImage CLOSED_DOOR;
    public static BufferedImage VERT_FENCE;
    public static BufferedImage HORIZ_FENCE_ODD;
    public static BufferedImage HORIZ_FENCE_EVEN;
    public static BufferedImage DOOR_KEY;
    // end of items

    //vehicles
    public static ArrayList<BufferedImage> ROOMBA;

    //projectile
    public static ArrayList<BufferedImage> HAIRBALL;

    public static BufferedImage SKULL;
    public static BufferedImage REDCROSS;
    public static BufferedImage STAR;
    public static BufferedImage FIRE;
    public static BufferedImage PORTAL;
    public static BufferedImage LEFT;
    public static BufferedImage RIGHT;
    public static BufferedImage BACK;
    public static BufferedImage BOX;
    public static BufferedImage BOX2;


    //DECORATION
    public static BufferedImage CIRCLE;

    public static void init() {
        //Tiles
        //Fog Tiles
        SpriteSheet FogTileSheet = new SpriteSheet(new ImageLoader().loadImage("/Textures/fullfog.png"));
        FOGTILE = FogTileSheet.crop(0, 0, 32, 28);
        SpriteSheet HalfFogTileSheet = new SpriteSheet(new ImageLoader().loadImage("/Textures/halffog.png"));
        HALFFOGTILE = HalfFogTileSheet.crop(0, 0, 32, 28);

        //Basic TIles
        SpriteSheet HexTileGrassSheet = new SpriteSheet(new ImageLoader().loadImage("/Textures/grassHex.png"));
        GRASSHEXTILE = HexTileGrassSheet.crop(0, 0, 32, 28);

        SpriteSheet HexTileWaterSheet = new SpriteSheet(new ImageLoader().loadImage("/Textures/waterHex.png"));
        WATERHEXTILE = HexTileWaterSheet.crop(0, 0, 32, 28);

        SpriteSheet HexTileMountainSheet = new SpriteSheet(new ImageLoader().loadImage("/Textures/mountainHex.png"));
        MOUNTAINHEXTILE = HexTileMountainSheet.crop(0, 0, 32, 28);
        // End of Tiles

        //Items

        SpriteSheet bagSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/ItemBag.png"));
        BAGOFITEMS = bagSheet.crop(0, 0, 32, 32);

        SpriteSheet potionHealthSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/HealthPotion.png"));
        HEALTH_POTION = potionHealthSheet.crop(0, 0, 32, 32);

        SpriteSheet potionManaSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/ManaPotion.png"));
        MANA_POTION = potionManaSheet.crop(0, 0, 32, 32);

        SpriteSheet potionAgilitySheet = new SpriteSheet(new ImageLoader().loadImage("/Items/AgilityPotion.png"));
        AGILITY_POTION = potionAgilitySheet.crop(0, 0, 32, 32);

        SpriteSheet potionExperienceSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/ExperiencePotion.png"));
        EXPERIENCE_POTION = potionExperienceSheet.crop(0, 0, 32, 32);

        SpriteSheet potionHardinessSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/HardinessPotion.png"));
        HARDINESS_POTION = potionHardinessSheet.crop(0, 0, 32, 32);

        SpriteSheet potionIntellectSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/IntellectPotion.png"));
        INTELLECT_POTION = potionIntellectSheet.crop(0, 0, 32, 32);

        SpriteSheet potionMovementSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/MovementPotion.png"));
        MOVEMENT_POTION = potionMovementSheet.crop(0, 0, 32, 32);

        SpriteSheet potionStrengthPotion = new SpriteSheet(new ImageLoader().loadImage("/Items/StrengthPotion.png"));
        STRENGTH_POTION = potionStrengthPotion.crop(0, 0, 32, 32);

        SpriteSheet stickSwordSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/StickSword.png"));
        STICK_SWORD = stickSwordSheet.crop(0, 0, 32, 32);

        SpriteSheet stickGreatSwordSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/StickGreatSword.png"));
        STICK_GREATSWORD = stickGreatSwordSheet.crop(0, 0, 32, 32);

        SpriteSheet woodenGlovesSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/WoodenGloves.png"));
        WOODEN_GLOVES = woodenGlovesSheet.crop(0, 0, 32, 32);

        SpriteSheet swordfishDaggerSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/SwordfishDagger.png"));
        SWORDFISH_DAGGER = swordfishDaggerSheet.crop(0, 0, 32, 32);

        SpriteSheet swordfishLanceSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/SwordfishLance.png"));
        SWORDFISH_LANCE = swordfishLanceSheet.crop(0, 0, 32, 32);

        SpriteSheet swordfishGauntletsSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/SwordfishGauntlets.png"));
        SWORDFISH_GAUNTLETS = swordfishGauntletsSheet.crop(0, 0, 32, 32);

        SpriteSheet pufferfishMaceSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/PufferfishMace.png"));
        PUFFER_FISH_MACE = pufferfishMaceSheet.crop(0, 0, 32, 32);

        SpriteSheet pufferfishFlailSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/PufferfishFlail.png"));
        PUFFER_FISH_FLAIL = pufferfishFlailSheet.crop(0, 0, 32, 32);

        SpriteSheet pufferfishKnucklesSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/PufferfishKnuckles.png"));
        PUFFER_FISH_KNUCKLES = pufferfishKnucklesSheet.crop(0, 0, 32, 32);

        SpriteSheet mouseOnAStringWandSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/MouseWand.png"));
        MOUSE_ON_A_STRING_WAND = mouseOnAStringWandSheet.crop(0, 0, 32, 32);

        SpriteSheet catnipStaffSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/CatnipStaff.png"));
        CATNIP_STAFF = catnipStaffSheet.crop(0, 0, 32, 32);

        SpriteSheet fishBoomerangSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/FishBoomerang.png"));
        FISH_BOOMERANG = fishBoomerangSheet.crop(0, 0, 32, 32);

        SpriteSheet laserPointerSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/LaserPointer.png"));
        LASER_POINTER = laserPointerSheet.crop(0, 0, 32, 32);

        SpriteSheet helmetSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/Helmet.png"));
        HELMET = helmetSheet.crop(0, 0, 32, 32);

        SpriteSheet chestPlateSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/ChestPlate.png"));
        CHESTPLATE = chestPlateSheet.crop(0, 0, 32, 32);

        SpriteSheet platelegsSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/ArmoredPants.png"));
        PLATELEGS = platelegsSheet.crop(0, 0, 32, 32);

        SpriteSheet pantsSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/Pants.png"));
        PANTS = pantsSheet.crop(0, 0, 32, 32);

        SpriteSheet bootsSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/Boots.png"));
        BOOTS = bootsSheet.crop(0, 0, 32, 32);

        SpriteSheet gauntletsSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/Gloves.png"));
        GAUNTLETS = gauntletsSheet.crop(0, 0, 32, 32);

        SpriteSheet shieldSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/Shield.png"));
        SHIELD = shieldSheet.crop(0, 0, 32, 32);

        SpriteSheet keySheet = new SpriteSheet(new ImageLoader().loadImage("/Items/Key.png"));
        CHEST_KEY = keySheet.crop(0, 0, 32, 32);

        SpriteSheet houseSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/House.png"));
        HOUSE = houseSheet.crop(0, 0, 32, 32);

        SpriteSheet smallCatnipSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/SmallCatnip.png"));
        SMALL_CATNIP = smallCatnipSheet.crop(0, 0, 32, 32);

        SpriteSheet mediumCatnipSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/MediumCatnip.png"));
        MEDIUM_CATNIP = mediumCatnipSheet.crop(0, 0, 32, 32);

        SpriteSheet largeCatnipSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/LargeCatnip.png"));
        LARGE_CATNIP = largeCatnipSheet.crop(0, 0, 32, 32);

        SpriteSheet tunaSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/Tuna.png"));
        TUNA = tunaSheet.crop(0,0,32,32);

        SpriteSheet sushiSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/Sushi.png"));
        SUSHI = sushiSheet.crop(0,0,32,32);

        SpriteSheet openDoorSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/OpenDoor.png"));
        OPEN_DOOR = openDoorSheet.crop(0,0,32,32);

        SpriteSheet closedDoorSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/ClosedDoor.png"));
        CLOSED_DOOR = closedDoorSheet.crop(0,0,32,32);

        SpriteSheet vertFenceSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/FenceVertical.png"));
        VERT_FENCE = vertFenceSheet.crop(0,0,32,32);

        SpriteSheet horizXOddFenceSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/HorizFenceXOdd.png"));
        HORIZ_FENCE_ODD = horizXOddFenceSheet.crop(0,0,32,32);

        SpriteSheet horizXEvenFenceSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/HorizFenceXEven.png"));
        HORIZ_FENCE_EVEN = horizXEvenFenceSheet.crop(0,0,32,32);

        SpriteSheet doorKeySheet = new SpriteSheet(new ImageLoader().loadImage("/Items/DoorKey.png"));
        DOOR_KEY = doorKeySheet.crop(0,0,32,32);

        // interactables

        TREASURE_CHEST = new ArrayList<>();
        SpriteSheet closedChestSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/ClosedChest.png"));
        SpriteSheet openChestSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/OpenChest.png"));
        TREASURE_CHEST.add(closedChestSheet.crop(0, 0, 32, 28));
        TREASURE_CHEST.add(openChestSheet.crop(0, 0, 32, 28));

        WOODEN_DOOR = new ArrayList<>();
        SpriteSheet woodenDoorClosedSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/WoodenDoorClosed.png"));
        SpriteSheet woodenDoorOpenSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/WoodenDoorOpen.png"));
        WOODEN_DOOR.add(woodenDoorClosedSheet.crop(0, 0, 32, 28));
        WOODEN_DOOR.add(woodenDoorOpenSheet.crop(0, 0, 32, 28));

        // end interactables

        // End of items

        //Vehciles
        ROOMBA = new ArrayList<>();
        SpriteSheet roomba1 = new SpriteSheet(new ImageLoader().loadImage("/Vehicles/Roomba.png"));
        SpriteSheet roomba2 = new SpriteSheet(new ImageLoader().loadImage("/Vehicles/Roomba.png"));
        SpriteSheet roomba3 = new SpriteSheet(new ImageLoader().loadImage("/Vehicles/Roomba.png"));
        SpriteSheet roomba4 = new SpriteSheet(new ImageLoader().loadImage("/Vehicles/Roomba.png"));
        SpriteSheet roomba5 = new SpriteSheet(new ImageLoader().loadImage("/Vehicles/Roomba.png"));
        SpriteSheet roomba6 = new SpriteSheet(new ImageLoader().loadImage("/Vehicles/Roomba.png"));
        ROOMBA.add(roomba1.crop(0, 0, 32, 32));
        ROOMBA.add(roomba2.crop(0, 0, 32, 32));
        ROOMBA.add(roomba3.crop(0, 0, 32, 32));
        ROOMBA.add(roomba4.crop(0, 0, 32, 32));
        ROOMBA.add(roomba5.crop(0, 0, 32, 32));
        ROOMBA.add(roomba6.crop(0, 0, 32, 32));

        //Projectiles
        HAIRBALL = new ArrayList<>();
        SpriteSheet hairball1 = new SpriteSheet((new ImageLoader().loadImage("/Projectiles/Hairball.png")));
        SpriteSheet hairball2 = new SpriteSheet((new ImageLoader().loadImage("/Projectiles/Hairball.png")));
        SpriteSheet hairball3 = new SpriteSheet((new ImageLoader().loadImage("/Projectiles/Hairball.png")));
        SpriteSheet hairball4 = new SpriteSheet((new ImageLoader().loadImage("/Projectiles/Hairball.png")));
        SpriteSheet hairball5 = new SpriteSheet((new ImageLoader().loadImage("/Projectiles/Hairball.png")));
        SpriteSheet hairball6 = new SpriteSheet((new ImageLoader().loadImage("/Projectiles/Hairball.png")));
        HAIRBALL.add(hairball1.crop(0, 0, 32, 32));
        HAIRBALL.add(hairball2.crop(0, 0, 32, 32));
        HAIRBALL.add(hairball3.crop(0, 0, 32, 32));
        HAIRBALL.add(hairball4.crop(0, 0, 32, 32));
        HAIRBALL.add(hairball5.crop(0, 0, 32, 32));
        HAIRBALL.add(hairball6.crop(0, 0, 32, 32));



        //Front Views
        SpriteSheet smasher = new SpriteSheet(new ImageLoader().loadImage("/Entities/Player/smasher.png"));
        SpriteSheet summoner = new SpriteSheet(new ImageLoader().loadImage("/Entities/Player/summoner.png"));
        SpriteSheet sneak = new SpriteSheet(new ImageLoader().loadImage("/Entities/Player/sneaker.png"));
        SMASHER = smasher.crop(0, 0, 32, 32);
        SUMMONER = summoner.crop(0, 0, 32, 32);
        SNEAK = sneak.crop(0, 0, 32, 32);

        //Entities

        PLAYER = new ArrayList<>();
        SpriteSheet player1 = new SpriteSheet(new ImageLoader().loadImage("/Entities/Player/PlayerNE.png"));
        PLAYER.add(player1.crop(0, 0, 32, 32));
        PLAYER.add(player1.crop(0, 32, 32, 32));
        PLAYER.add(player1.crop(0, 64, 32, 32));
        PLAYER.add(player1.crop(0, 96, 32, 32));
        PLAYER.add(player1.crop(0, 128, 32, 32));
        PLAYER.add(player1.crop(0, 160, 32, 32));

        PLAYERANIMATION = new ArrayList<>();
        for(int i = 0; i < 6; ++i) {
            ArrayList<BufferedImage> temp = new ArrayList<>();
            temp.add(player1.crop(0, (i*32), 32, 32));
            temp.add(player1.crop(32, (i*32), 32, 32));
            temp.add(player1.crop(64, (i*32), 32, 32));
            temp.add(player1.crop(96, (i*32), 32, 32));

            PLAYERANIMATION.add(temp);
        }

        System.out.println(PLAYERANIMATION);



        CAT_NPC = new ArrayList<>();
        SpriteSheet cat2 = new SpriteSheet(new ImageLoader().loadImage("/Entities/NPC/CatNorth.png"));
        SpriteSheet cat1 = new SpriteSheet(new ImageLoader().loadImage("/Entities/NPC/CatNE.png"));
        SpriteSheet cat6 = new SpriteSheet(new ImageLoader().loadImage("/Entities/NPC/CatSE.png"));
        SpriteSheet cat5 = new SpriteSheet(new ImageLoader().loadImage("/Entities/NPC/CatSouth.png"));
        SpriteSheet cat4 = new SpriteSheet(new ImageLoader().loadImage("/Entities/NPC/CatSW.png"));
        SpriteSheet cat3 = new SpriteSheet(new ImageLoader().loadImage("/Entities/NPC/CatNW.png"));
        CAT_NPC.add(cat1.crop(0, 0, 32, 32));
        CAT_NPC.add(cat2.crop(0, 0, 32, 32));
        CAT_NPC.add(cat3.crop(0, 0, 32, 32));
        CAT_NPC.add(cat4.crop(0, 0, 32, 32));
        CAT_NPC.add(cat5.crop(0, 0, 32, 32));
        CAT_NPC.add(cat6.crop(0, 0, 32, 32));

        BLUE_NPC = new ArrayList<>();
        SpriteSheet npc2 = new SpriteSheet(new ImageLoader().loadImage("/Entities/NPC/BlueNorth.png"));
        SpriteSheet npc1 = new SpriteSheet(new ImageLoader().loadImage("/Entities/NPC/BlueNE.png"));
        SpriteSheet npc6 = new SpriteSheet(new ImageLoader().loadImage("/Entities/NPC/BlueSE.png"));
        SpriteSheet npc5 = new SpriteSheet(new ImageLoader().loadImage("/Entities/NPC/BlueSouth.png"));
        SpriteSheet npc4 = new SpriteSheet(new ImageLoader().loadImage("/Entities/NPC/BlueSW.png"));
        SpriteSheet npc3 = new SpriteSheet(new ImageLoader().loadImage("/Entities/NPC/BlueNW.png"));
        BLUE_NPC.add(npc1.crop(0, 0, 32, 32));
        BLUE_NPC.add(npc2.crop(0, 0, 32, 32));
        BLUE_NPC.add(npc3.crop(0, 0, 32, 32));
        BLUE_NPC.add(npc4.crop(0, 0, 32, 32));
        BLUE_NPC.add(npc5.crop(0, 0, 32, 32));
        BLUE_NPC.add(npc6.crop(0, 0, 32, 32));

        DAVE_PET = new ArrayList<>();
        SpriteSheet pet2 = new SpriteSheet(new ImageLoader().loadImage("/Entities/Pet/TheDaveNorth.png"));
        SpriteSheet pet1 = new SpriteSheet(new ImageLoader().loadImage("/Entities/Pet/TheDaveNorthEast.png"));
        SpriteSheet pet6 = new SpriteSheet(new ImageLoader().loadImage("/Entities/Pet/TheDaveSouthEast.png"));
        SpriteSheet pet5 = new SpriteSheet(new ImageLoader().loadImage("/Entities/Pet/TheDaveSouth.png"));
        SpriteSheet pet4 = new SpriteSheet(new ImageLoader().loadImage("/Entities/Pet/TheDaveSouthWest.png"));
        SpriteSheet pet3 = new SpriteSheet(new ImageLoader().loadImage("/Entities/Pet/TheDaveNorthWest.png"));
        DAVE_PET.add(pet1.crop(0, 0, 32, 32));
        DAVE_PET.add(pet2.crop(0, 0, 32, 32));
        DAVE_PET.add(pet3.crop(0, 0, 32, 32));
        DAVE_PET.add(pet4.crop(0, 0, 32, 32));
        DAVE_PET.add(pet5.crop(0, 0, 32, 32));
        DAVE_PET.add(pet6.crop(0, 0, 32, 32));

        // End entities

        //Decals
        SpriteSheet skullSheet = new SpriteSheet(new ImageLoader().loadImage("/Decals/Skull.png"));
        SKULL = skullSheet.crop(0, 0, 32, 32);

        SpriteSheet redCrossSheet = new SpriteSheet(new ImageLoader().loadImage("/Decals/RedCross.png"));
        REDCROSS = redCrossSheet.crop(0, 0, 32, 32);

        SpriteSheet starSheet = new SpriteSheet(new ImageLoader().loadImage("/Decals/Star.png"));
        STAR = starSheet.crop(0, 0, 32, 32);

        SpriteSheet fireSheet = new SpriteSheet(new ImageLoader().loadImage("/Decals/Fire.png"));
        FIRE = fireSheet.crop(0, 0, 32, 32);

        SpriteSheet portalSheet = new SpriteSheet(new ImageLoader().loadImage("/Decals/Portal.png"));
        PORTAL = portalSheet.crop(0, 0, 32, 32);

        //menu
        LEFT = new ImageLoader().loadImage("/Pics/LEFT1.png");
        RIGHT = new ImageLoader().loadImage("/Pics/RIGHT1.png");
        BACK = new ImageLoader().loadImage("/Pics/blue.jpg");
        BOX = new ImageLoader().loadImage("/Pics/box.png");
        BOX2 = new ImageLoader().loadImage("/Pics/box2.png");

        //SpriteSheet invBox = new SpriteSheet(new ImageLoader().loadImage("/Pics/box.png"));
        //BOX = invBox.crop(0,0,32,32);

        SpriteSheet circle = new SpriteSheet(new ImageLoader().loadImage("/Entities/Player/rope.png"));
        CIRCLE = circle.crop(0, 0, 600, 600);

    }
}