package State;

import Controller.Controller;
import Model.Tickable;
import State.States.CreateState;
import State.States.GameState.*;
import State.States.InitialState;
import State.States.MenuState;
import View.ViewUtilities.Renderable;

import java.awt.*;

/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public class State implements Tickable, Renderable {

    public static GameState GAMESTATE;
    public static MenuState MENUSTATE;
    public static InventoryState INVENTORYSTATE;
    public static InitialState INITIALSTATE;
    public static CreateState CREATESTATE;
    public static TradeState TRADESTATE;
    public static SkillsState SKILLSSTATE;

    public static LoadState LOADSTATE;
    public static SaveState SAVESTATE;
    public static SettingState SETTINGSTATE;
    public static EquipmentState EQUIPMENTSTATE;
    public static PauseState PAUSESTATE;
    public static VehicleState VEHICLESTATE;

    public static Canvas canvas;
    public static State currentState = null;
    public static State previousState = null;

    private Controller controller;

    public static void switchState(State state) {
        if(currentState instanceof GameState || currentState instanceof VehicleState){
            previousState = currentState;
        }
        setState(state);
    }

    public static void setState(State state) {

        if (currentState != null) {
            currentState.deactivateListener();
        }
        currentState = state;
        currentState.activateListener();
    }

    public static State getCurrentState() {
        return currentState;
    }

    public static State getPreviousState() {
        return previousState;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }


    public void activateListener() {
        canvas.addKeyListener(controller);
    }

    public void deactivateListener() {
        canvas.removeKeyListener(controller);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
