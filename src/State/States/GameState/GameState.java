package State.States.GameState;

import Controller.Controllers.GameController;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.GameObject.MobileObjects.Entities.Entity;
import Model.Location;
import Model.Map.Map;
import Model.GameObject.Item.Item;
import State.StatesEnum;
import Utilities.ItemFactory;
import Utilities.ItemsEnum;
import State.State;
import Utilities.MapUtilities.MakeMap;
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

        Item item = ItemFactory.makeItem(ItemsEnum.HEALTH_POTION, new Location(0, 0));
        mapItems.put(item, ItemFactory.makeAsset(ItemsEnum.HEALTH_POTION, item));
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
