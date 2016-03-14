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
    int selection;

    public int getSelection(){
        return selection;
    }

    public SettingState() {
        settingView = new SettingView();
        setController(new SettingController(this));
        selection =0;
    }



    public void tick() {
    }

    public void render(Graphics g) {
        State.GAMESTATE.render(g);
        settingView.render(g, selection, ((SettingController)getController()).getStatus());
    }

    public void up(){
        if(selection ==0|| selection ==5){
            selection +=4;}
        else {
            selection--;}
    }
    public void down(){
        if(selection ==4|| selection ==9){
            selection -=4;}
        else {
            selection++;}
    }
    public void left(){
        if(selection <5){
            selection +=5;}
        else {
            selection -=5;}
    }
    public void right(){
        if(selection <5){
            selection +=5;}
        else {
            selection -=5;}
    }
}
