import Model.Game;
import View.View;
import View.ViewUtilities.Graphics.Assets;
import View.ViewUtilities.MainScreen;
import StatesEnum.States.InitialState;

/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public class Main {
    public static void main(String args[]) {
        Assets.init();
        //Create the View,
        View view = new View(new MainScreen());
        InitialState is = new InitialState();
        is.init(view.getCanvas());
        Game gameThread = new Game();
        view.start();
        gameThread.start();

    }
}
