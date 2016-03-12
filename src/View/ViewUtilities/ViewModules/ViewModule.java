package View.ViewUtilities.ViewModules;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/**
 * Created by Joshua Kegley on 3/11/2016.
 */
public class ViewModule {

    public static void renderHealthBox(Graphics g, int direction, int health, int maxHealth, int x, int y) {

        double theta = Math.toRadians(direction);

        Rectangle2D rect = new Rectangle2D.Double(x, y, 60, 10);

        AffineTransform transform = new AffineTransform();
        transform.rotate(theta);
        //transform.translate(2, 2);

        g.setColor(Color.BLACK);
        Shape rotatedRect = transform.createTransformedShape(rect);


        ((Graphics2D)g).draw(rotatedRect);
    }

}
