package Utilities.AIUtilities;

import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;
import Model.Map.Map;
import Model.Map.Tile;

import java.util.ArrayList;

/**
 * Created by Aidan on 3/12/2016.
 */
public class CanFace {

    public static boolean find(MobileObject base, MobileObject target, Map map) {

        ArrayList<Tile> tilesinView = FindTilesAround.find(base.getLocation(), map, base.getView(), base.getViewLocation());
        ArrayList<Tile> tilesinSight = new ArrayList<Tile>();

        for (Tile tile : tilesinView) {
            int posXdis = tile.getLocation().getX() - base.getX();
            int negXdis = base.getX() - tile.getLocation().getX();
            int posYdis = tile.getLocation().getY() - base.getY();
            int negYdis = base.getY() - tile.getLocation().getY();

            if (base.getX() % 2 == 0) {

                if (base.getView() >= 1 && posXdis == 1 && negYdis == 1) {
                    tilesinSight.add(tile);
                } else if (base.getView() == 2 && posXdis == 2 && negYdis == 1) {
                    tilesinSight.add(tile);
                }


                if (negYdis > 0 && negYdis <= base.getView() && tile.getLocation().getX() == base.getX()) {
                    tilesinSight.add(tile);
                }

                if (base.getView() >= 1 && negXdis == 1 && negYdis == 1) {
                    tilesinSight.add(tile);
                } else if (base.getView() == 2 && negXdis == 2 && negYdis == 1) {
                    tilesinSight.add(tile);
                }

                if (base.getView() >= 1 && negXdis == 1 && posYdis == 0) {
                    tilesinSight.add(tile);
                } else if (base.getView() == 2 && negXdis == 2 && posYdis == 1) {
                    tilesinSight.add(tile);
                }

                if (posYdis > 0 && negYdis <= base.getView() && tile.getLocation().getX() == base.getX()) {
                    tilesinSight.add(tile);
                }

                if (base.getView() >= 1 && posXdis == 1 && posYdis == 0) {
                    tilesinSight.add(tile);
                } else if (base.getView() == 2 && posXdis == 2 && posYdis == 1) {
                    tilesinSight.add(tile);
                }

            } else {

                if (base.getView() >= 1 && posXdis == 1 && negYdis == 0) {
                    tilesinSight.add(tile);
                } else if (base.getView() == 2 && posXdis == 2 && negYdis == 1) {
                    tilesinSight.add(tile);
                }

                if (negYdis > 0 && negYdis <= base.getView() && tile.getLocation().getX() == base.getX()) {
                    tilesinSight.add(tile);
                }

                if (base.getView() >= 1 && negXdis == 1 && negYdis == 0) {
                    tilesinSight.add(tile);
                } else if (base.getView() == 2 && negXdis == 2 && negYdis == 1) {
                    tilesinSight.add(tile);
                }

                if (base.getView() >= 1 && negXdis == 1 && posYdis == 1) {
                    tilesinSight.add(tile);
                } else if (base.getView() == 2 && negXdis == 2 && posYdis == 1) {
                    tilesinSight.add(tile);
                }

                if (posYdis > 0 && posYdis <= base.getView() && tile.getLocation().getX() == base.getX()) {
                    tilesinSight.add(tile);
                }

                if (base.getView() >= 1 && posXdis == 1 && posYdis == 1) {
                    tilesinSight.add(tile);
                } else if (base.getView() == 2 && posXdis == 2 && posYdis == 1) {
                    tilesinSight.add(tile);
                }

            }
        }
        return FindTargetinTiles.find(tilesinSight,target);
    }
}





