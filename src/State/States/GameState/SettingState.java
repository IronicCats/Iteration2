package State.States.GameState;

import Controller.Controllers.SettingController;
import State.State;
import View.Views.SettingView;

import java.awt.*;

/**
 * Created by Dartyx on 3/9/2016.
 */
public class SettingState extends State {

    private SettingView settingView;

    public SettingState() {
        settingView = new SettingView();
        setController(new SettingController(this));
    }

    public void tick() {
    }

    public void render(Graphics g) {
        State.GAMESTATE.render(g);
        settingView.render(g);
    }
}
