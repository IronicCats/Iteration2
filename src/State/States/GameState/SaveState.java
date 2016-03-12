package State.States.GameState;

import Controller.Controllers.PauseController;
import Controller.Controllers.SaveController;
import State.State;
import View.Views.PauseView;
import View.Views.SaveView;

import java.awt.*;

/**
 * Created by Andy on 3/3/2016.
 */
public class SaveState extends State {
    private GameState game;
    private SaveView saveView;
    public SaveState(GameState GS){
        saveView = new SaveView();
        setController(new SaveController(this));
        game=GS;

    }

    public void switchState() {

    }

    public void moveUp(){
        saveView.previous();
    }
    public void moveDown(){
        saveView.next();
    }

    public void tick() {
    }

    public void render(Graphics g) {
        game.render(g);
        saveView.render(g);
    }

    @Override
    public void switchState(State state) {
        setState(state);
    }
}
