package State.States.GameState;

import Controller.Controllers.LoadController;
import Controller.Controllers.PauseController;
import State.State;
import View.Views.LoadView;
import View.Views.PauseView;

import java.awt.*;

/**
 * Created by Dartyx on 3/7/2016.
 */
public class LoadState extends State{
    private GameState game;
    private LoadView loadView;
    public LoadState(GameState GS){
        loadView = new LoadView();
        setController(new LoadController(this));
        game=GS;

    }

    public void switchState() {

    }

    public void tick() {
    }

    public void render(Graphics g) {
        game.render(g);
    }

    @Override
    public void switchState(State state) {
        setState(state);
    }
}
