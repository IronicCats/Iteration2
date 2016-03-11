package Utilities.AIUtilities;

import Model.Location;
import java.util.Random;

/**
 * Created by Aidan on 3/9/2016.
 */
public class RandomLocation {

    private static Random random = new Random(System.currentTimeMillis());;

    public static Location computeRandomLocation(Location base,int range) {
        System.out.println("xLoc: " + base.getX() + " " + "yLoc: " + base.getY());
        return new Location(base.getX() + (int)Math.pow(-1, random.nextInt(2)) * random.nextInt(range)/*stats.getMovement()*/,
                            base.getY() + (int)Math.pow(-1, random.nextInt(2)) * random.nextInt(range))/*stats.getMovement()*/;
    }

}
