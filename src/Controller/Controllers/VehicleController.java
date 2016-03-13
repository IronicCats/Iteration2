package Controller.Controllers;

import Controller.Controller;
import Model.Abilities.CommandsEnum;
import State.State;
import State.States.GameState.GameState;
import State.States.GameState.VehicleState;
import Utilities.Settings;

import java.awt.event.KeyEvent;

/**
 * Created by Aidan on 3/12/2016.
 */
public class VehicleController extends Controller {

    public VehicleController(VehicleState state) {
        super(state);
    }
    private boolean cameraMoves;

    @Override
    public void keyPressed(KeyEvent e) {
        if(!canGetInput()) { return; }

        if (e.getKeyCode() == Settings.UP || e.getKeyCode() == 38) {
            ((VehicleState)state).move(Settings.NORTH);
        } else if (e.getKeyCode() == Settings.UP_LEFT || e.getKeyCode() == 33) {
            ((VehicleState)state).move(Settings.NW);
        } else if (e.getKeyCode() == Settings.DOWN_LEFT || e.getKeyCode() == 37) {
            ((VehicleState)state).move(Settings.SW);
        } else if (e.getKeyCode() == Settings.DOWN || e.getKeyCode() == 40) {
            ((VehicleState)state).move(Settings.SOUTH);
        } else if (e.getKeyCode() == Settings.DOWN_RIGHT || e.getKeyCode() == 39) {
            ((VehicleState)state).move(Settings.SE);
        } else if (e.getKeyCode() == Settings.UP_RIGHT || e.getKeyCode() == 34) {
            ((VehicleState)state).move(Settings.NE);
        } else if (e.getKeyCode() == Settings.ESC) {        /* open pause menu */
            state.switchState(State.PAUSESTATE);
        } else if (e.getKeyCode() == Settings.EQUIP) {          /* open equipment state */
            state.switchState(State.EQUIPMENTSTATE);

        } else if (e.getKeyCode() == Settings.INVENTORY) {          /* open inventory state */
            state.switchState(State.INVENTORYSTATE);
        } else if (e.getKeyCode() == Settings.MAP) {          /* open map state */
        } else if (e.getKeyCode() == Settings.DROP) {          /* execute inventory dump (temporary?) */
            ((VehicleState)state).unMount();
        } else if(e.getKeyCode() == Settings.F){
            Settings.FOG = !Settings.FOG;
        }

        //these are already listed above
        //if(e.getKeyCode() == KeyEvent.VK_)state.switchState(StatesEnum.GameState);
        //if(e.getKeyCode() == KeyEvent.VK_I)state.switchState(State.INVENTORYSTATE);
        //if(e.getKeyCode() == KeyEvent.VK_E)state.switchState(State.EQUIPMENTSTATE);
        //if(e.getKeyCode() == KeyEvent.VK_P)state.switchState(State.PAUSESTATE);
    }

}
