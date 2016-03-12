package State.States.GameState;

import Controller.Controllers.PauseController;
import State.State;
import View.Views.PauseView;

import java.awt.*;

/**
 * Created by Dartyx on 3/7/2016.
 */
public class PauseState extends State {

    private PauseView pauseView;
    private GameState game;

    public PauseState(GameState GS) {
        pauseView = new PauseView();
        setController(new PauseController(this));
        game = GS;
    }

    public void tick() {
    }

    public void moveUp() {
        pauseView.previous();
    }

    public void moveDown() {
        pauseView.next();
    }

    // passes in pause view and map view
    public void render(Graphics g) {
        game.render(g);
        pauseView.render(g);
    }

    @Override
    public void switchState(State state) {

        setState(state);
    }
}
