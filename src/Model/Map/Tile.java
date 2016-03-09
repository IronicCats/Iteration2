package Model.Map;

import Model.GameObject.Item.Item;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.GameObject.MobileObjects.Entities.Entity;
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
    private ArrayList<Item> items;
    private AreaEffect areaEffect;
    private MobileObject object;
    private boolean hasAreaEffect;
    private boolean visited;

    public Tile(Location location, boolean IsWalkable){
        items = new ArrayList<>();
        object = null;
        this.location = location;
        this.IsWalkable = IsWalkable;
        this.hasAreaEffect = false;
        visited = false;
        observers = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void setAreaEffectTile(AreaEffect a){
        this.areaEffect = a;
        this.hasAreaEffect = true;
        System.out.println(hasAreaEffect + "MM");
        alert();
    }

    public AreaEffect getAreaEffect(){
        return this.areaEffect;
    }

    public AreaEffectEnum getAreaEffectEnum(){
        return this.areaEffect.getAreaEffect();
    }

    public MobileObject getObject() {
        return object;
    }

    public void setObject(MobileObject object){
        this.object = object;
        if(object instanceof Player){
            visited = true;
        }
    }

    public void leaveTile(){
        this.object = null;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public boolean hasObject() {
        return object != null;
    }

    public boolean hasItems() {
        return items.size() > 0;
    }

    public Location getLocation(){
        return location;
    }

    public boolean getHasAreaEffect(){
        System.out.println("MM" + this.hasAreaEffect);
        return this.hasAreaEffect;
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
