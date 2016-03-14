package Model.Map;


import Model.Abilities.Abilities;
import Model.GameObject.AreaEffect.AreaEffect;
import Model.GameObject.AreaEffect.AreaEffectEnum;
import Model.GameObject.AreaEffect.TeleportAreaEffect;
import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Interactable;
import Model.GameObject.Item.Items.Obstacle;
import Model.GameObject.MobileObjects.Entities.Characters.Character;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.GameObject.MobileObjects.MobileObject;
import Model.GameObject.MobileObjects.Projectile;
import Model.GameObject.MobileObjects.Vehicle;
import Model.Location;
import State.State;
import State.States.GameState.GameState;
import Utilities.ItemUtilities.ItemsEnum;
import Utilities.Observer;
import Utilities.Subject;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Aidan on 3/1/2016.
 * <p>
 * TODO:
 * interact() needs to have different implementations based on instanceof
 * alternately, replace player.takeItems(...) to reflect different interactions
 */

public abstract class Tile implements Subject {

    private Location location;
    protected ArrayList<Observer> observers;
    public boolean isWalkable;
    protected Observer observer;
    private ArrayList<Item> items;
    private AreaEffect areaEffect;
    private MobileObject object;
    private boolean hasObject;
    private boolean hasAreaEffect;
    private boolean visited;
    private boolean hasTeleportAreaEffect;
    private TeleportAreaEffect teleportAreaEffect;

    private AreaEffectEnum ar;

    public Tile(Location location, boolean isWalkable) {
        items = new ArrayList<>();
        hasObject = false;
        this.location = location;
        this.isWalkable = isWalkable;
        visited = false;
        observers = new ArrayList<>();
        hasAreaEffect = false;
        hasTeleportAreaEffect = false;
    }


    public void interact() {
        if (hasItems()) {
            items = ((Character) object).takeItems(items);
            alert();
        }
        System.out.println("Interacting with tile");
        Map.map.carryInteraction(object);
    }

    public void receiveInteraction(MobileObject interacter) {
        if (hasObject()) {
            object.interact(interacter);
        }
        for(Item item: items){
            if(item instanceof Interactable && interacter instanceof Player){
                // enum for changed asset should be right after original enum (yes I know)
                if(!((Interactable) item).getState() && ((Interactable) item).getRequirements().hasRequiredItem(((Player) interacter).getPack())){
                    ((Interactable) item).toggleState();
                    alert();
                }
            }
        }
    }

    public void sendAttack(Character character, Abilities ability) {
        Map.map.carryAttack(character, ability);
    }

    public void receiveAttack(Character c, Abilities a) {
        if (hasObject() && !(object instanceof Vehicle)) {
            ((Character) object).receiveAttack(c, a);
        }
    }

    public void receiveProjectileAttack(Projectile p) {

        ((Character) object).receiveProjectileAttack(p.getEffect());

    }


    public void addItem(Item item) {
        items.add(item);
        alert();
    }

    public void addItems(ArrayList<Item> items) {
        for (Item i : items) {
            if (i != null) {
                this.items.add(i);
            }
        }
        alert();
    }


    public void setAreaEffectTile(AreaEffect a) {
        this.areaEffect = a;
        this.ar = a.getAreaEffect();
        hasAreaEffect = true;
        alert();
    }

    public void setTeleportAreaEffectTile(TeleportAreaEffect t) {
        this.teleportAreaEffect = t;
        hasTeleportAreaEffect = true;
        alert();
    }

    public boolean getHasTeleportAreaEffect(){
        return this.hasTeleportAreaEffect;
    }

    public TeleportAreaEffect getTeleportAreaEffect(){
        return this.teleportAreaEffect;
    }

    public AreaEffect getAreaEffect() {
        return this.areaEffect;
    }

    public void removeAreaEffect(){
        this.areaEffect = null;
        this.hasAreaEffect = false;
        this.ar = null;
    }

    public AreaEffectEnum getAreaEffectEnum() {
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
        if (object instanceof Player) {
            visited = true;
        }
        if(object instanceof Character){
            if (this.getHasAreaEffect()) {
                ((Character) object).applyEffect(areaEffect.getEffect());
            }
            if(this.getHasTeleportAreaEffect()){
                teleportAreaEffect.teleportPlayer(((Character) object));
            }
        }
        alert();
        return this;
    }

    public void deregister() {
        object = null;
        hasObject = false;
        alert();
    }


    public boolean isWalkable() {
        for (Item i : items) {
            if (i instanceof Obstacle || i instanceof Interactable && (!((Interactable) i).getState())) {
                return false;
            }
        }
        return isWalkable && !hasObject;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public boolean hasItems() {
        return items.size() > 0;
    }

    public Location getLocation() {
        return location;
    }

    //access if the current tile has an area effect or not
    public boolean getHasAreaEffect() {
        return hasAreaEffect;
    }

    public int amountOfItems() {
        return items.size();
    }

    public boolean isVisited() {
        return visited;
    }

    public void setIsVisited() {
        visited = true;
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
        for (Observer o : observers) {
            o.update();
        }
    }

}
