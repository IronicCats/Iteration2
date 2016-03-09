package State.States;


import State.State;
import State.States.GameState.GameState;
import State.StatesEnum;

import java.awt.*;

/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public class InitialState extends State {

    public void init(Canvas canvas) {
        State.canvas = canvas;
        //Create the MainMenu
        MenuState menu = new MenuState();
        State.addState(StatesEnum.MenuState, menu);
        //Create the Game
        GameState gameState = new GameState();
        State.addState(StatesEnum.GameState, gameState);

        State.setState(StatesEnum.MenuState);

    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void switchState(StatesEnum s) {

    }
}
