import Model.Game;
import Utilities.Settings;
import View.View;
import View.ViewUtilities.Graphics.Assets;
import View.ViewUtilities.MainScreen;
import State.States.InitialState;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public class Main {

    public static Game gameThread;
    public static void main(String args[]) {
        Assets.init();
        //Create the View,
        MainScreen mainScreen = new MainScreen();
        View view = new View(mainScreen);
        InitialState is = new InitialState();
        is.init(view.getCanvas());

        gameThread = new Game();

        view.start();
        gameThread.start();

    }

}
