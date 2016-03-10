package Controller.Controllers;

import Controller.Controller;
import State.State;
import Utilities.Settings;

import java.awt.event.KeyEvent;

/**
 * Created by broskj on 3/9/16.
 */
public class TradeController extends Controller {
    public TradeController(State state) {
        super(state);
    } // end constructor

    @Override
    public void keyPressed(KeyEvent e) {
        if (!canGetInput()) {
            return;
        }
        if(e.getKeyCode() == Settings.ESC) {                /* exit trade */
            state.switchState(State.GAMESTATE);
            System.out.println("exiting TradeState");
        } else if(e.getKeyCode() == Settings.UP) {          /* move cursor up */
            System.out.println("TradeController -> UP");
        } else if(e.getKeyCode() == Settings.DOWN) {        /* move cursor down */
            System.out.println("TradeController -> DOWN");
        } else if(e.getKeyCode() == Settings.DOWN_LEFT) {   /* move cursor left */
            System.out.println("TradeController -> LEFT");
        } else if(e.getKeyCode() == Settings.DOWN_RIGHT) {  /* move cursor right */
            System.out.println("TradeController -> RIGHT");
        } else if(e.getKeyCode() == Settings.ENTER) {       /* sell item/confirm/... */
            System.out.println("TradeController -> ENTER");
        }
    } // end keyPressed

} // end class TradeController
