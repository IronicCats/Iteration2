package StatesEnum.States.GameState;

import Controller.Controllers.GameController;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.GameObject.MobileObjects.Entities.Entity;
import Model.Stats.StatStructure;
import Model.Stats.StatsEnum;
import Model.Map.Map;
import Model.GameObject.Item.Item;
import StatesEnum.StatesEnum;
import Utilities.CreateItem;
import StatesEnum.State;
import Utilities.MapUtilities.MakeMap;
import View.ViewUtilities.Graphics.Assets;
import View.Views.EntityView;
import View.Views.ItemView;
import View.Views.MapView;

import java.awt.*;
import java.util.HashMap;


/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public class GameState extends State {

    private HashMap<Item, ItemView> mapItems;
    private HashMap<Entity, EntityView> entities;
    private Map map;
    private MapView mapView;

    public GameState() {
        setController(new GameController(this));
        mapItems = new HashMap<>();
        entities = new HashMap<>();
        map = MakeMap.makeMap();
        mapView = MakeMap.makeMapView(map);
        Item item = CreateItem.addOneShot("some name", "removes 5 life", 5, 1, new StatStructure(StatsEnum.LIFE, -5));
        mapItems.put(item, new ItemView(item, Assets.POTION));
        Player player = new Player();
    }

    public void switchState() {

    }


    public void movePlayer(int degrees) {
        System.out.println("Moving :" + degrees + " degrees");
    }

    public void tick() {

    }

    public void render(Graphics g) {
        mapView.render(g, -160, -20);
        //keyset for keys, values for values
        for (ItemView itemView : mapItems.values()) {
            itemView.render(g, -160, -20);
        }
    }

    @Override
    public void switchState(StatesEnum state) {

    }
}
