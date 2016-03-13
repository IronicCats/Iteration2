package State.States.GameState;

import Controller.Controllers.EquipmentController;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.Inventory.Equipment;
import Model.Inventory.EquipmentSlotEnum;
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
        s=1;
    }
    public void up(){
        if(s==1){s+=9;}
        else if(s==3||s==5){s+=6;}
        else s-=3;
    }
    public void down(){
        if(s==9||s==11){s-=6;}
        else if(s==10){s-=9;}
        else s+=3;
    }
    public void left(){
        if(s==1){}
        else if(s==3||s==9){s+=2;}
        else if(s==6){s+=2;}
        else s--;
    }
    public void right(){
        if(s==1){}
        else if(s%3==2){s-=2;}
        else s++;

    }
    public void unequip(){
        switch(s){
            case 1:
                player.unequip(EquipmentSlotEnum.HEAD);
                break;
            case 3:
                player.unequip(EquipmentSlotEnum.MAINHAND);
                break;
            case 4:
                player.unequip(EquipmentSlotEnum.CHEST);
                break;
            case 5:
                player.unequip(EquipmentSlotEnum.OFFHAND);
                player.unequip(EquipmentSlotEnum.SHIELD);
                break;
            case 6:
                player.unequip(EquipmentSlotEnum.GLOVES);
                break;
            case 7:
                player.unequip(EquipmentSlotEnum.HEAD);
                break;
            case 8:
                player.unequip(EquipmentSlotEnum.GLOVES);
                break;
            case 9:
                player.unequip(EquipmentSlotEnum.ACCESSORY1);
                break;
            case 10:
                player.unequip(EquipmentSlotEnum.BOOTS);
                break;
            case 11:
                player.unequip(EquipmentSlotEnum.ACCESSORY2);
                break;
            default:
                break;
        }
    }
    public void drop(){}
    public void switchState() {

    }

    public void tick() {
    }

    public void render(Graphics g) {
        State.GAMESTATE.render(g);
        equipmentView.render(g,s);
    }

}
