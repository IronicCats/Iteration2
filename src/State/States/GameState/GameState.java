package State.States.GameState;

import Controller.Controllers.GameController;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;
import Model.Map.Map;
import Model.GameObject.Item.Item;
import State.StatesEnum;
import Utilities.ItemUtilities.ItemFactory;
import Utilities.ItemUtilities.ItemsEnum;
import Utilities.MapUtilities.*;
import State.State;
import Utilities.MapUtilities.MakeMap;
import View.ViewUtilities.Graphics.Assets;
import View.Views.MobileObjectView;
import View.Views.ItemView;
import View.Views.MapView;

import java.awt.*;
import java.util.HashMap;


/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public class GameState extends State {

    private HashMap<Item, ItemView> mapItems;
    private HashMap<MobileObject, MobileObjectView> mobileObjects;
    private Map map;
    private MapView mapView;

    private Player player;
    private MobileObjectView playerView;

    public GameState() {
        setController(new GameController(this));
        mapItems = new HashMap<>();
        mobileObjects = new HashMap<>();
        map = MakeMap.makeMap();
        mapView = MakeMap.makeMapView(map);

        Item item = ItemFactory.makeItem(ItemsEnum.HEALTH_POTION, new Location(0, 0));
        mapItems.put(item, ItemFactory.makeAsset(ItemsEnum.HEALTH_POTION, item));
        player = new Player();
        playerView = new MobileObjectView(player, Assets.PLAYER);

    }

    public void switchState() {

    }

    public void movePlayer(int degrees) {
        if(Navigation.checkMove(Location.newLocation(degrees, player.getLocation()), map, player)){ // returns if new location is walkable
            player.move(degrees);
        }
    }

    public void moveObject(int degrees, MobileObject mobileObject){
        if(Navigation.checkMove(Location.newLocation(degrees, player.getLocation()), map, mobileObject)){ // returns if new location is walkable
            mobileObject.move(degrees);
        }
    }

    public void tick() {

    }

    public void render(Graphics g) {
        mapView.render(g, -160, -20);
        //keyset for keys, values for values
        for (ItemView itemView : mapItems.values()) {
            itemView.render(g, -160, -20);
        }
        playerView.render(g, -160, -20);
    }

    @Override
    public void switchState(StatesEnum state) {

    }
}
