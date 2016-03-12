package State.States.GameState;

import Controller.Controllers.InventoryController;
import Model.Abilities.CommandsEnum;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import State.State;
import View.Views.InventoryView;

import java.awt.*;

/**
 * Created by Dartyx on 3/6/2016.
 */
public class InventoryState extends State{
    GameState game;
    InventoryView invView=new InventoryView();
    Player player;
    int selector;

    public InventoryState(GameState GS){
        setController(new InventoryController(this));
        game=GS;
        selector=0;
    } // end constructor

    public void registerPlayer(Player player) {
        this.player = player;
    } // end registerPlayer

    public void switchState() {

    }

    public void tick() {
    }

    public void executeCommand(CommandsEnum command) {
        switch(command) {
            case up:                /* move cursor up */
                up();
                break;
            case down:              /* move cursor down */
                down();
                break;
            case left:              /* move cursor left */
                left();
                break;
            case right:             /* move cursor right */
                right();
                break;
            case drop:              /* drop item at selector */
                break;
            case equip:             /* equip item at selector */
                break;
            case use:               /* use item at selector */
                break;
            default:
                System.out.println("Don't sent that command to the inventory state");
                break;
        }
    } // end executeCommand

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
