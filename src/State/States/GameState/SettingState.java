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



    public void tick() {
    }

    public void render(Graphics g) {
        State.GAMESTATE.render(g);
        settingView.render(g,s);
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
    public void change() {
        switch (s) {
            case 0:
                //up
                break;
            case 1:
                //up left key
                break;
            case 2:
                //up right key
                break;
            case 3:
                //down
                break;
            case 4:
                //down left key
                break;
            case 5:
                //down right key
                break;
            case 6:
                //attack
                break;
            case 7:
                //interact
                break;
            case 8:
                //nothing yet
                break;
            case 9:
                //nothing yet
                break;
            default:
                break;


        }
    }
}
