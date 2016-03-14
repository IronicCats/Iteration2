package View.ViewUtilities.ViewModules;

import java.awt.*;

/**
 * Created by Joshua Kegley on 3/11/2016.
 */
public class ViewModule {

    public static void renderName(Graphics g, String name, int x, int y) {
        g.setColor(Color.WHITE);
        g.drawString(name, x, y - 4);
    }

    public static void renderHealthBox(Graphics g, int health, int maxHealth, int x, int y) {
        g.setColor(Color.WHITE);
        g.drawRect(x + 10, y - 5, 40, 5);
        g.setColor(calculateHealthColor(((double) health / maxHealth)));
        g.fillRect(x + 11, y - 4, (int) (((double) health / (double) maxHealth) * 39), 4);
    }

    public static Color calculateHealthColor(double power) {
        double blue = 0.0;
        double green = 0.0;
        double red = 0.0;
        if( 0<=power && power <0.5 ) { //first, green stays at 100 %, red raises to 100 %
                red = 1.0;
        }
        green = 2 * power;
        if( 0.5<=power && power <=1 ){ //then red stays at 100 %, green decays
                green = 1.0;
        }
        red = 1.0 - 2 * (power-0.5);

        return new Color((int) red*255, (int)green*255, (int)blue*255);
        //return Color.getHSBColor((float)(1 - ratio), 1, 1);
    }

}

