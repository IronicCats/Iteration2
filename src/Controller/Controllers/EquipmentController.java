package Controller.Controllers;

import Controller.Controller;
import State.State;
import State.States.GameState.EquipmentState;
import Utilities.Settings;

import java.awt.event.KeyEvent;

/**
 * Created by Dartyx on 3/7/2016.
 */
public class EquipmentController extends Controller {
    public EquipmentController(EquipmentState state) {
        super(state);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!canGetInput()) {
            return;
        }
        if(e.getKeyCode() == Settings.ESC || e.getKeyCode() == Settings.EQUIP){
            state.switchState(State.GAMESTATE);
        }
    }
}
