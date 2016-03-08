package Model.GameObject.MobileObjects.Entities.AI;

import Model.GameObject.MobileObjects.MobileObject;
import Model.Location;
import Model.Map.Map;
import Model.Map.Tile;
import Model.Tickable;
import Utilities.AIUtilities.Astar;

import java.util.ArrayList;

/**
 * Created by Aidan on 3/6/2016.
 */
    public abstract class AIcontroller implements Tickable {

        private Astar astar;
        private MobileObject AI;
        private Location Destination = new Location(2,5,0);

        public AIcontroller(Astar astar){
            this.astar = astar;
        }

        @Override
        public void tick() {
            if (AI.canMove() && !AI.getLocation().equals(Destination)) {
                AI.move(astar.Findpath(AI.getLocation(), Destination).get(0).getDir());
            }
        }

}
