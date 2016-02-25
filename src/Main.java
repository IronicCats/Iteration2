import Model.Game;
import View.View;
import View.ViewUtilities.MainScreen;
import States.States.InitialState;
import Utitlies.Settings;

/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public class Main {

    public static void main(String args[]) {
        //Create the View,
        View view = new View(new MainScreen("Ironic Cats", Settings.GAMEWIDTH, Settings.GAMEHEIGHT));
        InitialState is = new InitialState();
        is.init();
        Game gameThread = new Game();
        view.start();
        gameThread.start();

    }
}
