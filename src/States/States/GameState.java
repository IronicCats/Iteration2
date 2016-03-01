package States.States;


import Model.Effect;
import Model.GameObject.Item;
import Model.GameObject.ItemEnum;
import Model.GameObject.Items.OneShot;
import Model.Location;
import States.State;
import View.Views.ItemView;

import java.awt.*;


/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public class GameState extends State {

    public GameState() {
        Item item = new OneShot(ItemEnum.ONESHOT, "HitPoition", "Adds Health to Player", new Location(), new Effect[5] );
        ItemView itemView = new ItemView(item);
    }

    public void switchState() {

    }

    public void tick() {

    }

    public void render(Graphics g) {

    }

    @Override
    public void switchState(States state) {

    }
}
