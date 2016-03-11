package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.MobileObjects.Entities.AI.NPCController;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
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
    public Shopkeeper(Location location, Occupation occupation, Inventory inventory, NPCController controller, ArrayList<String> dialog) {
        super(location, occupation, inventory, controller, dialog);
    } // end constructor

    public void initiateTrade(Pack playerPack) {
        TradeState tradeState = new TradeState(State.GAMESTATE, playerPack, getPack());
        State.setState(tradeState);
    } // end initiateTrade
} // end class Shopkeeper