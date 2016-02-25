package View.ViewUtilities;

import View.View;
import sun.util.locale.provider.JRELocaleConstants;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public class MainScreen extends JFrame {

    private String title;
    private int windowHeight, windowWidth;
    Canvas canvas;

    public MainScreen(String title, int windowWidth, int windowHeight) {
        this.title = title;
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        setTitle(title);
        setSize(new Dimension(windowWidth, windowHeight));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);


        Dimension canvasDimension = new Dimension(windowWidth, windowHeight);
        canvas = new Canvas();
        canvas.setPreferredSize(canvasDimension);
        canvas.setMaximumSize(canvasDimension);
        canvas.setMinimumSize(canvasDimension);
        canvas.setFocusable(false);

        add(canvas);
        pack();
    }


    public Canvas getCanvas() {
        return canvas;
    }
}
