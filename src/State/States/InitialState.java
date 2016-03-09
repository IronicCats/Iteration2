package State.States;


import State.State;
import State.States.GameState.GameState;

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

        //switchState(MENUSTATE);

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
