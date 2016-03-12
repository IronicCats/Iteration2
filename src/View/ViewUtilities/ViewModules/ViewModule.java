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

    public static void renderHealthBox(Graphics g, int direction, int health, int maxHealth, int x, int y) {

        g.setColor(Color.WHITE);
        g.drawRect(x, y-5, 60, 8);
        g.setColor(Color.GREEN);
        g.fillRect(x+1, y-4, (int)(((double)health/(double)maxHealth)*60), 7);
    }

}
