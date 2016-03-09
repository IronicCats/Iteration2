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
        public AIcontroller(Map map){
            this.map = map;
        }

        private MobileObject AI;
        private Location destination = new Location(0,2,0);
        public void setAI(MobileObject AI) {this.AI = AI;}
        @Override
        public void tick() {

            if (AI.canMove() && !AI.getLocation().equals(destination)) {

                Location start = Astar.Findpath(map, AI.getLocation(), destination).get(0);
                Location end  = Astar.Findpath(map, AI.getLocation(), destination).get(1);
                if(Navigation.checkMove(end, map, AI)) {
                    AI.move(start.getDir());
                    AI.alert();
                    System.out.println("Moving");
                }
            }
        }

    public void setDestination(Location location) {
        this.destination = location;
    } // end setDestination
} // end class AIcontroller
