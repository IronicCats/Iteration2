package State.States.GameState;

import Controller.Controllers.PauseController;
import Controller.Controllers.SaveController;
import Controller.Controllers.SettingController;
import State.State;
import View.Views.PauseView;
import View.Views.SaveView;
import View.Views.SettingView;

import java.awt.*;

/**
 * Created by Dartyx on 3/9/2016.
 */
public class SettingState extends State {
    private GameState game;
    private SettingView settingView;
    public SettingState(GameState GS){
        settingView = new SettingView();
        setController(new SettingController(this));
        game=GS;

    }

    public void switchState() {

    }

    public void tick() {
    }

    public void render(Graphics g) {
    game.render(g);
        settingView.render(g);
    }

    @Override
    public void switchState(State state) {
        setState(state);
    }
}
