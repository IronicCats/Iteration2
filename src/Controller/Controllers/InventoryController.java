package Controller.Controllers;

import Controller.Controller;
import State.State;
import State.States.GameState.InventoryState;
import State.States.MenuState;
import Utilities.Settings;

import java.awt.event.KeyEvent;

/**
 * Created by Dartyx on 3/7/2016.
 */
public class InventoryController extends Controller {
    int selector;
    public InventoryController(InventoryState state) {
        super(state);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!canGetInput()) {
            return;
        }
        if(e.getKeyCode() == Settings.ESC ) {
            ((InventoryState)state).switchState(State.PAUSESTATE);
        }
        else if(e.getKeyCode() == KeyEvent.VK_I|| e.getKeyCode() == Settings.INVENTORY){
            state.switchState(state.getPreviousState());
        }
        else if(e.getKeyCode() == Settings.EQUIP) {
            state.switchState(State.EQUIPMENTSTATE);
        }
        else if(e.getKeyCode() == Settings.DROP) {
            ((InventoryState)state).drop();
        }
        else if(e.getKeyCode() == Settings.UP || e.getKeyCode() == KeyEvent.VK_UP) {          /* move cursor up */
            ((InventoryState)state).up();
        }
        else if(e.getKeyCode() == Settings.DOWN || e.getKeyCode() == KeyEvent.VK_DOWN) {        /* move cursor down */
            ((InventoryState)state).down();
        }
        else if(e.getKeyCode() == Settings.DOWN_LEFT || e.getKeyCode() == Settings.UP_LEFT || e.getKeyCode() == KeyEvent.VK_LEFT) {   /* move cursor down */
            ((InventoryState)state).left();
        }
        else if(e.getKeyCode() == Settings.DOWN_RIGHT || e.getKeyCode() == Settings.UP_RIGHT || e.getKeyCode() == KeyEvent.VK_RIGHT) {  /* move cursor down */
            ((InventoryState)state).right();
        }
        else if(e.getKeyCode() == Settings.INTERACT) {       /* interact */
            ((InventoryState)state).interact();
        }
        else if(e.getKeyCode() == Settings.DROP) {
            ((InventoryState)state).drop();
        }
        else if(e.getKeyCode() == KeyEvent.VK_Q){
            ((InventoryState)state).interact();
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){
            ((InventoryState)state).drop();
        }
    }
}
