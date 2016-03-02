package States.States;


import Model.GameObject.Entity.StatsEnum;
import Model.StatStruc;
import Model.GameObject.Item.Item;
import Model.GameObject.Item.CreateItem;
import States.State;
import View.ViewUtilities.Graphics.Assets;
import View.Views.ItemView;

import java.awt.*;


/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public class GameState extends State {

    private CreateItem createItem = new CreateItem();
    ItemView itemView;

    public GameState() {
        // read createItem class!
        Item item = createItem.addOneShot("some name", "removes 5 life", 0, 0, new StatStruc(StatsEnum.LIFE, -5)); // null for now. Stats has not been created
        itemView = new ItemView(item, Assets.POTION);
    }

    public void switchState() {

    }

    public void tick() {

    }

    public void render(Graphics g) {
        itemView.render(g);
    }

    @Override
    public void switchState(States state) {

    }
}
