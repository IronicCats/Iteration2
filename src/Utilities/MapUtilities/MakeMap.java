package Utilities.MapUtilities;

import Model.Location;
import Model.Map.Map;
import Model.Map.Tiles.Grass;
import Model.Map.Tile;
import Utilities.Utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Aidan on 3/1/2016.
 */
public class MakeMap {

    public MakeMap(){

    }

    public static Map makeMap() {
        Tile[][] tiles;
        int width;
        int height;
        Location spawn;

        StringBuilder builder = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader("res/MapFiles/Map.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line + "\n");
            }
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Failed");

        }

        String[] tokens = builder.toString().split("\\s+");
        width = Utilities.parseInt(tokens[0]);
        height = Utilities.parseInt(tokens[1]);
        spawn = new Location(Utilities.parseInt(tokens[2]), Utilities.parseInt(tokens[3]), 2);
        tiles = new Tile[width][height];
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                Tile tile;
                switch (Utilities.parseInt(tokens[(x + y * width) + 4])) {
                    case -1:
                        tile = new Grass(new Location(x,y,0));
                        break;
                    default:
                        tile = new Grass(new Location(x,y,0));
                        break;
                }
                tiles[x][y] = tile;
            }
        }
        return new Map(tiles,width,height,spawn);
    }

}
