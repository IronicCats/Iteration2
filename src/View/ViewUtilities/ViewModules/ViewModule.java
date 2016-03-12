package View.ViewUtilities.ViewModules;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Joshua Kegley on 3/11/2016.
 */
public class ViewModule {

    public static void renderName(Graphics g, String name, int x, int y) {
        g.setColor(Color.WHITE);
        g.drawString(name, x, y-4);
    }

    public static void renderHealthBox(Graphics g, int health, int maxHealth, int x, int y) {
        g.setColor(Color.WHITE);
        g.drawRect(x, y-5, 60, 8);
        g.setColor(calculateHealthColor(((double)health/maxHealth)));
        g.fillRect(x+1, y-4, (int)(((double)health/(double)maxHealth)*60), 7);
    }

    public static Color calculateHealthColor(double ratio) {
        int gr = (int)(255 * ratio*100) / 100;
        int red = (int)(255 * (100 - ratio*100)) / 100;
        int blu = 0;
        if(gr > 255 || red > 255) {
            return new Color(255, 255, 255);
        }else if(gr < 0 || red < 0) {
            return new Color(0, 0, 0);
        }

        return new Color(red, gr, blu);
    }

}

