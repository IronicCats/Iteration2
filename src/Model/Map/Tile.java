package Model.Map;


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
    public boolean IsWalkable;
    protected Observer observer;
    private boolean isWalkable;
    private ArrayList<Item> items;
    private AreaEffect areaEffect;
    private MobileObject object;
    private boolean hasObject;
    private boolean hasAreaEffect;
    private boolean visited;
    private AreaEffectEnum ar;

    public Tile(Location location, boolean isWalkable){
        items = new ArrayList<>();
        hasObject = false;
        this.location = location;
        this.IsWalkable = IsWalkable;
        visited = false;
        observers = new ArrayList<>();
        hasAreaEffect = false;
    }


    public void interact() {
        if (hasItems() && object instanceof Player) {
            items = ((Player) object).takeItems(items);
        }
    }

    public void addItem(Item item) {
        items.add(item);
    }

    //set the tiles area effect
    public void setAreaEffect(AreaEffect a){
        this.areaEffect = a;
        this.ar = a.getAreaEffect();
        hasAreaEffect = true;
        alert();
    }

    public AreaEffect getAreaEffect(){
        return this.areaEffect;
    }

    public AreaEffectEnum getAreaEffectEnum(){
        return this.ar;
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
        return isWalkable;
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

    //access if the current tile has an area effect or not
    public boolean getHasAreaEffect(){
        return hasAreaEffect;
    }


    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {

    }

    @Override
    public void alert() {
        for(Observer o: observers) {
            o.update();
        }
    }

}
