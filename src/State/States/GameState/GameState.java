package State.States.GameState;

import Controller.Controllers.GameController;
import Model.Abilities.CommandsEnum;
import Model.GameObject.AreaEffect.AreaEffect;
import Model.GameObject.AreaEffect.AreaEffectEnum;
import Model.GameObject.AreaEffect.TeleportAreaEffect;
import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Takables.Equippable.Weapon;
import Model.GameObject.MobileObjects.Entities.Characters.Character;
import Model.GameObject.MobileObjects.Entities.Characters.HostileNPC;
import Model.GameObject.MobileObjects.Entities.Characters.NPC;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.GameObject.MobileObjects.Entities.Pet;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;
import Model.Map.Map;
import Model.Stats.Stats;
import State.State;
import Utilities.AreaEffectUtilities.AreaEffectFactory;
import Utilities.ItemUtilities.ItemFactory;
import Utilities.ItemUtilities.ItemsEnum;
import Utilities.MapUtilities.MakeMap;
import Utilities.MapUtilities.Navigation;
import Utilities.MobileObjectUtilities.MobileObjectEnum;
import Utilities.MobileObjectUtilities.MobileObjectFactory;
import Utilities.MobileObjectUtilities.RespawnQueue;
import Utilities.SaveLoad;
import Utilities.Settings;
import View.ViewUtilities.Camera;
import View.Views.*;
import View.Views.MessageBox.DisplayMessage;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


/**
 * Created by Joshua Kegley on 2/24/2016.
 */
public class GameState extends State {

    public StatusView statusView;
    public HashMap<Item, ItemView> mapItems;
    public HashMap<AreaEffect, DecalView> decals;
    public HashMap<MobileObject, MobileObjectView> mobileObjects;
    private ArrayList<AreaEffect> areaEffects = new ArrayList<>();
    public static Map map;
    public Camera camera;
    public MapView mapView;

   public boolean cameraMoving;

    public static Player player;
    public boolean loading;


    public GameState() {
        //need to change this
        loading = false;
        cameraMoving = false;
        mapItems = new HashMap<>();
        decals = new HashMap<>();
        mobileObjects = new HashMap<>();

        map = MakeMap.makeMap();
        mapView = MakeMap.makeMapView(map);

        setController(new GameController(this));

        camera = new Camera(Settings.GAMEWIDTH, Settings.GAMEHEIGHT, map);

        // creating player
        player = MobileObjectFactory.makeSmasher();
        MobileObjectFactory.makeAsset(MobileObjectEnum.PLAYER, player);

        // initializing items
        mapItems = ItemFactory.initMainMap();
        MakeMap.populateItems(mapItems.keySet().toArray(new Item[mapItems.size()]), map);

        // initializing NPC's
        mobileObjects = MobileObjectFactory.Init(map, player);

        // adding player to hash map
        mobileObjects.put(player, MobileObjectFactory.makeAsset(MobileObjectEnum.PLAYER, player));

        // syncing pet with player
        for (MobileObject key : mobileObjects.keySet()) {
            if(key instanceof Pet){
                player.setPet((Pet)key);
            }
        }

        // syncing mobile objects with map
        map.setMobileObjects(mobileObjects);

        //area effect

        TeleportAreaEffect e = new TeleportAreaEffect(new Location(18,13), new Location(18, 11));
        areaEffects = AreaEffectFactory.init();

        for(AreaEffect effect: areaEffects){
            map.placeAreaEffect(effect);
        }

        map.placeTeleportAreaEffectBeginning(e);

        map.setMapItems(mapItems);

        statusView = new StatusView(player);
    }

    public GameState(boolean bs){
        loading = true;
        //map = SaveLoad.getGameMap();
       // mapView = MakeMap.makeMapView(map);

        setController(new GameController(this));
        //player = S
    }

    public GameState(Player p,Map m, MapView mv,HashMap<MobileObject, MobileObjectView> mo,HashMap<AreaEffect, DecalView> d,HashMap<Item, ItemView> mi){
        loading = true;
        cameraMoving = false;
        map = m;
        mapView = mv;
        setController(new GameController(this));
        camera = new Camera(Settings.GAMEWIDTH, Settings.GAMEHEIGHT, map);
        player = p;


        mobileObjects = mo;
        decals = d;
        mapItems = mi;

        mobileObjects.put(player, MobileObjectFactory.makeAsset(MobileObjectEnum.PLAYER, player));
        map.setMobileObjects(mobileObjects);
        //System.out.println(getMobileObjectView(player));
        //System.out.println("x:" +Integer.toString(player.getLocation().getX()));
        //System.out.println("y:" +Integer.toString(player.getLocation().getY()));
        mapView.update();


        //idk
        Iterator it = mobileObjects.entrySet().iterator();
        while (it.hasNext()) {
            java.util.Map.Entry pair = (java.util.Map.Entry) it.next();
            //System.out.println(pair.getKey() + "  This is the related view: " + pair.getValue());
            //MobileObject a = (MobileObject)pair.getKey();
           // MobileObjectView ab = (MobileObjectView)pair.getValue();
            //int x = a.getX();
            //int y = a.getY();
              map.getTile(((MobileObject)pair.getKey()).getLocation()).register((MobileObject)pair.getKey());
            ((MobileObject) pair.getKey()).registerTile(((MobileObject)pair.getKey()).getLocation());


            //it.remove(); ??? Says it avoids CurrentModificationException
        }
        if(!mapItems.isEmpty()) {
            Iterator ia = mapItems.entrySet().iterator();
            while (ia.hasNext()) {
                java.util.Map.Entry q = (java.util.Map.Entry) ia.next();
                System.out.println(q.getKey() + "  This is the related view: " + q.getValue());

                Item b = (Item) q.getKey();
                System.out.println(b);

                map.getTile(b.getLocation()).addItem(b);
            }
        }
        map.setMapItems(mapItems);
        map.setMobileObjects(mobileObjects);
        AreaEffect a = AreaEffectFactory.makeAreaEffect(AreaEffectEnum.LEVELUP, new Location(3, 2));
        map.placeAreaEffect(a);
        //mapView.update();
        statusView = new StatusView(player);

    }

    public void switchState() {

    }

    public void move(int degrees) {
        //If camera is moving then movement will be applied to camera, otherwise apply it to the player
        /*
        if(player.canMove())
            System.out.println("Player can move.");
        else
            System.out.println("Player can't move");
        */

        if (cameraMoving) {
            camera.move(degrees);
        } else if (Navigation.checkMove(Location.newLocation(degrees, player.getLocation()), map, player) & player.canMove()) { // returns if new location is walkable
            player.move(degrees);
        } else {
            player.face(degrees);
        }

    }

    public void setCameraMoving(boolean movement) {
        cameraMoving = movement;
        if (!cameraMoving) {
            camera.centerOnObject(player); //If you set the camera to not moving. Center on the player to revert back to original offsets
        }
    }

    public MobileObjectView getMobileObjectView(MobileObject o) {
        return mobileObjects.get(o);
    }

    public Camera getCamera() {
        return camera;
    }

    public HashMap<MobileObject, MobileObjectView> getMobileObjects() {
        return mobileObjects;
    }

    public static Map getMap() {
        return map;
    }

    @Override
    public void tick() {
        if(loading)
            return;
        for (MobileObject key : mobileObjects.keySet()) {
            key.tick();
        }
        RespawnQueue.tick();
    }

    public void render(Graphics g) {
        if(loading)
            return;
        if(!cameraMoving){
            camera.centerOnObject(GameState.player);
        }
        mapView.render(g, camera.getxOffset(), camera.getyOffset(), player.getLocation());
        DisplayMessage.render(g);
        if(State.getCurrentState() == GAMESTATE)statusView.render(g);
    }

    public void executePlayerCommand(CommandsEnum pce) {
        player.execute(pce);
    }


    public void playerExamineInventory() {
        player.examinePack();
    } // end playerExamineInventory

    public MapView getMapView() {
        return mapView;
    }

    public static Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        GameState.player = player;
        MobileObjectFactory.makeAsset(MobileObjectEnum.PLAYER, player);

        // initializing NPC'selection
        mobileObjects = MobileObjectFactory.Init(map, player);

        // adding player to hash map
        mobileObjects.put(player, MobileObjectFactory.makeAsset(MobileObjectEnum.PLAYER, player));

        for (MobileObject key : mobileObjects.keySet()) {
            if(key instanceof Pet) {
                player.setPet((Pet) key);
            }
        }

        statusView.setPlayer(player);
        map.setMobileObjects(mobileObjects);

    } // end setPlayer

    public static void setMap(Map map) {
        GameState.map = map;
    }
    public  void setMapView(MapView mv){
        mapView = mv;
    }

    public void setMobileObjects(HashMap<MobileObject, MobileObjectView> mo) {
        mobileObjects = mo;
    }

    public void toggleloading(){
        loading = !loading;
    }


    public void initGameState() {
        camera = new Camera(Settings.GAMEWIDTH,Settings.GAMEHEIGHT,map);
        mapView = MakeMap.makeMapView(map);
        statusView = new StatusView(player);
        map.setMobileObjects(mobileObjects);
        mobileObjects.put(player, MobileObjectFactory.makeAsset(MobileObjectEnum.PLAYER, player));

    }
}

