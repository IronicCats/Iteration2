package State.States.GameState;

import Controller.Controllers.LoadController;
import State.State;
import View.Views.LoadView;

import java.awt.*;

/**
 * Created by Dartyx on 3/7/2016.
 */
public class LoadState extends State {

    private LoadView loadView;


    private int lastState;  //0 = Menu,1 = Pause
    public LoadState() {
        loadView = new LoadView();
        setController(new LoadController(this));


    }

    public void setLastState(int lastState) {
        this.lastState = lastState;
    }

    public int getLastState() {
        return lastState;
    }

    public void switchState() {

    }
    public void moveUp(){
        loadView.previous();
    }
    public void moveDown(){loadView.next();}

    public void tick() {
    }

    public void render(Graphics g) {
        //game.render(g);
        loadView.render(g);
    }

    @Override
    public void switchState(State state) {
        setState(state);
    }
}
