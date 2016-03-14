package Model.GameObject.MobileObjects.Entities.AI;

import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;
import Model.Map.Map;
import Model.Map.Tile;
import Model.Tickable;
import Utilities.AIUtilities.*;
import Utilities.MapUtilities.Navigation;
import Utilities.MapUtilities.RangeofTilesinSight;
import Utilities.MobileObjectUtilities.MobileObjectEnum;
import Utilities.Observer;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by Aidan on 3/6/2016.
 */

    public abstract class AIController implements Tickable, Observer {

        Map map;
        MobileObject target;
        Location oldTargetLocation;
        //TODO: Take in an array of possible targets. When looking at tiles in sight
        //check to see if one of the nearby mobile objects is one of my possible targets
        //then set target
        Random random = new Random(System.currentTimeMillis());
        Location baseLoc;

        public AIController(Map map){
            this.map = map;
        }

    protected MobileObject AI;

    private Location destination = new Location(0, 2, 0);

    public void setAI(MobileObject AI) {
        this.AI = AI;
    }

    @Override
    public void tick() {
        if (target != null) {
            //follow(mobileObject);
            //goToObjInView();
        }
        else {
            //randomlyMoveinRange();
            //moveTo(destination);
        }
    }

    //Moves to destination
    public void moveTo(Location destination) {
        if (AI.canMove() && !AI.getLocation().equals(destination)) {
            Location start = Astar.Findpath(map, AI.getLocation(), destination).get(0); //Tile currently on
            Location end = Astar.Findpath(map, AI.getLocation(), destination).get(1);  //Tile that AI wants to go to
            if (Navigation.checkMove(end, map, AI)) {   //Check you can move to the tile you want to go to
                AI.move(start.getDir());     //Get the direction of your tile and move accordingly
                AI.alert();
            }
        }
    }

    public void setMap(Map map) {
        this.map = map;
    }

    //Moves to location of a mobileobject
    public void follow() {
        System.out.println("following");
        if (oldTargetLocation == null || AI.getLocation().equals(oldTargetLocation)) {
            oldTargetLocation = target.getLocation();
        }
        moveTo(oldTargetLocation);
    }

    //Waits for a particular mobileobject to be in sight and when in sight, follows that mobileobject
    public void goToObjInView() {
        if (targetinView()) {
            follow();
        }
    }

    public ArrayList<Tile> getTilesinView() {
        return FindTilesAround.find(AI.getLocation(), map, AI.getView(), AI.getViewLocation());
    }

    public ArrayList<Tile> getTilesinSight() {
        return FindTilesInSight.find(getTilesinView(), AI.getLocation(), AI.getView());
    }

    public void randomlyMoveinRange() {
        int temp = random.nextInt(30);
        if (temp == 1) { // arbitrary number; 60 ticks/second means one movement per 10 seconds on average
            Location randomLoc = RandomLocation.computeRandomLocation(baseLoc, AI.getRange());
            if (map.getTile(randomLoc.getX(), randomLoc.getY()) != null) {
                moveTo(randomLoc);
            }
        }
    }

    public void tryToPickUpRandomly(int percentPickup) {
        int temp = random.nextInt(30);
        if (temp == 1) {
            if (map.getTile(AI.getLocation().getX(), AI.getLocation().getY()).hasItems()) {
                //AI.pickupItem
            }
        }
    }

    public boolean targetinFront() {
        Location targetTile = Location.newLocation(AI.getLocation().getDir(), AI.getLocation());
        if (map.getTile(targetTile).getObject() == target) {
            return true;
        }
        return false;
    }

    public boolean targetinView() {

        return FindTargetinTiles.find(getTilesinView(), target);

    }

    public boolean targetinSight() {

        return FindTargetinTiles.find(getTilesinSight(), target);

    }

    public void followReturnToBaseWhenOutofRange() {
        if (targetinView()) {
            follow();
        }
        returntoBase();
    }

    public void returntoBase() {
        moveTo(baseLoc);
    }

    public void runawayWheNearDeath(int runawayRange) {
        //if(nearDeath)
        int temp = random.nextInt(30);
        if (temp == 1) { // arbitrary number; 60 ticks/second means one movement per 10 seconds on average
            Location randomLoc = RandomLocation.computeRandomLocation(baseLoc, runawayRange);
            if (map.getTile(randomLoc.getX(), randomLoc.getY()) != null) {
                moveTo(randomLoc);
            }
        }
    }

    public boolean canFace(){
        return CanFace.find(AI,target,map);
    }

    public void setDestination(Location location) {
        this.destination = location;
    } // end setDestination

    public void setTarget(MobileObject mobileObject) {
        this.target = mobileObject; }

    public Location getBaseLoc() {
        return baseLoc;
    }

    public void setBaseLoc(Location baseLoc) {
        this.baseLoc = baseLoc;
    }

    @Override
    public void update() {
        AI.getStats();
        //if health of AI is below certain level then runawaynearDeath
    }

    @Override
    public void remove() {
    }
} // end class AIController
