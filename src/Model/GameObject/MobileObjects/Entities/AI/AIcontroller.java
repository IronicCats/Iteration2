package Model.GameObject.MobileObjects.Entities.AI;

import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;
import Model.Map.Map;
import Model.Map.Tile;
import Model.Tickable;
import Utilities.AIUtilities.Astar;
import Utilities.AIUtilities.FindTilesinRange;
import Utilities.MapUtilities.Navigation;
import Utilities.MapUtilities.Neighbors;
import Utilities.Settings;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Aidan on 3/6/2016.
 */
    public abstract class AIcontroller implements Tickable {

        Map map;
        MobileObject mobileObject;

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
            goToObjInSight(mobileObject,3);
        }
        else{
            moveTo(destination);
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

    public void goToObjInSight(MobileObject mobileObject, int sight) {

        ArrayList<Tile> range = FindTilesinRange.find(AI,map,sight);

        for(Tile tile : range){
            if(tile.getObject() == mobileObject){
                follow(mobileObject);
                break;
            }
        }

    }

    public void setDestination(Location location) {
        this.destination = location;
    } // end setDestination

    public void setMobileObject(MobileObject mobileObject) { this.mobileObject = mobileObject; }

} // end class AIcontroller
