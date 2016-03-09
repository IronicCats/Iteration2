package State.States.GameState;

import Controller.Controllers.InventoryController;
import Controller.Controllers.PauseController;
import State.State;

import java.awt.*;

/**
 * Created by Dartyx on 3/7/2016.
 */
public class PauseState extends State{

    GameState game;

    public PauseState(GameState GS){
        setController(new PauseController(this));
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
