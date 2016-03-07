package Model.Map;

import Model.GameObject.Decal.Decal;
import Model.GameObject.Decal.DecalEnum;
import Model.GameObject.Item.Item;
import Model.GameObject.MobileObjects.Entities.Entity;
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
    public boolean IsWalkable;
    private ArrayList<Item> items;
    private AreaEffect a;
    private AreaEffectEnum areaEffectEnum;
    private Entity entity;
    private Decal decal;
    private boolean hasAreaEffect;

    public Tile(Location location, boolean IsWalkable){
        items = new ArrayList<>();
        entity = null;
        this.location = location;
        this.IsWalkable = IsWalkable;
        hasAreaEffect = false;
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

    public Entity getEntity() {
        return entity;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public boolean hasEntity() {
        return entity != null;
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
