package State.States.GameState;

import Controller.Controllers.GameController;
import Model.GameObject.Decal.Decal;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.GameObject.MobileObjects.Entities.Entity;
import Model.Location;
import Model.Map.Map;
import Model.GameObject.Item.Item;
import State.StatesEnum;
import Utilities.ItemFactory;
import Utilities.ItemsEnum;
import Utilities.MapUtilities.*;
import State.State;
import Utilities.MapUtilities.MakeMap;
import Utilities.Settings;
import View.ViewUtilities.Camera;
import View.ViewUtilities.Graphics.Assets;
import View.Views.DecalView;
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
    private HashMap<Decal, DecalView> decals;
    private Map map;
    private Camera camera;
    private MapView mapView;

    private Player player;
    private EntityView playerView;

    public GameState() {
        setController(new GameController(this));
        mapItems = new HashMap<>();
        entities = new HashMap<>();
        map = MakeMap.makeMap();
        camera = new Camera(Settings.GAMEWIDTH, Settings.GAMEHEIGHT,map);
        mapView = MakeMap.makeMapView(map);

        Item item = ItemFactory.makeItem(ItemsEnum.HEALTH_POTION, new Location(0, 0));
        mapItems.put(item, ItemFactory.makeAsset(ItemsEnum.HEALTH_POTION, item));
        player = new Player();
        playerView = new EntityView(player, Assets.PLAYER);

    }

    public void switchState() {

    }

    public void movePlayer(int degrees) {
        if(Navigation.checkMove(Location.newLocation(degrees, player.getLocation()), map)){ // returns if new location is walkable
            player.move(degrees);
        }
    }

    public void tick() {

    }

    public void render(Graphics g) {
        mapView.render(g, camera.getxOffset(), camera.getyOffset());
        //keyset for keys, values for values
        for (ItemView itemView : mapItems.values()) {
            itemView.render(g, camera.getxOffset(), camera.getyOffset());
        }
        camera.centerOnPlayer(player);
        playerView.render(g, camera.getxOffset(), camera.getyOffset());
    }

    @Override
    public void switchState(StatesEnum state) {

    }
}
