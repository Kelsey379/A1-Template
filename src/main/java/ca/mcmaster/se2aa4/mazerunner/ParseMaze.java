package ca.mcmaster.se2aa4.mazerunner;

import java.util.HashSet;
import java.util.Set;

public class ParseMaze {

    static int x;
    static int y;
    private Maze maze;
    private SolveMaze solve;
    static DirectionOrientation.Direction currentDirection;
    StringBuilder pathTried;
    private static Set<String> visitedPosition;

    public ParseMaze(Maze maze, int startXPos, int startYPos, DirectionOrientation.Direction startDirection, SolveMaze solve){
        this.x = startXPos;
        this.y = startYPos;
        this.currentDirection = startDirection;
        this.maze = maze;
        this.solve = solve;
        this.pathTried = new StringBuilder();
        this.visitedPosition = new HashSet<>();
    }

    public void executeCommand(Command command){
        command.execute(this);
    }

    public DirectionOrientation.Direction getCurrentDirection () {
        return currentDirection;
    }

    //will calculate the next position based on the direction faced
    public static MapPosition findNextPosition() {
        return switch (currentDirection) {
            case NORTH -> new MapPosition(x-1, y, currentDirection);
            case SOUTH -> new MapPosition(x + 1, y, currentDirection);
            case EAST -> new MapPosition(x, y+1, currentDirection);
            case WEST -> new MapPosition(x, y-1, currentDirection);
        };
    }

    public StringBuilder solveMaze() throws Exception{
        if (maze.emptyExists()){
            String pathForwards = maze.checkEmptyRow();
            if (!pathForwards.isEmpty()) {
                pathTried.append(pathForwards);
                return pathTried;
            }
        }
        MapPosition startPos = Maze.getStartPosition();
        this.x = startPos.x();
        this.y = startPos.y();
        this.currentDirection = startPos.direction();

        solve.solveGivenMaze(this, maze);
        return pathTried;
    }

    static void markAsVisited(){
        visitedPosition.add(x + "," + y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getPath() {
        return pathTried.toString();
    }

}
