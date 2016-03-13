package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.MobileObjects.Entities.AI.FriendlyController;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Inventory.Inventory;
import Model.Inventory.Pack;
import Model.Location;
import State.State;
import State.States.GameState.TradeState;

import java.util.ArrayList;

/**
 * Created by broskj on 3/10/16.
 */
public class Shopkeeper extends FriendlyNPC {
    public Shopkeeper(Location location, int id, Occupation occupation, Inventory inventory, FriendlyController controller, ArrayList<String> dialog) {
        super(location, id, occupation, inventory, controller, dialog);
    } // end constructor

    public void initiateTrade(Player player) {
        TradeState tradeState = new TradeState( player, getPack());
        State.setState(tradeState);
    } // end initiateTrade

    public void interact(MobileObject mo) {
        if (mo instanceof Player) {
            initiateTrade(((Player) mo));
        }
    }
} // end class Shopkeeper
