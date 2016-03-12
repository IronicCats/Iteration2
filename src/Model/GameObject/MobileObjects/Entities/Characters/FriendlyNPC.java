package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.MobileObjects.Entities.AI.FriendlyController;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.Inventory.Inventory;
import Model.Location;

import java.util.ArrayList;

/**
 * Created by broskj on 3/9/16.
 */
public class FriendlyNPC extends NPC {

    private FriendlyController friendlyController;
    private ArrayList<String> dialog;

    public FriendlyNPC(Location location, int id, Occupation occupation, Inventory inventory, FriendlyController friendlyController, ArrayList<String> dialog) {
        super(location, id, occupation, inventory);
        this.friendlyController = friendlyController;
        friendlyController.setAI(this);
        this.dialog = dialog;
    } // end constructor

    @Override
    public void tick() {
        if (friendlyController != null) {
            getStats().tick();
            friendlyController.tick();
        }
    } // end tick

    public String getRandomDialog() {
        return dialog.get((int) (Math.random() * dialog.size()));
    } // end getRandomDialog

    public FriendlyController getController(){
        return friendlyController;
    }
} // end class FriendlyNPC
