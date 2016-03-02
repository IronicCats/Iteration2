package States.States;

import Model.GameObject.Entity.StatsEnum;
import Model.Map.Map;
import Model.GameObject.Entity.Stats.StatStruc;
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
        // read createItem class!
        map = MakeMap.makeMap();
        MapView mapView = MakeMap.MakeMapView(map);
        Item item = CreateItem.addOneShot("some name", "removes 5 life", 0, 0, new StatStruc(StatsEnum.LIFE, -5)); // null for now. Stats has not been created
        itemView = new ItemView(item, Assets.POTION);
    }

    public void switchState() {

    }

    public void tick() {

    }

    public void render(Graphics g) {
        itemView.render(g);
        //mapView.render(g,0,0);
    }

    @Override
    public void switchState(States state) {

    }
}
