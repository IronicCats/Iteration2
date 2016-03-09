package Model.GameObject.MobileObjects.Entities.Characters;

import Model.GameObject.MobileObjects.Entities.AI.NPCController;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.Inventory.Inventory;
import Model.Location;

import java.util.ArrayList;

/**
 * Created by broskj on 3/9/16.
 */
public class FriendlyNPC extends NPC {
    private ArrayList<String> dialog;

    public FriendlyNPC(Location location, Occupation occupation, Inventory inventory, NPCController controller, ArrayList<String> dialog) {
        super(location, occupation, inventory, controller);
        this.dialog = dialog;
    } // end constructor

    public String getRandomDialog() {
        return dialog.get((int)(Math.random() * dialog.size()));
    } // end getRandomDialog
} // end class FriendlyNPC
