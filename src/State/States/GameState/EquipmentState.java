package State.States.GameState;

import Controller.Controllers.EquipmentController;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.Inventory.Equipment;
import Model.Inventory.Inventory;
import Model.Inventory.Pack;
import State.State;
import View.Views.EquipmentView;
import View.Views.InventoryView;

import java.awt.*;

/**
 * Created by Dartyx on 3/6/2016.
 */
public class EquipmentState extends State {

    EquipmentView equipmentView;
    Inventory inventory;
    Player player;
    Equipment equipment;
    int s;

    public EquipmentState() {
        setController(new EquipmentController(this));

        this.player=State.GAMESTATE.getPlayer();
        this.inventory=player.getInventory();
        this.equipment=inventory.getEquipment();
        equipmentView = new EquipmentView(player);
        s=0;
    }
    public void up(){
        if(s==1){s+=9;}
        else if(s==3||s==5){s+=6;}
        else if(s==11){s-=6;}
        else s-=3;
    }
    public void down(){
        if(s==5){s-=6;}
        else if(s==9||s==11){s-=6;}
        else if(s==10){s-=9;}
        else s+=3;
    }
    public void left(){
        if(s==1){}
        else if(s==3||s==9){s-=2;}
        else if(s==6){s++;}
        else s--;
    }
    public void right(){
        if(s==1){}
        else if(s==5||s==11){s-=2;}
        else if(s==7){s--;}
        else s++;

    }

    public void switchState() {

    }

    public void tick() {
    }

    public void render(Graphics g) {
        State.GAMESTATE.render(g);
        equipmentView.render(g,s);
    }

    @Override

    public void switchState(State state) {
        setState(state);
    }

}
