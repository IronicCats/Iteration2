package Model.Map;

import Model.GameObject.Decal.Decal;
import Model.GameObject.Item.Item;
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
    protected Observer observer;
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
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void setAreaEffectTile(AreaEffect a){
        this.areaEffectEnum = a.getAreaEffect();
        this.a = a;
        hasAreaEffect = true;
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
        return this;
    }

    public void deregister() {
        object = null;
        hasObject = false;
    }



    public boolean isWalkable() {
        return isWalkable && !hasObject();
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


    @Override
    public void addObserver(Observer o) {

    }

    @Override
    public void removeObserver(Observer o) {

    }

    @Override
    public void alert() {

    }

}
