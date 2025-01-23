package ca.mcmaster.se2aa4.mazerunner;

public class LocateEntryExitPoints {

    public MapPosition startPoint;
    public MapPosition endPoint;

    private MapPosition mazeStart() throws Exception {

        throw new Exception("Maze has no entry points");
    }

    private MapPosition mazeEnd() throws Exception {

        throw new Exception("Maze has no exit points");
    }

}
