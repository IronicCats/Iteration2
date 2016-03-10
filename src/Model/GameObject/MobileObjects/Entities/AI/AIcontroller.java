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
import Utilities.MapUtilities.Neighbors;
import Utilities.Settings;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


/**
 * Created by Aidan on 3/6/2016.
 */
    public abstract class AIcontroller implements Tickable {

        Map map;
        MobileObject mobileObject;
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
        if(mobileObject != null) {
            //follow(mobileObject);
            goToObjInSight(mobileObject,2);
        }
        else{
            randomlyMoveinRange();
            //moveTo(destination);
        }

    }

    public void moveTo(Location destination){
        if (AI.canMove() && !AI.getLocation().equals(destination)) {
            Location start = Astar.Findpath(map, AI.getLocation(), destination).get(0); //Tile currently on
            Location end  = Astar.Findpath(map, AI.getLocation(), destination).get(1);  //Tile that AI wants to go to
            if(Navigation.checkMove(end, map, AI)) {   //Check you can move to the tile you want to go to
                    AI.move(start.getDir());     //Get the direction of your tile and move accordingly
                    AI.alert();
                    System.out.println("Moving ");
            }
        }
    }

    public void follow(MobileObject mobileObject){
        moveTo(mobileObject.getLocation());
    }

    public void goToObjInSight(MobileObject target, int sight) {

        ArrayList<Tile> range = FindTilesinRange.find(AI.getLocation(), map, sight); //give AI location not AI

        for (Tile tile : range) {
            if (tile.getObject() == target) {
                follow(target);
                break;
            }
        }
    }

    public void  randomlyMoveinRange(){
        int temp = random.nextInt(30);
        if(temp == 1) { // arbitrary number; 60 ticks/second means one movement per 10 seconds on average
            Location randomLoc = RandomLocation.computeRandomLocation(baseLoc);
            if(map.getTile(randomLoc.getX(),randomLoc.getY()) != null) {
                moveTo(randomLoc);
            }
        }
    }

    public void setDestination(Location location) {
        this.destination = location;
    } // end setDestination

    public void setMobileObject(MobileObject mobileObject) { this.mobileObject = mobileObject; }

    public Location getBaseLoc() {
        return baseLoc;
    }

    public void setBaseLoc(Location baseLoc) {
        this.baseLoc = baseLoc;
    }
} // end class AIcontroller
