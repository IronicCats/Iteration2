package Utilities.AIUtilities;

import Model.Location;

/**
 * Created by Aidan on 3/12/2016.
 */
public class DirectionofTarget {

    public static int getDir(Location base, Location target){

        int posXdis = target.getX() - base.getX();
        int negXdis = base.getX() - target.getX();
        int posYdis = target.getY() - base.getY();
        int negYdis = base.getY() - target.getY();

        if (base.getX() % 2 == 0) {

            if (posXdis == 1 && negYdis == 1) {
                return 45;
            }
            else if (posXdis == 2 && negYdis == 1) {
                return 45;
            }


            else if (negYdis > 0 && posXdis == 0) {
                return 90;
            }

            else if (negXdis == 1 && negYdis == 1) {
                return 135;
            }
            else if (negXdis == 2 && negYdis == 1) {
                return 135;
            }

            else if (negXdis == 1 && posYdis == 0) {
                return 225;
            }
            else if (negXdis == 2 && posYdis == 1) {
                return 225;
            }

            else if (posYdis > 0 && negXdis == 0) {
                return 270;
            }

            else if (posXdis == 1 && posYdis == 0) {
                return 315;
            }
            else if (posXdis == 2 && posYdis == 1) {
                return 315;
            }

        }
        else {

            if (posXdis == 1 && negYdis == 0) {
                return 45;
            }
            else if (posXdis == 2 && negYdis == 1) {
                return 45;
            }

            else if (negYdis > 0 && posXdis == 0) {
                return 90;
            }

            else if (negXdis == 1 && negYdis == 0) {
                return 135;
            }
            else if (negXdis == 2 && negYdis == 1) {
                return 135;
            }

            else if (negXdis == 1 && posYdis == 1) {
                return 225;
            }
            else if (negXdis == 2 && posYdis == 1) {
                return 225;
            }

            else if (posYdis > 0 && negXdis == 0) {
                return 270;
            }

            else if (posXdis == 1 && posYdis == 1) {
                return 315;
            }
            else if (posXdis == 2 && posYdis == 1) {
                return 315;
            }

        }

        return 0;

    }

}
