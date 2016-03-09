package State.States.GameState;

import Controller.Controllers.GameController;
import Model.GameObject.Item.Items.Takables.Equippable.Weapon;
import Model.GameObject.AreaEffect.AreaEffect;
import Model.GameObject.AreaEffect.AreaEffectEnum;
import Model.GameObject.Decal.Decal;
import Model.GameObject.Decal.DecalEnum;
import Model.GameObject.MobileObjects.Entities.AI.NPCController;
import Model.GameObject.MobileObjects.Entities.AI.PetController;
import Model.GameObject.MobileObjects.Entities.Characters.NPC;
import Model.GameObject.MobileObjects.Entities.Characters.Occupation.Smasher;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.GameObject.MobileObjects.Entities.Pet;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Inventory.Inventory;
import Model.Inventory.Pack;
import Model.Location;
import Model.Map.Map;
import Model.GameObject.Item.Item;
import Model.Stats.PetStats;
import Model.Stats.StatStructure;
import Model.Stats.StatsEnum;
import State.StatesEnum;
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
    private NPC enemy;
    private Pet pet;

    private MobileObjectView playerView;
    private MobileObjectView enemyView;
    private MobileObjectView petView;

    public GameState() {
        setController(new GameController(this));
        mapItems = new HashMap<>();
        mobileObjects = new HashMap<>();
        decals = new HashMap<>();
        map = MakeMap.makeMap();
        camera = new Camera(Settings.GAMEWIDTH, Settings.GAMEHEIGHT,map);
        mapView = MakeMap.makeMapView(map);

        // initializing items
        mapItems = ItemFactory.initMainMap();
        MakeMap.populateItems(mapItems.keySet().toArray(new Item [mapItems.size()]), map);

        //creating a new player
        player = new Player();
        player = new Player(new Location(0, 2), new Smasher(), new Inventory());
        player.equip((Weapon) ItemFactory.makeItem(ItemsEnum.SWORDFISH_DAGGER, player.getLocation()));
        enemy = new NPC(new Location(5,5,0), new Smasher(), new Inventory(),new NPCController(map));
        pet = new Pet(new PetController(map), new Location(3, 3), new PetStats(new StatStructure(StatsEnum.MOVEMENT, 3)), new Pack(), false);
        System.out.println(enemy);
        playerView = new MobileObjectView(player, Assets.PLAYER);
        enemyView = new MobileObjectView(enemy, Assets.PLAYER);
        petView = new MobileObjectView(pet, Assets.PLAYER);


        
        //area effect
        AreaEffect a = AreaEffectFactory.makeAreaEffect(AreaEffectEnum.LEVELUP, new Location(1,1));
        decals.put(a, AreaEffectFactory.makeAsset(new Decal(new Location(1,1),DecalEnum.GOLDSTAR)));
        map.placeAreaEffect(a);

        InventoryState inventoryState = new InventoryState(this);//adding the inv state
        State.addState(StatesEnum.InventoryState, inventoryState);

        EquipmentState equipementState = new EquipmentState(this);//adding the equipment state
        State.addState(StatesEnum.EquipmentState, equipementState);

       //This is code to check Astar
        /*Astar astar = new Astar(map);
        ArrayList<Location> path = astar.Findpath(new Location(0,0),new Location(5,5));
        for(int i = 0; i < path.size(); i++){
            System.out.println("xLoc " + path.get(i).getX());
            System.out.println("yLoc " + path.get(i).getY());
            System.out.println("dir " + path.get(i).getDir());
        }*/
    }

    public void switchState() {

    }

    public void movePlayer(int degrees) {
        if(Navigation.checkMove(Location.newLocation(degrees, player.getLocation()), map, player) & player.canMove()) { // returns if new location is walkable
            map.deRegister(player.getLocation()); // removes player from tile
            player.move(degrees);
            map.registerObject(player); // registers player with tile
        }
    }


    public void render(Graphics g) {
        mapView.render(g, camera.getxOffset(), camera.getyOffset(), player.getLocation());
        //keyset for keys, values for values
        for (ItemView itemView : mapItems.values()) {
            itemView.render(g, camera.getxOffset(), camera.getyOffset());
        }
        for (DecalView decalView : decals.values()) {
            decalView.render(g, camera.getxOffset(), camera.getyOffset());
        }
        camera.centerOnPlayer(player);
        playerView.render(g, camera.getxOffset(), camera.getyOffset());
        enemyView.render(g, camera.getxOffset(), camera.getyOffset());
        petView.render(g, camera.getxOffset(), camera.getyOffset());
    }


    @Override
    public void tick() {
        enemy.tick();
        pet.tick();
    }

    @Override
    public void switchState(StatesEnum state) {
        System.out.println(state);
        setState(state);
    }
}
