package State.States.GameState;

import Controller.Controllers.InventoryController;
import State.State;
import View.Views.InventoryView;

import java.awt.*;

/**
 * Created by Dartyx on 3/6/2016.
 */
public class InventoryState extends State{
    GameState game;
    InventoryView invView=new InventoryView();
    int selector;
    public InventoryState(GameState GS){
        setController(new InventoryController(this));
        game=GS;
        selector=0;
    }

    public void switchState() {

    }

    public void tick() {
    }

    public void render(Graphics g) {
        game.render(g);
        invView.render(g,selector);
    }

    @Override
    public void switchState(State state) {

        setState(state);
    }
    public void up(){
        if(selector-4<0)selector+=12;
        else selector-=4;
        System.out.println(selector);
    }
    public void down(){
        if(selector+4>15)selector-=12;
        else selector+=4;
        System.out.println(selector);
    }
    public void right(){
        if(selector%4==3)selector-=3;
        else selector++;
        System.out.println(selector);
    }
    public void left(){
        if(selector%4==0)selector+=3;
        else selector--;
        System.out.println(selector);
    }
}
