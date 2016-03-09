package State.States.GameState;

import Controller.Controllers.EquipmentController;
import State.State;

import java.awt.*;

/**
 * Created by Dartyx on 3/6/2016.
 */
public class EquipmentState extends State {
    GameState game;
    public EquipmentState(GameState GS){
        setController(new EquipmentController(this));
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
