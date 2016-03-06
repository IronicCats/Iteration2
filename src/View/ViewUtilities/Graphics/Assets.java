package View.ViewUtilities.Graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Aidan on 3/1/2016.
 */
public class Assets {

    private static final int width = 32, height = 32;

    public static BufferedImage GRASSHEXTILE;
    public static BufferedImage WATERHEXTILE;
    public static BufferedImage MOUNTAINHEXTILE;


    public static ArrayList<BufferedImage> PLAYER;


    public static BufferedImage POTION;

    public static void init() {
        //Tiles
        SpriteSheet HexTileGrassSheet = new SpriteSheet(new ImageLoader().loadImage("/Textures/grassHex.png"));
        GRASSHEXTILE = HexTileGrassSheet.crop(0,0,32,28);

        SpriteSheet HexTileWaterSheet = new SpriteSheet(new ImageLoader().loadImage("/Textures/waterHex.png"));
        WATERHEXTILE = HexTileWaterSheet.crop(0,0,32,28);

        SpriteSheet HexTileMountainSheet = new SpriteSheet(new ImageLoader().loadImage("/Textures/mountainHex.png"));
        MOUNTAINHEXTILE = HexTileMountainSheet.crop(0,0,32,28);
        // End of Tiles

        //Items
        SpriteSheet potionSheet = new SpriteSheet(new ImageLoader().loadImage("/Items/PotionSprite.png"));
        POTION = potionSheet.crop(0,0,32,32);

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
    }
}
