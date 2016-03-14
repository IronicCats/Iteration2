package Utilities.AIUtilities;

import Model.GameObject.MobileObjects.MobileObject;

/**
 * Created by Aidan on 3/12/2016.
 */
public class DistanceFromFaceableTarget {

    public static int calculate(MobileObject base, MobileObject target){


        int posXdis = target.getX() - base.getX();
        int negXdis = base.getX() - target.getX();
        int posYdis = target.getY() - base.getY();
        int negYdis = base.getY() - target.getY();

        if (base.getX() % 2 == 0) {


            if (posXdis == 1 && negYdis == 1) {
                return 1;
            }
            else if (posXdis == 2 && negYdis == 1) {
                return 2;
            }


            else if (negYdis == 1  && posXdis == 0) {
                return 1;
            }

            else if (negYdis == 2  && posXdis == 0) {
                return 2;
            }

            else if (negXdis == 1 && negYdis == 0) {
                return 1;
            }
            else if (negXdis == 2 && negYdis == 1) {
                return 2;
            }

            else if (negXdis == 1 && posYdis == 1) {
                return 1;
            }
            else if (negXdis == 2 && posYdis == 1) {
                return 2;
            }

            else if (posYdis == 1 && negXdis == 0) {
                return 1;
            }

            else if (posYdis == 2 && negXdis == 0) {
                return 2;
            }

            else if (posXdis == 1 && posYdis == 1) {
                return 1;
            }
            else if (posXdis == 2 && posYdis == 1) {
                return 2;
            }

        } else {

            if (posXdis == 1 && negYdis == 0) {
                return 1;
            }
            else if (negYdis == 1 && posXdis == 0) {
                return 1;
            }
            else if (negXdis == 1 && negYdis == 0) {
                return 1;
            }
            else if (negXdis == 1 && posYdis == 1) {
                return 1;
            }

            else if (posYdis == 1 && negXdis == 0) {
                return 1;
            }

            else if (posXdis == 1 && posYdis == 1) {
                return 1;
            }

            else if (posXdis == 2 && negYdis == 1) {
                return 2;
            }



            else if (negYdis == 2 && posXdis == 0) {
                return 2;
            }


            else if (negXdis == 2 && negYdis == 1) {
                return 2;
            }


            else if (negXdis == 2 && posYdis == 1) {
                return 2;
            }



            else if (posYdis == 2 && negXdis == 0) {
                return 2;
            }


            else if (posXdis == 2 && posYdis == 1) {
                return 2;
            }

        }
        return 0;
    }

}
