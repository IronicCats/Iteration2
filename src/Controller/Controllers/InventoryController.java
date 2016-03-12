package Controller.Controllers;

import Controller.Controller;
import State.State;
import State.States.GameState.InventoryState;
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
        if(e.getKeyCode() == KeyEvent.VK_I){
            state.switchState(State.GAMESTATE);
        }
        else if(e.getKeyCode() == Settings.UP || e.getKeyCode() == KeyEvent.VK_UP) {          /* move cursor up */
            ((InventoryState)state).up();
            System.out.println("YOLO -> UP");
        }
        else if(e.getKeyCode() == Settings.DOWN || e.getKeyCode() == KeyEvent.VK_DOWN) {        /* move cursor down */
            ((InventoryState)state).down();
            System.out.println("YOLO -> DOWN");
        }
        else if(e.getKeyCode() == Settings.DOWN_LEFT || e.getKeyCode() == Settings.UP_LEFT || e.getKeyCode() == KeyEvent.VK_LEFT) {   /* move cursor down */
            ((InventoryState)state).left();
            System.out.println("YOLO -> LEFT");
        }
        else if(e.getKeyCode() == Settings.DOWN_RIGHT || e.getKeyCode() == Settings.UP_RIGHT || e.getKeyCode() == KeyEvent.VK_RIGHT) {  /* move cursor down */
            ((InventoryState)state).right();
            System.out.println("YOLO -> RIGHT");
        }
        else if(e.getKeyCode() == Settings.ENTER) {       /* sell item/confirm/... */
            System.out.println("YOLO -> ENTER");
        }
    }
}
