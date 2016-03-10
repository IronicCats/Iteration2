package State.States.GameState;

import Controller.Controllers.TradeController;
import State.State;

import java.awt.*;

/**
 * Created by broskj on 3/9/16.
 */
public class TradeState extends State {
    GameState game;

    public TradeState(GameState game) {
        setController(new TradeController(this));
        this.game = game;
    } // end constructor

    public void tick() {
    }

    public void render(Graphics g) {
        game.render(g);
    }
    @Override

    public void switchState(State state) {
        setState(state);
    }
} // end TradeState
