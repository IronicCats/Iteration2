package State.States.GameState;

import Controller.Controllers.LoadController;
import State.State;
import View.Views.LoadView;

import java.awt.*;

/**
 * Created by Dartyx on 3/7/2016.
 */
public class LoadState extends State {

    private LoadView loadView;

    public LoadState() {
        loadView = new LoadView();
        setController(new LoadController(this));


    }

    public void switchState() {

    }

    public void tick() {
    }

    public void render(Graphics g) {
        //game.render(g);
    }

    @Override
    public void switchState(State state) {
        setState(state);
    }
}
