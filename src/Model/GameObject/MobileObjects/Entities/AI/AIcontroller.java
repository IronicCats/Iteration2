package Model.GameObject.MobileObjects.Entities.AI;

import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;
import Model.Map.Map;
import Model.Map.Tile;
import Model.Tickable;
import Utilities.AIUtilities.Astar;
import Utilities.AIUtilities.FindTilesinRange;
import Utilities.AIUtilities.RandomLocation;
import Utilities.MapUtilities.Navigation;
import Utilities.MobileObjectUtilities.MobileObjectEnum;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by Aidan on 3/6/2016.
 */
    public abstract class AIcontroller implements Tickable {

        Map map;
        MobileObject target;
        Location oldTargetLocation;
        //TODO: Take in an array of possible targets. When looking at tiles in sight
        //check to see if one of the nearby mobile objects is one of my possible targets
        //then set target
        MobileObjectEnum[] possibleTargets;
        Random random = new Random(System.currentTimeMillis());
        Location baseLoc;

        public AIcontroller(Map map){
            this.map = map;
        }

        private MobileObject AI;
        private Location destination = new Location(0,2,0);
        public void setAI(MobileObject AI) {this.AI = AI;}

    @Override
    public void tick() {
        if(target != null) {
            //follow(mobileObject);
            goToObjInSight();
        }
        else{
            randomlyMoveinRange();
            //moveTo(destination);
        }
    }

    //Moves to destination
    public void moveTo(Location destination){
        if (AI.canMove() && !AI.getLocation().equals(destination)) {
            Location start = Astar.Findpath(map, AI.getLocation(), destination).get(0); //Tile currently on
            Location end  = Astar.Findpath(map, AI.getLocation(), destination).get(1);  //Tile that AI wants to go to
            if(Navigation.checkMove(end, map, AI)) {   //Check you can move to the tile you want to go to
                    AI.move(start.getDir());     //Get the direction of your tile and move accordingly
                    AI.alert();
            }
        }
    }

    public void setMap(Map map) {
        this.map = map;
    }

    //Moves to location of a mobileobject
    public void follow(){
        if(oldTargetLocation == null || AI.getLocation().equals(oldTargetLocation)){
            oldTargetLocation = target.getLocation();
        }
        moveTo(oldTargetLocation);
    }

    //Waits for a particular mobileobject to be in sight and when in sight, follows that mobileobject
    public void goToObjInSight() {
        if(targetinSight()){
            follow();
        }
    }

    public ArrayList<Tile> getTilesinSight(){
        return FindTilesinRange.find(AI.getLocation(), map, AI.getSight(), AI.getViewLocation());
    }

    public void  randomlyMoveinRange(){
        int temp = random.nextInt(30);
        if(temp == 1) { // arbitrary number; 60 ticks/second means one movement per 10 seconds on average
            Location randomLoc = RandomLocation.computeRandomLocation(baseLoc,AI.getRange());
            if(map.getTile(randomLoc.getX(),randomLoc.getY()) != null) {
                moveTo(randomLoc);
            }
        }
    }

    public boolean targetinFront() {
        Location targetTile = Location.newLocation(AI.getLocation().getDir(),AI.getLocation());
        if(map.getTile(targetTile).getObject() == target){
            return true;
        }
        return false;
    }

    public boolean targetinSight(){
        ArrayList<Tile> range = getTilesinSight();

        for (Tile tile : range) {
            if (tile.getObject() == target) {
                return true;
            }
        }
        return false;
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


} // end class AIcontroller