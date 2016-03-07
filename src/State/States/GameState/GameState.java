package State.States.GameState;

import Controller.Controllers.GameController;
import Model.GameObject.AreaEffect.AreaEffect;
import Model.GameObject.AreaEffect.AreaEffectEnum;
import Model.GameObject.Decal.Decal;
import Model.GameObject.Decal.DecalEnum;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Occupation;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Smasher;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.GameObject.MobileObjects.Entities.Entity;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Inventory.Inventory;
import Model.Location;
import Model.Map.Map;
import Model.GameObject.Item.Item;
import Model.Map.Tile;
import Model.Stats.CharacterStats;
import State.StatesEnum;
import Utilities.AIUtilities.Astar;
import Utilities.AreaEffectUtilities.AreaEffectFactory;
import Utilities.ItemUtilities.ItemFactory;
import Utilities.ItemUtilities.ItemsEnum;
import Utilities.MapUtilities.*;
import State.State;
import Utilities.MapUtilities.MakeMap;
import Utilities.SaveLoad;
import Utilities.Settings;
import View.ViewUtilities.Camera;
import View.ViewUtilities.Graphics.Assets;
import View.Views.DecalView;
import View.Views.MobileObjectView;
import View.Views.ItemView;
import View.Views.MapView;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public class GameState extends State {

    private HashMap<Item, ItemView> mapItems;
    private HashMap<AreaEffect, DecalView> decals;
    private HashMap<MobileObject, MobileObjectView> mobileObjects;
    private Map map;
    private Camera camera;
    private MapView mapView;
    SaveLoad sl = SaveLoad.getInstance();   //TODO remove this line, currently testing




    private Player player;
    private MobileObjectView playerView;

    public GameState() {
        setController(new GameController(this));
        mapItems = new HashMap<>();
        mobileObjects = new HashMap<>();
        decals = new HashMap<>();
        map = MakeMap.makeMap();
        camera = new Camera(Settings.GAMEWIDTH, Settings.GAMEHEIGHT,map);
        mapView = MakeMap.makeMapView(map);

        Item item = ItemFactory.makeItem(ItemsEnum.HEALTH_POTION, new Location(0, 0));
        player = new Player();
        Item chest = ItemFactory.makeItem(ItemsEnum.CLOSED_TREASURE_CHEST, new Location(5, 5));
        map.placeItem(item);
        mapItems.put(item, ItemFactory.makeAsset(ItemsEnum.HEALTH_POTION, item));
        mapItems.put(chest, ItemFactory.makeAsset(ItemsEnum.CLOSED_TREASURE_CHEST, chest));
        //creating a new player
        player = new Player(new Location(2, 2), new CharacterStats(), new Smasher(), new Inventory());
        playerView = new MobileObjectView(player, Assets.PLAYER);

        //from here down is Aidan's test and imports are because of Aidan if they are related to this
        Astar astar = new Astar(map);
        ArrayList<Tile> path;
        path = astar.Findpath(map.getTile(0,0), map.getTile(5,4));
        for(int i = 0; i < path.size(); i++){
            System.out.println("xLocation " + i + " " + path.get(i).getLocation().getX());
            System.out.println("yLocation " + i + " " + path.get(i).getLocation().getY());
        }

        //area effect
        AreaEffect a = AreaEffectFactory.makeAreaEffect(AreaEffectEnum.LEVELUP, new Location(1,1));
        decals.put(a, AreaEffectFactory.makeAsset(new Decal(new Location(1,1),DecalEnum.GOLDSTAR)));
        map.placeAreaEffect(a);
    }

    public void switchState() {

    }

    public void movePlayer(int degrees) {
        if(Navigation.checkMove(Location.newLocation(degrees, player.getLocation()), map, player) & player.canMove()) { // returns if new location is walkable
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
        mapView.render(g, camera.getxOffset(), camera.getyOffset());
        //keyset for keys, values for values
        for (ItemView itemView : mapItems.values()) {
            itemView.render(g, camera.getxOffset(), camera.getyOffset());
        }
        for (DecalView decalView : decals.values()) {
            decalView.render(g, camera.getxOffset(), camera.getyOffset());
        }
        camera.centerOnPlayer(player);
        playerView.render(g, camera.getxOffset(), camera.getyOffset());
    }

    @Override
    public void switchState(StatesEnum state) {

    }
}
