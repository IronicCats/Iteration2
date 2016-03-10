package Model.GameObject.MobileObjects.Entities.AI;

import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;
import Model.Map.Map;
import Model.Tickable;
import Utilities.AIUtilities.Astar;
import Utilities.MapUtilities.Navigation;

/**
 * Created by Aidan on 3/6/2016.
 */
    public abstract class AIcontroller implements Tickable {

        Map map;
        MobileObject mobileObject;

        public AIcontroller(){

        }

        private MobileObject AI;
        private Location destination = new Location(0,2,0);
        public void setAI(MobileObject AI) {this.AI = AI;}



    @Override
    public void tick() {
        if(mobileObject != null) {
            follow(mobileObject);
        }
        else{
            //moveTo(destination);
        }
    }

    /*public void moveTo(Location destination){
        if (AI.canMove() && !AI.getLocation().equals(destination)) {
            Location start = Astar.Findpath(map, AI.getLocation(), destination).get(0); //Tile currently on
            Location end  = Astar.Findpath(map, AI.getLocation(), destination).get(1);  //Tile that AI wants to go to
            if(Navigation.checkMove(end, map, AI)) {   //Check you can move to the tile you want to go to
                    AI.move(start.getDir());     //Get the direction of your tile and move accordingly
                    AI.alert();
                    System.out.println("Moving");
            }
        }
    }*/

    public void setMap(Map map) {
        this.map = map;
    }

    public void follow(MobileObject mobileObject){
        //moveTo(mobileObject.getLocation());
    }

    public void setDestination(Location location) {
        this.destination = location;
    } // end setDestination

    public void setMobileObject(MobileObject mobileObject) { this.mobileObject = mobileObject; }

} // end class AIcontroller
