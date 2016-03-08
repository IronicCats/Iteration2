package State;

import Controller.*;
import Model.*;
import View.ViewUtilities.Renderable;

import java.awt.*;

/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public abstract class State implements Tickable, Renderable {
    public static Canvas canvas;
    public static State currentState = null;
    public static State previousState = null;
    private static State[] states = new State[(int) StatesEnum.ExitState.ordinal()];

    private Controller controller;


    public void switchState(StatesEnum state) {
        currentState = states[state.ordinal()];
    }
    public static void addState( StatesEnum s, State state ) {
        System.out.println("Added state: " + state);
        states[s.ordinal()] = state;
    }
    public static void setState( StatesEnum s ) {
        System.out.println("Set state: " + s);
        if(currentState != null) {
            currentState.deactivateListener();
        }
        currentState = states[s.ordinal()];
        currentState.activateListener();
    }
    public static State getCurrentState() { return currentState; }
    public State getStoredState(StatesEnum state) { return states[state.ordinal()];}


    public Controller getController() { return controller; }
    public void setController( Controller controller ) { this.controller = controller; }

    public void activateListener() {
        canvas.addKeyListener(controller);
    }
    public void deactivateListener() {
        canvas.removeKeyListener(controller);
    }
}
