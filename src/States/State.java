package States;

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
    public static State[] states = new State[(int) States.ExitState.ordinal()];
    private Controller controller;

    public abstract void tick();
    public abstract void render(Graphics g);

    public abstract void switchState(States state);

    public static void addState( States s, State state ) { states[s.ordinal()] = state; }
    public static void setState( States s ) {
        if(currentState != null) {
            currentState.deactivateListener();
        }
        currentState = states[s.ordinal()];
        currentState.activateListener();
    }
    public static State getState() { return currentState; }

    public State getPreviousState(){ return previousState; }

    public Controller getController() { return controller; }
    public void setController( Controller controller ) { this.controller = controller; }

    public void activateListener() {
        canvas.addKeyListener(controller);
    }
    public void deactivateListener() {
        System.out.println(canvas.getKeyListeners());
        canvas.removeKeyListener(controller);
    }
    public enum States {
        InitialState, MenuState, GameState, PauseState, LoadState, InventoryState, GearState, MapState, ShopState, ExitState
    }
}