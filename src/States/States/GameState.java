package States.States;


import Model.GameObject.Entity.StatsEnum;
import Model.StatStruc;
import Model.Effects.Effect;
import Model.GameObject.Item.Item;
import Model.GameObject.Item.CreateItem;
import Model.GameObject.Item.ItemEnum;
import Model.GameObject.Item.Items.OneShot;
import Model.Location;
import States.State;
import View.Views.ItemView;

import java.awt.*;


/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public class GameState extends State {

    private CreateItem createItem = new CreateItem();

    public GameState() {
        // read createItem class!
        //cool
        Item item = createItem.addOneShot("some name", "removes 5 life", 0, 0, new StatStruc(StatsEnum.LIFE, -5)); // null for now. Stats has not been created
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
