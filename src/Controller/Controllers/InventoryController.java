package Controller.Controllers;

import Controller.Controller;
import State.States.GameState.EquipmentState;
import State.States.GameState.InventoryState;
import State.StatesEnum;

import java.awt.event.KeyEvent;

/**
 * Created by Dartyx on 3/7/2016.
 */
public class InventoryController extends Controller {
    public InventoryController(InventoryState state) {
        super(state);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!canGetInput()) {
            return;
        }
        if(e.getKeyCode() == KeyEvent.VK_I){
            state.switchState(StatesEnum.GameState);
            System.out.println("ayylmao");
        }
    }
}
