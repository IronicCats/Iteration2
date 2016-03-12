package State.States;


import State.State;
import State.States.GameState.*;

import java.awt.*;

/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public class InitialState extends State {

    public void init(Canvas canvas) {
        INITIALSTATE = this;
        State.canvas = canvas;
        currentState = this;
        //Create the MainMenu
        MenuState menu = new MenuState();
        MENUSTATE = menu;
        //Create the Game
        GameState gameState = new GameState();
        GAMESTATE = gameState;


        InventoryState inventoryState = new InventoryState(gameState);//adding the inv state
        INVENTORYSTATE = inventoryState;
        EquipmentState equipementState = new EquipmentState(gameState);//adding the equipment state
        EQUIPMENTSTATE = equipementState;
        //switchState(MENUSTATE);

        PauseState pauseState = new PauseState(gameState); // adding pause state
        PAUSESTATE = pauseState;
        SettingState settingState = new SettingState(gameState);
        SETTINGSTATE = settingState;
        SaveState saveState = new SaveState(gameState);
        SAVESTATE = saveState;
        LoadState loadState = new LoadState(gameState);
        LOADSTATE = loadState;
        TradeState tradeState = new TradeState(gameState);
        TRADESTATE = tradeState;

    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void switchState(State state) {
        setState(state);
    }
}
