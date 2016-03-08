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
        //Need way to do this without importing map
        private Map map;

        public AIcontroller(Astar astar){
            this.astar = astar;
        }

        @Override
        public void tick() {
            //Ai will have a tile that it is currently on and a destination
            //This is used until you can access tile from location without having to go thgrough map first
            Location Destination = AI.getDestination();
            Tile DestinationTile = map.getTile(Destination.getX(),Destination.getY());
            Location CurrentPosition = AI.getLocation();
            Tile CurrentTile = map.getTile(CurrentPosition.getX(),CurrentPosition.getY());
            if(!CurrentPosition.equals(Destination)) {
                ArrayList<Tile> path = astar.Findpath(CurrentTile, DestinationTile);
                for (int i = 0; i < path.size(); i++) {
                    AI.move(path.get(i).getLocation().getDir());
                }
            }
        }

}
