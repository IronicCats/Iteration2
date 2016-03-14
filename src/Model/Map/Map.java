package Model.Map;

import Model.Abilities.*;
import Model.GameObject.AreaEffect.AreaEffect;
import Model.GameObject.AreaEffect.TeleportAreaEffect;
import Model.GameObject.Item.Item;
import Model.GameObject.MobileObjects.Entities.Characters.Character;
import Model.GameObject.MobileObjects.Entities.Characters.HostileNPC;
import Model.GameObject.MobileObjects.MobileObject;
import Model.GameObject.MobileObjects.Projectile;
import Model.Location;
import Utilities.AIUtilities.FindTilesAround;
import Utilities.MapUtilities.Neighbors;
import Utilities.MobileObjectUtilities.MobileObjectEnum;
import Utilities.MobileObjectUtilities.MobileObjectFactory;
import Utilities.Observer;
import Utilities.Settings;
import Utilities.Subject;
import View.Views.ItemView;
import View.Views.MessageBox.DisplayMessage;
import View.Views.MessageBox.GameMessage;
import View.Views.MobileObjectView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Aidan on 3/1/2016.
 */
public class Map implements Subject {

    public static Map map;
    private Tile tiles[][];
    private int width;
    private int height;
    private Location spawn;
    protected Observer observer;

    private HashMap<MobileObject, MobileObjectView> mobileObjects;
    private HashMap<Item, ItemView> mapItems;


    public Map(Tile tiles[][], int width, int height, Location spawn) {
        this.tiles = tiles;
        this.width = width;
        this.height = height;
        this.spawn = spawn;
        map = this;
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x > Settings.MAPWIDTH - 1 || y > Settings.MAPHEIGHT - 1) {
            return null;
        }
        return tiles[x][y];
    }

    public Tile getTile(Location location) {
        int x = location.getX();
        int y = location.getY();
        if (x < 0 || y < 0 || x > Settings.MAPWIDTH - 1 || y > Settings.MAPHEIGHT - 1) {
            return null;
        }
        return tiles[x][y];
    }

    // notifies tile that Mobile object is on it
    public Tile register(MobileObject object) {
        return tiles[object.getX()][object.getY()].register(object);
    }

    public void deRegister(Location location) {
        tiles[location.getX()][location.getY()].deregister();
    }

    public void placeItem(Item item) {
        try {
            tiles[item.getX()][item.getY()].addItem(item);
            tiles[item.getX()][item.getY()].alert();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void placeAreaEffect(AreaEffect a) {
        try {
            tiles[a.getX()][a.getY()].setAreaEffectTile(a);
            tiles[a.getX()][a.getY()].alert();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void placeTeleportAreaEffectBeginning(TeleportAreaEffect t){
        try {
            tiles[t.getX()][t.getY()].setTeleportAreaEffectTile(t);
            tiles[t.getX()][t.getY()].alert();
            this.placeTeleportAreaEffectEnding(new TeleportAreaEffect(t.getEndLocation(), t.getLocation())); //place other portal
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void placeTeleportAreaEffectEnding(TeleportAreaEffect t){
        try {
            tiles[t.getX()][t.getY()].setTeleportAreaEffectTile(t);
            tiles[t.getX()][t.getY()].alert();
            //not calling the other method because that has already been placed
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void carryAttack(Character c, Abilities a) {
        if (a.getManaCost() > c.getStats().getMana()) {
            System.out.println("You can't afford to use this ability");
        } else {
            if (a instanceof DirectAbility) { //when using direct ability
                getTile(Location.newLocation(c.getDir(), c.getLocation())).receiveAttack(c, a);
            } else if (a instanceof SelfAbility) {  //using a self ability
                System.out.println("This is a self ability");
                getTile(c.getLocation()).receiveAttack(c, a);
            } else if (a instanceof ProjectileAbility) { //using a projectile ability

                System.out.println("Projectile Ability Set");
                Tile infront = getTile(Location.newLocation(c.getDir(), c.getLocation()));
                if (infront.hasObject()) {
                    infront.receiveAttack(c, a);
                } else {
                    Projectile p = MobileObjectFactory.Hairball(Location.newLocation(c.getDir(), c.getLocation()), a.getEffects());
                    ((ProjectileAbility) a).setProjectile(p);
                    mobileObjects.put(p, MobileObjectFactory.makeAsset(MobileObjectEnum.HAIRBALL, p));
                    ((ProjectileAbility) a).getProjectile().execute();
                }
            }
            else if (a instanceof EnchantmentAbility) {
                System.out.println("using enchantment ability");
                ArrayList<Tile> range = FindTilesAround.find(c.getLocation(), this, a.getRange(), c.getViewLocation());
                for (Tile tile : range) {
                    if (tile.getObject() instanceof HostileNPC) {
                        ((HostileNPC) tile.getObject()).makeSleep();
                    }
                }
            }
            else if (a instanceof AOEAbility) { //using an area of effect ability
                System.out.println("AOEAbility");
                if (((AOEAbility) (a)).getDegreeMovement() == 60) {
                    Tile[] t = Neighbors.neighborsAtSixtyDegrees(c.getTile(), this, c.getDir());
                    for (int i = 0; i < t.length; i++) {
                        t[i].receiveAttack(c, a);
                    }
                } else if (((AOEAbility) (a)).getDegreeMovement() == 360) {
                    Tile[] t = Neighbors.neighbors(c.getTile(), this);
                    for (int i = 0; i < t.length; i++) {
                        t[i].receiveAttack(c, a);
                    }
                }

            }

            else if (a instanceof CreepAbility){
                System.out.println("activating creep ability");
                c.getStats().setMovement(c.getMovement()-8);
                for(MobileObject key: mobileObjects.keySet()){
                    if(key instanceof HostileNPC){
                        ((HostileNPC) key).takeAwayTarget();
                    }
                }
            }

            else if(a instanceof RemoveTrap){
                System.out.println("Remove Trap");
                if(a.getSkillLevel() == 2)
                {
                    getTile(Location.newLocation(c.getDir(), c.getLocation())).removeAreaEffect();
                }
            }
            else {
                System.out.println("Not a Direct Ability");
                c.applyEffect(a.getCost());
            }
        }
    }

    public void carryInteraction(MobileObject mo) {
        Location destination = Location.newLocation(mo.getDir(), mo.getLocation());
        if (isInMap(destination)) {
            System.out.println("Tile within map");
            getTile(destination).receiveInteraction(mo);
        }
    }

    public Location getSpawn() {
        return spawn;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void addObserver(Observer o) {

    }

    @Override
    public void removeObserver(Observer o) {

    }

    @Override
    public void alert() {
        for (Tile t[] : tiles) {
            for (Tile tile : t) {
                tile.alert();
            }
        }
    }

    public void alert(Tile tile) {
        tile.alert();
    }

    public HashMap<MobileObject, MobileObjectView> getMobileObjects() {
        return mobileObjects;
    }

    public void setMobileObjects(HashMap<MobileObject, MobileObjectView> mobileObjects) {
        this.mobileObjects = mobileObjects;
    }

    public HashMap<Item, ItemView> getMapItems() {
        return mapItems;
    }

    public void setMapItems(HashMap<Item, ItemView> mapItems) {
        this.mapItems = mapItems;
    }

    public boolean isInMap(Location l) {
        if (l.getX() < 0 || l.getX() > map.getWidth() || l.getY() < 0 || l.getY() > map.getHeight()) {
            System.out.println("Trying to interact outside map");
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "Map{" +
                ", width=" + width +
                ", height=" + height +
                ", spawn=" + spawn +
                '}';
    }
}
