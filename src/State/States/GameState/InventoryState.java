package State.States.GameState;

import Controller.Controllers.InventoryController;
import Model.GameObject.Item.Items.Takables.Equippable.Armor;
import Model.GameObject.Item.Items.Takables.Equippable.Weapon;
import Model.GameObject.Item.Items.Takables.Usable;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.Inventory.Inventory;
import Model.Inventory.Pack;
import State.State;
import View.Views.InventoryView;

import java.awt.*;

/**
 * Created by Dartyx on 3/6/2016.
 */
public class InventoryState extends State {

    Inventory inventory;
    Player player;
    Pack pack;
    InventoryView invView;
    int selector;

    public InventoryState() {
        setController(new InventoryController(this));
        selector = 0;


        player=State.GAMESTATE.getPlayer();
        inventory=player.getInventory();
        pack=player.getPack();
        invView=new InventoryView(pack,player);

    }


    public void switchState() {

    }

    public void tick() {
    }

    public void render(Graphics g) {
        State.GAMESTATE.render(g);
        invView.render(g, selector);

    }

    @Override
    public void switchState(State state) {

        setState(state);
    }

    //movement functions
    public void up() {
        if (selector - 4 < 0) selector += 12;
        else selector -= 4;
        //System.out.println(selector);
    }

    public void down() {
        if (selector + 4 > 15) selector -= 12;
        else selector += 4;
        //System.out.println(selector);
    }

    public void right() {
        if (selector % 4 == 3) selector -= 3;
        else selector++;
        //System.out.println(selector);
    }

    public void left() {
        if (selector % 4 == 0) selector += 3;
        else selector--;
        //System.out.println(selector);
    }

    //interaction

    public void interact() {
        if (pack.get(selector) instanceof Weapon || pack.get(selector) instanceof Armor) {
            equip();
        } else if (pack.get(selector) instanceof Usable) {
            player.applyEffect(pack.use(selector));
            inventory.remove(selector);
        }
    } // end interact

    public void equip() {
        player.equip(selector);
    } // end equip


    public void drop() {
        player.drop(selector);
    } // end drop

}
