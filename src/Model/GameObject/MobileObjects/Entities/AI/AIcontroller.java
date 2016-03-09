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
        private Location destination = new Location(2,3,0);
        public void setAI(MobileObject AI) {this.AI = AI;}
        @Override
        public void tick() {

            if (AI.canMove() && !AI.getLocation().equals(destination)) {

                Location path = Astar.Findpath(map, AI.getLocation(), destination).get(0);
                if(Navigation.checkMove(path, map, AI)) {
                    AI.move(Astar.Findpath(map, AI.getLocation(), destination).get(0).getDir());
                    AI.alert();
                    System.out.println("Moving");
                }
            }
        }

    public void setDestination(Location location) {
        this.destination = location;
    } // end setDestination
} // end class AIcontroller
