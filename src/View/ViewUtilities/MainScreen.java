package View.ViewUtilities;

import Utilities.Settings;
import View.View;
import sun.util.locale.provider.JRELocaleConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
        setResizable(true);
        setLocationRelativeTo(null);


        Dimension canvasDimension = new Dimension(windowWidth, windowHeight);
        canvas = new Canvas();
        canvas.setPreferredSize(canvasDimension);
        canvas.setMinimumSize(canvasDimension);
        canvas.setFocusable(true);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(canvas, BorderLayout.CENTER);
        pack();

        this.getRootPane().addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                // This is only called when the user releases the mouse button.
                System.out.println("componentResized");
                Settings.GAMEHEIGHT = windowHeight = getHeight();
                Settings.GAMEWIDTH = windowWidth = getWidth();

                canvas.setPreferredSize(new Dimension(windowWidth, windowHeight));
                repaint();

            }
        });
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
