package Model.Map;

import Model.GameObject.Decal.Decal;
import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Obstacle;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;
import Model.GameObject.AreaEffect.AreaEffect;
import Model.GameObject.AreaEffect.AreaEffectEnum;
import Utilities.Observer;
import Utilities.Subject;

import java.util.ArrayList;

/**
 * Created by Aidan on 3/1/2016.
 */
public abstract class Tile implements Subject {

    private Location location;
    protected ArrayList<Observer> observers;
    private boolean isWalkable;
    private ArrayList<Item> items;
    private AreaEffect a;
    private AreaEffectEnum areaEffectEnum;
    private MobileObject object;
    private boolean hasObject;
    private Decal decal;
    private boolean hasAreaEffect;
    private boolean visited;

    public Tile(Location location, boolean isWalkable){
        items = new ArrayList<>();
        hasObject = false;
        this.location = location;
        this.isWalkable = isWalkable;
        hasAreaEffect = false;
        visited = false;
        observers = new ArrayList<>();
    }


    public void interact() {
        if (hasItems() && object instanceof Player) {
            items = ((Player) object).takeItems(items);
            alert();
        }
    }

    public void addItem(Item item) {
        items.add(item);
        alert();
    }

    public void setAreaEffectTile(AreaEffect a){
        this.areaEffectEnum = a.getAreaEffect();
        this.a = a;
        hasAreaEffect = true;
        alert();
    }

    public AreaEffectEnum getAreaEffectEnum(){
        return this.areaEffectEnum;
    }

    public Decal getDecal(){
        return this.decal;
    }

    public MobileObject getObject() {
        return object;
    }

    public boolean hasObject() {
        return hasObject;
    }

    public Tile register(MobileObject object) {
        this.object = object;
        hasObject = true;
        System.out.println(object + " Has registered" + " Has items: " + hasItems());
        if(object instanceof Player) {
            visited = true;
        }
        return this;
    }

    public void deregister() {
        object = null;
        hasObject = false;
    }



    public boolean isWalkable() {
        for(Item i: items){
            if(i instanceof Obstacle){
                return false;
            }
        }
        // add && !hasObject()
        return isWalkable && !hasObject;
    }


    public ArrayList<Item> getItems() {
        return items;
    }

    public boolean hasItems() {
        return items.size() > 0;
    }

    public Location getLocation(){
        return location;
    }

    public boolean getHasAreaEffect(){return this.hasAreaEffect;}

    public int amountOfItems() {
        return items.size();
    }

    public boolean isVisited() {
        return visited;
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void alert() {
        for(Observer o: observers) {
            o.update();
        }
    }

}
