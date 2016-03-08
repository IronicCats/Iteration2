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

        Map map;

        public AIcontroller(Map map){
            this.map = map;
        }

        private MobileObject AI;
        private Location destination = new Location(2,5,0);
        public void setAI(MobileObject AI) {this.AI = AI;}
        @Override
        public void tick() {
            System.out.println(destination);
            System.out.println(AI.getLocation());
            if (AI.canMove() && !AI.getLocation().equals(destination)) {
                AI.move(Astar.Findpath(map, AI.getLocation(), destination).get(0).getDir());
                AI.alert();
                System.out.println("Moving");
            }
        }

}
