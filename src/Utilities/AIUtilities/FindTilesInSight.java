package Utilities.AIUtilities;

import Model.GameObject.MobileObjects.ViewLocation;
import Model.Location;
import Model.Map.Map;
import Model.Map.Tile;

import java.util.ArrayList;

/**
 * Created by Aidan on 3/11/2016.
 */
public class FindTilesInSight {

    public static ArrayList<Tile> find(Location location, Map map, int view, ViewLocation viewLoc) {


        ArrayList<Tile> tilesinView = FindTilesAround.find(location,map,view,viewLoc);

        ArrayList<Tile> tilesinSight = new ArrayList<Tile>();

        for (Tile tile : tilesinView) {
            if(location.getX() % 2 == 0 ){
                switch(location.getDir()) {
                        case 45:
                            if (tile.getLocation().getX() - location.getX() <= view && location.getY() - tile.getLocation().getY() == 1) {
                                tilesinSight.add(tile);
                            }
                        case 90:
                            if (location.getY() - tile.getLocation().getY() <= view && tile.getLocation().getX() == location.getX()) {
                                tilesinSight.add(tile);
                            }
                        case 135:
                            if(location.getX() - tile.getLocation().getX() <= view && location.getY() - tile.getLocation().getY() == 1){
                                tilesinSight.add(tile);
                            }
                        case 225:
                            if(location.getX() - tile.getLocation().getX() <= view && tile.getLocation().getY() - location.getY() <= view + 1){
                                if(!(location.getX() - tile.getLocation().getX()  == 1 && tile.getLocation().getY() - location.getY() == 1) ) {
                                    tilesinSight.add(tile);
                                }
                            }
                        case 270:
                            if(tile.getLocation().getY() - location.getY() <= view && tile.getLocation().getX() == location.getX()){
                                tilesinSight.add(tile);
                        }
                        case 315:
                            if(tile.getLocation().getX() - location.getX() <= view && tile.getLocation().getY() - location.getY() <= view + 1){
                                if(!(tile.getLocation().getX() - location.getX()  == 1 && tile.getLocation().getY() - location.getY() == 1) ) {
                                    tilesinSight.add(tile);
                                }
                            }
                }

            }
            else{
                switch(location.getDir()) {
                    case 45:
                        if (tile.getLocation().getX() - location.getX() <= view && location.getY() - tile.getLocation().getY() == 1) {
                            if(!(tile.getLocation().getX() - location.getX() == 1 && location.getY() - tile.getLocation().getY() == 1))
                            tilesinSight.add(tile);
                        }
                    case 90:
                        if (location.getY() - tile.getLocation().getY() <= view && tile.getLocation().getX() == location.getX()) {
                            tilesinSight.add(tile);
                        }
                    case 135:
                        if(location.getX() - tile.getLocation().getX() <= view && location.getY() - tile.getLocation().getY() == 1){
                            if(!(location.getX() - tile.getLocation().getX() == 1 && location.getY() - tile.getLocation().getY() == 1)) {
                                tilesinSight.add(tile);
                            }
                        }
                    case 225:
                        if(location.getX() - tile.getLocation().getX() <= view && tile.getLocation().getY() - location.getY() <= view + 1){
                            tilesinSight.add(tile);
                        }
                    case 270:
                        if(tile.getLocation().getY() - location.getY() <= view && tile.getLocation().getX() == location.getX()){
                            tilesinSight.add(tile);
                        }
                    case 315:
                        if(tile.getLocation().getX() - location.getX() <= view && tile.getLocation().getY() - location.getY() <= view + 1){
                            tilesinSight.add(tile);
                        }
                }

            }
        }

        return tilesinSight;

    }


}
