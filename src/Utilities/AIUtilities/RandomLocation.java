package Utilities.AIUtilities;

import Model.Location;
import java.util.Random;

/**
 * Created by Aidan on 3/9/2016.
 */
public class RandomLocation {

    private static Random random;

    public static Location computeRandomLocation(Location base) {
        return new Location(base.getX() + (int)Math.pow(-1, random.nextInt(2)) * 2/*stats.getMovement()*/,
                base.getY() + (int)Math.pow(-1, random.nextInt(2)) * 2)/*stats.getMovement()*/;
    }

}
