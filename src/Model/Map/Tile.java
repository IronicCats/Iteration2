package Model.Map;


import Model.GameObject.Item.Item;
import Model.GameObject.Item.Items.Obstacle;
import Model.GameObject.MobileObjects.Entities.Characters.Character;
import Model.GameObject.MobileObjects.Entities.Characters.FriendlyNPC;
import Model.GameObject.MobileObjects.Entities.Characters.NPC;
import Model.GameObject.MobileObjects.Entities.Characters.Player;
import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;
import Model.GameObject.AreaEffect.AreaEffect;
import Model.GameObject.AreaEffect.AreaEffectEnum;
import State.State;
import State.States.GameState.TradeState;
import Utilities.Observer;
import Utilities.Subject;

import java.util.ArrayList;

/**
 * Created by Aidan on 3/1/2016.
 *
 * TODO:
 *  interact() needs to have different implementations based on instanceof
 *      alternately, replace player.takeItems(...) to reflect different interactions
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
    private AreaEffectEnum ar;

    public Tile(Location location, boolean isWalkable){
        items = new ArrayList<>();
        hasObject = false;
        this.location = location;
        this.isWalkable = isWalkable;
        visited = false;
        observers = new ArrayList<>();
        hasAreaEffect = false;
    }


    public void interact(Location playersLocation) {
        /*
        look at this beautiful anticohesion!  I'll fix it later
         */
        Tile tempTile = Map.map.getTile(Location.newLocation(playersLocation.getDir(), this.location));     /* get tile in front of player */
        MobileObject tempObject = tempTile.getObject();
        if(tempObject instanceof FriendlyNPC) {      /* check for Shopkeeper at that tile */

            TradeState tradeState = new TradeState(State.GAMESTATE, ((Player) object).getPack(), ((NPC) tempTile.getObject()).getPack());
            State.setState(tradeState);
        }
        if (hasItems()) {
            items = ((Character) object).takeItems(items);
            System.out.print("Telling player to take items");
            alert();
        }
    }

    public void recieveAttack(Character character) {
        Location location = Location.newLocation(character.getDir(), this.location);
        Map.map.carryAttack(character, location);

    }
    public void deliverAttack(Character character) {
        Location location = Location.newLocation(character.getDir(), this.location);
        if(hasObject()) {
            ((Character)object).recieveAttack(character);
        }
    }


    public void addItem(Item item) {
        items.add(item);
        alert();
    }

    public void addItems(ArrayList<Item> items) {
        for (Item i: items) {
            this.items.add(i);
        }
        alert();
    }



    public void setAreaEffectTile(AreaEffect a){
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
            if (object instanceof Player) {
                visited = true;
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

    //access if the current tile has an area effect or not
    public boolean getHasAreaEffect(){
        return hasAreaEffect;
    }

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
