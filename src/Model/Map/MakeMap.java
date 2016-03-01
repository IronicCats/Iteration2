package Model.Map;

import Model.Location;
import Model.Map.Tiles.Grass;
import Model.Map.Tiles.Tile;
import Utitlies.Subject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Aidan on 3/1/2016.
 */
public class MakeMap {
    private Tile[][] tiles;
    private int width;
    private int height;
    private Location spawn;

    public MakeMap(){
        makeMap();
    }

    public void makeMap() {
        StringBuilder builder = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader("res/MapFiles/Map.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line + "\n");
            }
        } catch (IOException e) {
            System.out.println(e);

        }

        String[] tokens = builder.toString().split("\\s+");
        width = parseInt(tokens[0]);
        height = parseInt(tokens[1]);
        spawn = new Location(parseInt(tokens[2]), parseInt(tokens[3]), 2);
        tiles = new Tile[width][height];
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                Tile tile;
                switch (parseInt(tokens[(x + y * width) + 4])) {
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
    }

        private int parseInt(String string) {
            try {
                return Integer.parseInt(string);
            }catch(NumberFormatException e) {
                e.printStackTrace();
                return 0;
            }
        }

    public Tile getTile(int x , int y) {
        if(x < 0 || y < 0 || x >= width  || y >= height){
            return tiles[2][2];
        }
        return tiles[x][y];
    }
}
