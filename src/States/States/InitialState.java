package States.States;


import States.State;

import java.awt.*;

/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public class InitialState extends State {

    public void init() {
        //Create the MainMenu
        MenuState menu = new MenuState();
        State.addState(States.MenuState, menu);
        //Create the Game
        GameState gameState = new GameState();
        State.addState(States.GameState, gameState);
        State.setState(States.GameState);

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void switchState(States s) {

    }
}
