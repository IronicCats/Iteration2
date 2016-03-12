package Controller.Controllers;

import Controller.Controller;
import Model.Abilities.CommandsEnum;
import State.State;
import State.States.GameState.InventoryState;
import State.States.GameState.TradeState;
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
        //this is your stuff, i just commented it cause fuck that shit
        //if(e.getKeyCode() == Settings.ESC) {                    /* exit trade */
        //    state.switchState(State.GAMESTATE);
        //    System.out.println("exiting TradeState");
        //} else if(e.getKeyCode() == Settings.UP) {              /* move cursor up */
        //    System.out.println("TradeController -> UP");
        //} else if(e.getKeyCode() == Settings.DOWN) {            /* move cursor down */
        //    System.out.println("TradeController -> DOWN");
        //} else if(e.getKeyCode() == Settings.DOWN_LEFT) {       /* move cursor left */
        //    System.out.println("TradeController -> LEFT");
        //} else if(e.getKeyCode() == Settings.DOWN_RIGHT) {      /* move cursor right */
        //    System.out.println("TradeController -> RIGHT");
        //} else if(e.getKeyCode() == Settings.ENTER) {           /* sell item/confirm/... */
        //    System.out.println("TradeController -> ENTER");
        //    ((TradeState) state).executeCommand(CommandsEnum.make_transaction);
        //}
        if(e.getKeyCode() == Settings.ESC) {                    /* exit trade */
                state.switchState(State.GAMESTATE);
                System.out.println("exiting TradeState");
        }
        else if(e.getKeyCode() == Settings.UP || e.getKeyCode() == KeyEvent.VK_UP) {          /* move cursor up */
            ((TradeState)state).executeCommand(CommandsEnum.up);
        }
        else if(e.getKeyCode() == Settings.DOWN || e.getKeyCode() == KeyEvent.VK_DOWN) {        /* move cursor down */
            ((TradeState)state).executeCommand(CommandsEnum.down);
        }
        else if(e.getKeyCode() == Settings.DOWN_LEFT || e.getKeyCode() == Settings.UP_LEFT || e.getKeyCode() == KeyEvent.VK_LEFT) {   /* move cursor down */
            ((TradeState)state).executeCommand(CommandsEnum.left);
        }
        else if(e.getKeyCode() == Settings.DOWN_RIGHT || e.getKeyCode() == Settings.UP_RIGHT || e.getKeyCode() == KeyEvent.VK_RIGHT) {  /* move cursor down */
            ((TradeState)state).executeCommand(CommandsEnum.right);
        }
        else if(e.getKeyCode() == Settings.INTERACT) {       /* initiate transaction */
            ((TradeState)state).executeCommand(CommandsEnum.make_transaction);
        }


    } // end keyPressed

} // end class TradeController
