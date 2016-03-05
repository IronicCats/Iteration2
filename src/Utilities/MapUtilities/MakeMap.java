package Utilities.MapUtilities;

import Model.GameObject.Item.Item;
import Model.Location;
import Model.Map.Map;
import Model.Map.Tiles.Grass;
import Model.Map.Tile;
import Model.Map.Tiles.Mountain;
import Model.Map.Tiles.Water;
import Utilities.Utilities;
import View.ViewUtilities.Graphics.Assets;
import View.Views.MapView;
import View.Views.TileView;

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
        //Reads in map from map.txt
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
                    case 10:
                        tile = new Water(new Location(x,y,0));
                        break;
                    case 20:
                        tile = new Mountain(new Location(x,y,0));
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
    //does same thing map does but also makes a coinciding tileview for every tile. all the tileview make a mapview
    public static MapView makeMapView(Map map){
        TileView tileViews[][] = new TileView [map.getWidth()][map.getHeight()];
        for(int x = 0; x < map.getWidth(); x++){
            for(int y = 0; y < map.getHeight(); y++) {
                Tile tile = map.getTile(x, y);
                if (tile instanceof Grass) {
                    tileViews[x][y] = new TileView(tile, Assets.GRASSHEXTILE);
                } else if(tile instanceof Water){
                    tileViews[x][y] = new TileView(tile, Assets.WATERHEXTILE);
                } else if(tile instanceof Mountain){
                    tileViews[x][y] = new TileView(tile, Assets.MOUNTAINHEXTILE);
                }
                else {
                    tileViews[x][y] = new TileView(tile, Assets.GRASSHEXTILE);
                }
            }
        }
        return new MapView(map,tileViews);
    }

    public static void populateItems(Item items[], Map map) {
        for(Item item:items) {
            map.placeItem(item);
        }
    }
}
