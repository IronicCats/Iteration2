package View.ViewUtilities;

import Utilities.Settings;
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

    public MainScreen() {
        this.title = Settings.TITLE;
        this.windowHeight = Settings.GAMEHEIGHT;
        this.windowWidth = Settings.GAMEWIDTH;
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
        canvas.setFocusable(true);

        add(canvas);
        pack();
    }


    public Canvas getCanvas() {
        return canvas;
    }
}
