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
    int s;

    public SettingState() {
        settingView = new SettingView();
        setController(new SettingController(this));
        s=0;

    }

    public void switchState() {

    }

    public void tick() {
    }

    public void render(Graphics g) {
        State.GAMESTATE.render(g);
        settingView.render(g,s);
    }

    @Override
    public void switchState(State state) {
        setState(state);
    }
    public void up(){
        if(s==0||s==5){s+=4;}
        else {s--;}
    }
    public void down(){
        if(s==4||s==9){s-=4;}
        else {s++;}
    }
    public void left(){
        if(s<5){s+=5;}
        else {s-=5;}
    }
    public void right(){
        if(s<5){s+=5;}
        else {s-=5;}
    }
}
