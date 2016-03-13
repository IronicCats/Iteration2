package State.States.GameState;

import Controller.Controllers.SaveController;
import State.State;
import View.Views.SaveView;

import java.awt.*;

/**
 * Created by Andy on 3/3/2016.
 */
public class SaveState extends State {

    private SaveView saveView;

    public SaveState() {
        saveView = new SaveView();
        setController(new SaveController(this));


    }

    public void moveUp() {
        saveView.previous();
    }

    public void moveDown() {
        saveView.next();
    }

    public void tick() {
    }

    public void render(Graphics g) {
        State.GAMESTATE.render(g);
        saveView.render(g);
    }
}
