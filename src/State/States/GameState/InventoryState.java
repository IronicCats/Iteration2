package State.States.GameState;

import Controller.Controllers.InventoryController;
import State.State;
import State.StatesEnum;

import java.awt.*;

/**
 * Created by Dartyx on 3/6/2016.
 */
public class InventoryState extends State{
    GameState game;
    public InventoryState(GameState GS){
        setController(new InventoryController(this));
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
    public void switchState(StatesEnum state) {
        if(getCurrentState() != getStoredState(state)) {
            System.out.println(state);
            setState(state);
        }
    }
}
