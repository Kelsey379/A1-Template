package ca.mcmaster.se2aa4.mazerunner;

public class DirectionOrientation {

    public enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST;


        //these fields are to be filled in for the MVP version of the project
        public Direction rightTurn() {
            if (this == NORTH) return EAST;
            if (this == EAST) return SOUTH;
            if (this == SOUTH) return WEST;
            if (this == WEST) return NORTH;
            else{
                throw new UnsupportedOperationException("Direction Right Turn is not implemented yet");
            }
        }

        public Direction leftTurn() {
            if (this == NORTH) return WEST;
            if (this == EAST) return NORTH;
            if (this == SOUTH) return EAST;
            if (this == WEST) return SOUTH;
            else{
                throw new UnsupportedOperationException("Direction Left Turn is not implemented yet");
            }
        }

    }
}
