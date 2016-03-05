package Utilities;

/**
 * Created by Aidan on 3/1/2016.
 */
public class Utilities {
    public static int parseInt(String string){
        try {
            return Integer.parseInt(string);
        }catch(NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int calculateHexXLocation(int x, int y) {

        return x * 48;
    }

    public static int calculateHexYLocation(int x, int y) {
        return 0;
    }
}
