package View.ViewUtilities.Graphics;

import java.awt.image.BufferedImage;

/**
 * Created by Aidan on 3/1/2016.
 */
public class Assets {

    private static final int width = 32, height = 32;

    public static BufferedImage HexTileGrass;

    public static void init() {
        SpriteSheet HexTileGrassSheet = new SpriteSheet(new ImageLoader().loadImage("/textures/grassHex.png"));

        HexTileGrass = HexTileGrassSheet.crop(0,0,32,28);
    }
}
