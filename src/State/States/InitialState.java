package State.States;


import Model.GameObject.MobileObjects.Vehicle;
import State.State;
import State.States.GameState.*;
import Model.GameObject.MobileObjects.Vehicle;
import Utilities.MobileObjectUtilities.MobileObjectFactory;

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


        InventoryState inventoryState = new InventoryState();//adding the inv state
        INVENTORYSTATE = inventoryState;
        EquipmentState equipementState = new EquipmentState();//adding the equipment state
        EQUIPMENTSTATE = equipementState;
        //switchState(MENUSTATE);
        SkillsState skillsState = new SkillsState();
        SKILLSSTATE = skillsState;

        PauseState pauseState = new PauseState(); // adding pause state
        PAUSESTATE = pauseState;
        SettingState settingState = new SettingState();
        SETTINGSTATE = settingState;
        SaveState saveState = new SaveState();
        SAVESTATE = saveState;
        LoadState loadState = new LoadState();
        LOADSTATE = loadState;

        //TradeState tradeState = new TradeState(gameState);
        //TRADESTATE = tradeState;

    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {

    }
}
