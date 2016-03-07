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


    public static ArrayList<BufferedImage> PLAYER;


    public static BufferedImage HEALTH_POTION;
    public static BufferedImage CLOSED_TREASURE_CHEST;
    public static BufferedImage MANA_POTION;
    public static BufferedImage STICK_SWORD;
    public static BufferedImage STICK_GREATSWORD;
    public static BufferedImage PUFFER_FISH_MACE;
    public static BufferedImage HAIRBALL;
    public static BufferedImage LASER_POINTER;
    public static BufferedImage HELMET;
    public static BufferedImage CHESTPLATE;
    public static BufferedImage PANTS;
    public static BufferedImage PLATELEGS;
    public static BufferedImage GAUNTLETS;
    public static BufferedImage BOOTS;
    public static BufferedImage SHIELD;

    public static BufferedImage SKULL;
    public static BufferedImage REDCROSS;
    public static BufferedImage STAR;


    public static void init() {
        //Tiles
        //Fog Tiles
        SpriteSheet FogTileSheet = new SpriteSheet(new ImageLoader().loadImage("/Textures/grassHex.png"));
        FOGTILE = FogTileSheet.crop(0,0,32,28);
        SpriteSheet HalfFogTileSheet = new SpriteSheet(new ImageLoader().loadImage("/Textures/grassHex.png"));
        HALFFOGTILE = HalfFogTileSheet.crop(0,0,32,28);

        //Basic TIles
        SpriteSheet HexTileGrassSheet = new SpriteSheet(new ImageLoader().loadImage("/Textures/grassHex.png"));
        GRASSHEXTILE = HexTileGrassSheet.crop(0,0,32,28);

        SpriteSheet HexTileWaterSheet = new SpriteSheet(new ImageLoader().loadImage("/Textures/waterHex.png"));
        WATERHEXTILE = HexTileWaterSheet.crop(0,0,32,28);

        SpriteSheet HexTileMountainSheet = new SpriteSheet(new ImageLoader().loadImage("/Textures/mountainHex.png"));
        MOUNTAINHEXTILE = HexTileMountainSheet.crop(0,0,32,28);
        // End of Tiles

        //Items
        SpriteSheet potionHealthSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/HealthPotion.png"));
        HEALTH_POTION = potionHealthSheet.crop(0,0,32,32);

        SpriteSheet potionManaSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/ManaPotion.png"));
        MANA_POTION = potionManaSheet.crop(0,0,32,32);

        SpriteSheet chestSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/ClosedChest.png"));
        CLOSED_TREASURE_CHEST = chestSheet.crop(0,0,32,28);

        SpriteSheet stickSwordSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/StickSword.png"));
        STICK_SWORD = stickSwordSheet.crop(0,0,32,32);

        SpriteSheet stickGreatSwordSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/StickGreatSword.png"));
        STICK_GREATSWORD = stickGreatSwordSheet.crop(0,0,32,32);

        SpriteSheet laserPointerSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/LaserPointer.png"));
        LASER_POINTER = laserPointerSheet.crop(0,0,32,32);

        SpriteSheet helmetSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/Helmet.png"));
        HELMET = helmetSheet.crop(0,0,32,32);

        SpriteSheet chestPlateSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/ChestPlate.png"));
        CHESTPLATE = chestPlateSheet.crop(0,0,32,32);

        SpriteSheet pantsSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/Pants.png"));
        PANTS = pantsSheet.crop(0,0,32,32);

        SpriteSheet bootsSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/Boots.png"));
        BOOTS = bootsSheet.crop(0,0,32,32);

        SpriteSheet gauntletsSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/Gauntlets.png"));
        GAUNTLETS = gauntletsSheet.crop(0,0,32,32);

        SpriteSheet shieldSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/Shield.png"));
        SHIELD = shieldSheet.crop(0,0,32,32);

        //End of Tiles

        //Entities
        PLAYER = new ArrayList<>();
        SpriteSheet player1 = new SpriteSheet(new ImageLoader().loadImage("/Entities/Player/player1.png"));
        SpriteSheet player2 = new SpriteSheet(new ImageLoader().loadImage("/Entities/Player/player2.png"));
        SpriteSheet player3 = new SpriteSheet(new ImageLoader().loadImage("/Entities/Player/player3.png"));
        SpriteSheet player4 = new SpriteSheet(new ImageLoader().loadImage("/Entities/Player/player4.png"));
        PLAYER.add(player1.crop(0, 0, 32, 32));
        PLAYER.add(player2.crop(0, 0, 32, 32));
        PLAYER.add(player3.crop(0, 0, 32, 32));
        PLAYER.add(player4.crop(0, 0, 32, 32));

        //Decals
        SpriteSheet skullSheet = new SpriteSheet(new ImageLoader().loadImage("/Decals/Skull.png"));
        SKULL = skullSheet.crop(0,0,32,32);

        SpriteSheet redCrossSheet = new SpriteSheet(new ImageLoader().loadImage("/Decals/RedCross.png"));
        REDCROSS = redCrossSheet.crop(0,0,32,32);

        SpriteSheet starSheet = new SpriteSheet(new ImageLoader().loadImage("/Decals/Star.png"));
        STAR = starSheet.crop(0,0,32,32);
    }
}
