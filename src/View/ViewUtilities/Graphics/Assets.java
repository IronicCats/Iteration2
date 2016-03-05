package View.ViewUtilities.Graphics;

import java.awt.image.BufferedImage;

/**
 * Created by Aidan on 3/1/2016.
 */
public class Assets {

    private static final int width = 32, height = 32;

    public static BufferedImage GRASSHEXTILE;
    public static BufferedImage WATERHEXTILE;
    public static BufferedImage MOUNTAINHEXTILE;

    public static BufferedImage POTION;

    public static void init() {
        //Tiles
        SpriteSheet HexTileGrassSheet = new SpriteSheet(new ImageLoader().loadImage("/Textures/grassHex.png"));
        GRASSHEXTILE = HexTileGrassSheet.crop(0,0,32,28);

        SpriteSheet HexTileWaterSheet = new SpriteSheet(new ImageLoader().loadImage("/Textures/waterHex.png"));
        WATERHEXTILE = HexTileWaterSheet.crop(0,0,32,28);

        SpriteSheet HexTileMountainSheet = new SpriteSheet(new ImageLoader().loadImage("/Textures/mountainHex.png"));
        MOUNTAINHEXTILE = HexTileMountainSheet.crop(0,0,32,28);


        //Items
        SpriteSheet potionSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/PotionSprite.png"));
        POTION = potionSheet.crop(0,0,32,32);


    }
}
