package ca.mcmaster.se2aa4.mazerunner;

public record MapPosition(int x, int y) {

    public MapPosition move(DirectionOrientation direction){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public MapPosition addPosition(MapPosition newPosition){
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

