package Controller.Controllers;

import State.State;
import State.States.GameState.PauseState;
import Controller.Controller;

import java.awt.event.KeyEvent;

/**
 * Created by Dartyx on 3/7/2016.
 */
public class PauseController extends Controller{

    public PauseController(PauseState state) {
        super(state);
    }

    public void keyPressed(KeyEvent e) {
        if (!canGetInput()) {
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            state.switchState(State.GAMESTATE);
            System.out.println("Game is Paused");
        }

    }
}
