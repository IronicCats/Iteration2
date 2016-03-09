package State.States.GameState;

import Controller.Controllers.GameController;
import Model.Abilities.PlayerCommandsEnum;
import Model.GameObject.Item.Items.Takables.Equippable.Weapon;
import Model.GameObject.AreaEffect.AreaEffect;
import Model.GameObject.AreaEffect.AreaEffectEnum;
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
import Utilities.AreaEffectUtilities.AreaEffectFactory;
import Utilities.ItemUtilities.ItemFactory;
import Utilities.ItemUtilities.ItemsEnum;
import Utilities.MapUtilities.*;
import State.State;
import Utilities.MapUtilities.MakeMap;
import Utilities.MobileObjectUtilities.MobileObjectEnum;
import Utilities.MobileObjectUtilities.NPCFactory;
import Utilities.SaveLoad;
import Utilities.Settings;
import View.ViewUtilities.Camera;
import View.ViewUtilities.Graphics.Assets;
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


    private Player player;
    private NPC enemy;
    private NPC enemy1;
    private Pet pet;


    private MobileObjectView playerView;
    private MobileObjectView enemyView;
    private MobileObjectView enemyView1;
    private MobileObjectView petView;

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
        player = new Player();
        player = new Player(new Location(0, 1), new Smasher(), new Inventory());
        player.equip((Weapon) ItemFactory.makeItem(ItemsEnum.SWORDFISH_DAGGER, player.getLocation()));
        playerView = new MobileObjectView(player, Assets.PLAYER);

        enemy = (NPC)NPCFactory.makeNPC(MobileObjectEnum.KITTEN, new Location(0, 0, 0), map);
        enemy1 = (NPC)NPCFactory.makeNPC(MobileObjectEnum.KITTEN, new Location(4, 5, 0), map);
        //pet = new Pet(new PetController(map), new Location(3, 3), new PetStats(new StatStructure(StatsEnum.MOVEMENT, 3)), new Pack(), false);

        enemyView = NPCFactory.makeAsset(MobileObjectEnum.KITTEN, enemy);
        enemyView1 = NPCFactory.makeAsset(MobileObjectEnum.KITTEN, enemy1);

        //petView = new MobileObjectView(pet, Assets.HEALTH_POTION);

        mobileObjects.put(player, playerView);
        mobileObjects.put(enemy, enemyView);
        mobileObjects.put(enemy1, enemyView1);
        // initializing items
        mapItems = ItemFactory.initMainMap();
        MakeMap.populateItems(mapItems.keySet().toArray(new Item [mapItems.size()]), map);


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
        if(cameraMoving){
            System.out.println("camera moving");
            camera.move(degrees);
        }
        else if(Navigation.checkMove(Location.newLocation(degrees, player.getLocation()), map, player) & player.canMove()) { // returns if new location is walkable
            player.move(degrees);
        }



    }

    public void setCameraMoving(boolean movement){
        cameraMoving = movement;
        if(!cameraMoving){
            camera.centerOnPlayer(player);
        }
    }

    public MobileObjectView getMobileObjectView(MobileObject o){
            return mobileObjects.get(o);
    }
    public Camera getCamera() {
        return camera;
    }

    @Override
    public void tick() {
        player.tick();
        enemy.tick();
        enemy1.tick();
    }

    public void render(Graphics g) {
        if(!cameraMoving) {
            camera.centerOnPlayer(player);
        }
        mapView.render(g, camera.getxOffset(), camera.getyOffset(), player.getLocation());
        //keyset for keys, values for values
        //enemyView1.render(g, camera.getxOffset(), camera.getyOffset());

        //playerView.render(g, camera.getxOffset(), camera.getyOffset());
        //enemyView.render(g, camera.getxOffset(), camera.getyOffset());

        DisplayMessage.render(g);
        //petView.render(g, camera.getxOffset(), camera.getyOffset());
    }

    public void executePlayerCommand(PlayerCommandsEnum pce){
        switch(pce){
            case interact:
                player.interactWithTile();
                break;
            case drop:
                player.emptyPack();
                break;
            case attack:
                System.out.println("Attack!");
                break;
            case ability1:
                System.out.println("Ability 1");
                break;
            case ability2:
                System.out.println("Ability 2");
                break;
            case ability3:
                System.out.println("Ability 3");
                break;
        }
    }


    public void playerExamineInventory() {
        player.examinePack();
    } // end playerExamineInventory


    @Override
    public void switchState(State state) {
        setState(state);
    }
}
