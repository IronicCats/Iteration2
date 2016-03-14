package State.States.GameState;

import Controller.Controllers.GameController;
import Model.Abilities.CommandsEnum;
import Model.GameObject.AreaEffect.AreaEffect;
import Model.GameObject.AreaEffect.AreaEffectEnum;
import Model.GameObject.AreaEffect.TeleportAreaEffect;
import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Takables.Equippable.Weapon;
import Model.GameObject.MobileObjects.Entities.Characters.Character;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.GameObject.MobileObjects.Entities.Pet;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;
import Model.Map.Map;
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
import View.Views.DecalView;
import View.Views.ItemView;
import View.Views.MapView;
import View.Views.MessageBox.DisplayMessage;
import View.Views.MobileObjectView;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;


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

    private boolean cameraMoving;

    private static Player player;
    private boolean pause;


    public GameState() {
        //need to change this
        pause = true;
        cameraMoving = false;
        mapItems = new HashMap<>();
        decals = new HashMap<>();
        mobileObjects = new HashMap<>();

        map = MakeMap.makeMap();
        mapView = MakeMap.makeMapView(map);

        setController(new GameController(this));

        camera = new Camera(Settings.GAMEWIDTH, Settings.GAMEHEIGHT, map);

        //creating a new player
        player = MobileObjectFactory.Player();
        player.setInitialLevel(5);
        MobileObjectFactory.makeAsset(MobileObjectEnum.PLAYER, player);

        // initializing items
        mapItems = ItemFactory.initMainMap();
        MakeMap.populateItems(mapItems.keySet().toArray(new Item[mapItems.size()]), map);

        //creating a new player
        player = MobileObjectFactory.Player();

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
        AreaEffect a = AreaEffectFactory.makeAreaEffect(AreaEffectEnum.LEVELUP, new Location(3, 2));
        AreaEffect b = AreaEffectFactory.makeAreaEffect(AreaEffectEnum.HEAL, new Location(6, 4));
        AreaEffect c = AreaEffectFactory.makeAreaEffect(AreaEffectEnum.DAMAGE, new Location(4, 4));
        AreaEffect d = AreaEffectFactory.makeAreaEffect(AreaEffectEnum.DEATH, new Location(10, 2));
        TeleportAreaEffect e = AreaEffectFactory.makeTeleportAreaEffect(new Location(8,4), new Location(3,3));
        AreaEffect f = AreaEffectFactory.makeAreaEffect(AreaEffectEnum.TRAP, new Location(2, 5));
        map.placeAreaEffect(a);
        map.placeAreaEffect(b);
        map.placeAreaEffect(c);
        map.placeAreaEffect(d);
        map.placeTeleportAreaEffectBeginning(e);
        map.placeAreaEffect(f);

        map.setMapItems(mapItems);

    }

    public GameState(Player p,Map m, MapView mv,HashMap<MobileObject, MobileObjectView> mo,HashMap<AreaEffect, DecalView> d,HashMap<Item, ItemView> mi){
        pause = true;
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
    }

    public void switchState() {

    }

    public void move(int degrees) {
        //If camera is moving then movement will be applied to camera, otherwise apply it to the player
        System.out.println("x:" +Integer.toString(player.getLocation().getX()));
        System.out.println("y:" +Integer.toString(player.getLocation().getY()));
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
        for (MobileObject key : mobileObjects.keySet()) {
            key.tick();
        }
        RespawnQueue.tick();
    }

    public void render(Graphics g) {
        if(!cameraMoving){
            camera.centerOnObject(player);
        }
        mapView.render(g, camera.getxOffset(), camera.getyOffset(), player.getLocation());
        DisplayMessage.render(g);
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

    public static void setPlayer(Player player) {
        GameState.player = player;
    }

    public static void setMap(Map map) {
        GameState.map = map;
    }
    public  void setMapView(MapView mv){
        mapView = mv;
    }

    public void setMobileObjects(HashMap<MobileObject, MobileObjectView> mo) {
        mobileObjects = mo;
    }

    public void togglePause(){
        pause = !pause;
    }
}
