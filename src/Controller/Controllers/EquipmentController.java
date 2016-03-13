package Controller.Controllers;

import Controller.Controller;
import State.State;
import State.States.GameState.EquipmentState;
import State.States.GameState.InventoryState;
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
        if(e.getKeyCode() == Settings.EQUIP){
            state.switchState(State.GAMESTATE);
        }
        else if(e.getKeyCode() == Settings.INVENTORY){
            state.switchState(State.INVENTORYSTATE);
        }
        else if(e.getKeyCode() == Settings.ESC){
            state.switchState(State.PAUSESTATE);
        }
        else if(e.getKeyCode() == Settings.UP || e.getKeyCode() == KeyEvent.VK_UP) {          /* move cursor up */
            ((EquipmentState)state).up();
        }
        else if(e.getKeyCode() == Settings.DOWN || e.getKeyCode() == KeyEvent.VK_DOWN) {        /* move cursor down */
            ((EquipmentState)state).down();
        }
        else if(e.getKeyCode() == Settings.DOWN_LEFT || e.getKeyCode() == Settings.UP_LEFT || e.getKeyCode() == KeyEvent.VK_LEFT) {   /* move cursor down */
            ((EquipmentState)state).left();
        }
        else if(e.getKeyCode() == Settings.DOWN_RIGHT || e.getKeyCode() == Settings.UP_RIGHT || e.getKeyCode() == KeyEvent.VK_RIGHT) {  /* move cursor down */
            ((EquipmentState)state).right();
        }
        else if(e.getKeyCode() == Settings.INTERACT){
            ((EquipmentState)state).unequip();
        }
    }
}
