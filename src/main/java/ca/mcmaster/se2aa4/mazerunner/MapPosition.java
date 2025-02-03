package ca.mcmaster.se2aa4.mazerunner;

import static ca.mcmaster.se2aa4.mazerunner.DirectionOrientation.Direction.*;

public record MapPosition(int x, int y, DirectionOrientation.Direction direction) {

    public MapPosition addPosition(MapPosition newPosition){
        return new MapPosition(this.x + newPosition.x(), this.y + newPosition.y(), this.direction);
    }

    public MapPosition turnRight() {
      return new MapPosition(x, y, direction.rightTurn());
    }

    public MapPosition turnLeft() {
        return new MapPosition(x, y, direction.leftTurn());
    }
}

