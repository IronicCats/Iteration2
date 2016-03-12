package State.States.GameState;

import Controller.Controllers.GameController;
import Model.Abilities.CommandsEnum;
import Model.GameObject.Item.Items.Takables.Equippable.Weapon;
import Model.GameObject.AreaEffect.AreaEffect;
import Model.GameObject.AreaEffect.AreaEffectEnum;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;
import Model.Map.Map;
import Model.GameObject.Item.Item;
import Utilities.AreaEffectUtilities.AreaEffectFactory;
import Utilities.ItemUtilities.ItemFactory;
import Utilities.ItemUtilities.ItemsEnum;
import Utilities.MapUtilities.*;
import State.State;
import Utilities.MapUtilities.MakeMap;
import Utilities.MobileObjectUtilities.MobileObjectEnum;
import Utilities.MobileObjectUtilities.MobileObjectFactory;
import Utilities.SaveLoad;
import Utilities.Settings;
import View.ViewUtilities.Camera;
import View.Views.DecalView;
import View.Views.MessageBox.DisplayMessage;
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
    private HashMap<AreaEffect, DecalView> decals;
    private HashMap<MobileObject, MobileObjectView> mobileObjects;
    public static Map map;
    private Camera camera;
    private MapView mapView;
    SaveLoad sl = SaveLoad.getInstance();   //TODO remove this line, currently testing

    private boolean cameraMoving;

    private static Player player;


    public GameState() {
        //need to change this
        cameraMoving = false;
        mapItems = new HashMap<>();
        decals = new HashMap<>();
        mobileObjects = new HashMap<>();

        map = MakeMap.makeMap();
        mapView = MakeMap.makeMapView(map);

        setController(new GameController(this));

        camera = new Camera(Settings.GAMEWIDTH, Settings.GAMEHEIGHT,map);

        //creating a new player
        player =  MobileObjectFactory.Player();
        MobileObjectFactory.makeAsset(MobileObjectEnum.PLAYER, player);
        player.equip((Weapon) ItemFactory.makeItem(ItemsEnum.SWORDFISH_DAGGER, player.getLocation()));

        // initializing items
        mapItems = ItemFactory.initMainMap();
        MakeMap.populateItems(mapItems.keySet().toArray(new Item [mapItems.size()]), map);

        // initializing NPC's
        mobileObjects = MobileObjectFactory.Init(map);
        mobileObjects.put(player, MobileObjectFactory.makeAsset(MobileObjectEnum.PLAYER, player));
        map.setMobileObjects(mobileObjects);

        //area effect
        AreaEffect a = AreaEffectFactory.makeAreaEffect(AreaEffectEnum.LEVELUP, new Location(3,2));
        AreaEffect  b = AreaEffectFactory.makeAreaEffect(AreaEffectEnum.HEAL, new Location(6,4));
        map.placeAreaEffect(a);
        map.placeAreaEffect(b);

        map.setMapItems(mapItems);
    }

    public void switchState() {

    }

    public void move(int degrees) {
        //If camera is moving then movement will be applied to camera, otherwise apply it to the player
        if(cameraMoving){
            camera.move(degrees);
        }else if(Navigation.checkMove(Location.newLocation(degrees, player.getLocation()), map, player) & player.canMove()) { // returns if new location is walkable
            player.move(degrees);
        }else {
            player.face(degrees);
        }
    }

    public void setCameraMoving(boolean movement){
        cameraMoving = movement;
        if(!cameraMoving){
            camera.centerOnPlayer(player); //If you set the camera to not moving. Center on the player to revert back to original offsets
        }
    }

    public MobileObjectView getMobileObjectView(MobileObject o){
            return mobileObjects.get(o);
    }
    public Camera getCamera() {
        return camera;
    }

    public HashMap<MobileObject, MobileObjectView> getMobileObjects() {return mobileObjects;}

    public static Map getMap() {return map;}

    @Override
    public void tick() {
        player.tick();
        for (MobileObject key : mobileObjects.keySet()) {
            if(!(key instanceof Player)) {
                key.tick();
            }
        }
    }

    public void render(Graphics g) {
        if(!cameraMoving) {
            camera.centerOnPlayer(player);
        }
        mapView.render(g, camera.getxOffset(), camera.getyOffset(), player.getLocation());
        
        DisplayMessage.render(g);
    }

    public void executePlayerCommand(CommandsEnum pce){
        player.execute(pce);
    }


    public void playerExamineInventory() {
        player.examinePack();
    } // end playerExamineInventory

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        GameState.player = player;
    }

    public static void setMap(Map map) {
        GameState.map = map;
    }
    public  void setMapView(MapView mv){
        mapView = mv;
    }

    public void setMobileObjects(HashMap<MobileObject, MobileObjectView> mobileObjects) {
        this.mobileObjects = mobileObjects;
    }

    @Override
    public void switchState(State state) {
        setState(state);
    }
}
