package States.States.GameState;

import Model.Stats.StatsEnum;
import Model.Map.Map;
import Model.Stats.StatStruc;
import Model.GameObject.Item.Item;
import Utilities.CreateItem;
import States.State;
import Utilities.MapUtilities.MakeMap;
import View.ViewUtilities.Graphics.Assets;
import View.Views.ItemView;
import View.Views.MapView;

import java.awt.*;
import java.util.ArrayList;


/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public class GameState extends State {

    private ArrayList<Item> items;
    ItemView itemView;
    Map map;
    MapView mapView;

    public GameState() {
        map = MakeMap.makeMap();
        mapView = MakeMap.MakeMapView(map);
        Item item = CreateItem.addOneShot("some name", "removes 5 life", 0, 0, new StatStruc(StatsEnum.LIFE, -5));
        itemView = new ItemView(item, Assets.POTION);
    }

    public void switchState() {

    }

    public void tick() {

    }

    public void render(Graphics g) {
        mapView.render(g, -160, -20);
        itemView.render(g);
    }

    @Override
    public void switchState(States state) {

    }
}
