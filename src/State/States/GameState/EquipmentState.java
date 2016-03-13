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
    GameState game;
    EquipmentView equipmentView;
    Inventory inventory;
    Player player;
    Equipment equipment;
    int s;

    public EquipmentState(GameState GS) {
        setController(new EquipmentController(this));
        game = GS;
        this.player=game.getPlayer();
        this.inventory=player.getInventory();
        this.equipment=inventory.getEquipment();
        equipmentView = new EquipmentView(player);
        s=0;
    }

    public void switchState() {

    }

    public void tick() {
    }

    public void render(Graphics g) {
        game.render(g);
        equipmentView.render(g,s);
    }

    @Override

    public void switchState(State state) {
        setState(state);
    }

}
