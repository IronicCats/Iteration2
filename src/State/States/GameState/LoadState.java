package State.States.GameState;

import Controller.Controllers.LoadController;
import Controller.Controllers.PauseController;
import State.State;
import View.Views.LoadView;
import View.Views.PauseView;

import java.awt.*;

/**
 * Created by Dartyx on 3/7/2016.
 */
public class LoadState extends State{
    private GameState game;
    private LoadView loadView;
    private int lastState;  //0 = Menu,1 = Pause
    public LoadState(GameState GS){
        loadView = new LoadView();
        setController(new LoadController(this));
        game=GS;

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
