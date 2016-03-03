package States.States;

import Model.GameObject.MobileObjects.Entities.Stats.StatsEnum;
import Model.Map.Map;
import Model.GameObject.MobileObjects.Entities.Stats.StatStruc;
import Model.GameObject.Item.Item;
import Utilities.CreateItem;
import States.State;
import Utilities.MapUtilities.MakeMap;
import View.ViewUtilities.Graphics.Assets;
import View.Views.ItemView;

import java.awt.*;
import java.util.ArrayList;


/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public class GameState extends State {

    private ArrayList<Item> items;
    ItemView itemView;
    Map map;

    public GameState() {
        // read createItem class!
        map = MakeMap.makeMap();
        Item item = CreateItem.addOneShot("some name", "removes 5 life", 0, 0, new StatStruc(StatsEnum.LIFE, -5));
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
