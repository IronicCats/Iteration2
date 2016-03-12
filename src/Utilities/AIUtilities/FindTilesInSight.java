package Utilities.AIUtilities;

import Model.Location;
import Model.Map.Tile;

import java.util.ArrayList;

/**
 * Created by Aidan on 3/11/2016.
 */
public class FindTilesInSight {

    public static ArrayList<Tile> find(ArrayList<Tile> tilesinView, Location location, int view) {

        ArrayList<Tile> tilesinSight = new ArrayList<Tile>();

        for (Tile tile : tilesinView) {
            int posXdis = tile.getLocation().getX() - location.getX();
            int negXdis = location.getX() - tile.getLocation().getX();
            int posYdis = tile.getLocation().getY() - location.getY();
            int negYdis = location.getY() - tile.getLocation().getY();

            if (location.getX() % 2 == 0) {
                switch (location.getDir()) {
                    case 45:
                        if (view >= 1 && posXdis == 1 && negYdis == 1) {
                            tilesinSight.add(tile);
                        } else if (view == 2 && posXdis == 2 && negYdis == 1) {
                            tilesinSight.add(tile);
                        }
                        break;
                    case 90:
                        if (negYdis > 0 && negYdis <= view && tile.getLocation().getX() == location.getX()) {
                            tilesinSight.add(tile);
                        }
                        break;
                    case 135:
                        if (view >= 1 && negXdis == 1 && negYdis == 1) {
                            tilesinSight.add(tile);
                        } else if (view == 2 && negXdis == 2 && negYdis == 1) {
                            tilesinSight.add(tile);
                        }
                        break;
                    case 225:
                        if (view >= 1 && negXdis == 1 && posYdis == 0) {
                            tilesinSight.add(tile);
                        } else if (view == 2 && negXdis == 2 && posYdis == 1) {
                            tilesinSight.add(tile);
                        }
                        break;
                    case 270:
                        if (posYdis > 0 && negYdis <= view && tile.getLocation().getX() == location.getX()) {
                            tilesinSight.add(tile);
                        }
                        break;
                    case 315:
                        if (view >= 1 && posXdis == 1 && posYdis == 0) {
                            tilesinSight.add(tile);
                        } else if (view == 2 && posXdis == 2 && posYdis == 1) {
                            tilesinSight.add(tile);
                        }
                        break;
                }

            } else {
                switch (location.getDir()) {
                    case 45:
                        if (view >= 1 && posXdis == 1 && negYdis == 0) {
                            tilesinSight.add(tile);
                        } else if (view == 2 && posXdis == 2 && negYdis == 1) {
                            tilesinSight.add(tile);
                        }
                        break;
                    case 90:
                        if (negYdis > 0 && negYdis <= view && tile.getLocation().getX() == location.getX()) {
                            tilesinSight.add(tile);
                        }
                        break;
                    case 135:
                        if (view >= 1 && negXdis == 1 && negYdis == 0) {
                            tilesinSight.add(tile);
                        } else if (view == 2 && negXdis == 2 && negYdis == 1) {
                            tilesinSight.add(tile);
                        }
                        break;
                    case 225:
                        if (view >= 1 && negXdis == 1 && posYdis == 1) {
                            tilesinSight.add(tile);
                        } else if (view == 2 && negXdis == 2 && posYdis == 1) {
                            tilesinSight.add(tile);
                        }
                        break;
                    case 270:
                        if (posYdis > 0 && posYdis <= view && tile.getLocation().getX() == location.getX()) {
                            tilesinSight.add(tile);
                        }
                        break;
                    case 315:
                        if (view >= 1 && posXdis == 1 && posYdis == 1) {
                            tilesinSight.add(tile);
                        } else if (view == 2 && posXdis == 2 && posYdis == 1) {
                            tilesinSight.add(tile);
                        }
                        break;

                }
            }
        }
        return tilesinSight;
    }
}
