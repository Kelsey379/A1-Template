package ca.mcmaster.se2aa4.mazerunner;

import java.util.HashSet;
import java.util.Set;

public class ParseMaze {

    private int x, y;
    private MapPosition position;
    private Maze maze;
    private SolveMaze solve;
    private DirectionOrientation.Direction currentDirection;
    private StringBuilder pathTried;
    private Set<String> visitedPosition;

    public ParseMaze(Maze maze, int startXPos, int startYPos, DirectionOrientation.Direction startDirection, SolveMaze solve){
        this.x = startXPos;
        this.y = startYPos;
        this.currentDirection = startDirection;
        this.maze = maze;
        this.solve = solve;
        this.pathTried = new StringBuilder();
        this.visitedPosition = new HashSet<>();
    }

    public DirectionOrientation.Direction getCurrentDirection () {
        System.out.println("Current Direction is: " + currentDirection);
        return currentDirection;
    }

    public void moveForward() {
        System.out.println(currentDirection + " Coordinates: " + x + ", " + y);
        MapPosition nextPos = findNextPosition();
        System.out.println("Next Position forwards would be: " + nextPos);
//        if (isVisited(nextPos)){
//            System.out.println("Visited position: " + nextPos);
//            return;
//        }
        this.x = nextPos.x();
        this.y = nextPos.y();
        System.out.println('F');
        pathTried.append('F');
        markAsVisited();
    }

    public void turnRight() {
        System.out.println(currentDirection + " Coordinates: " + x + ", " + y);
        MapPosition nextPos = findNextPosition();
        System.out.println("Right turn next Position forwards would be: " + nextPos);
        currentDirection = currentDirection.rightTurn();
        System.out.println("R " + currentDirection);
        pathTried.append('R');
    }

    public void turnLeft() {
        System.out.println(currentDirection + " Coordinates: " + x + ", " + y);
        MapPosition nextPos = findNextPosition();
        System.out.println("Left turn next Position forwards would be: " + nextPos);
        currentDirection = currentDirection.leftTurn();
        System.out.println('L');
        pathTried.append('L');
    }

    //will calculate the next position based on the direction faced
    public MapPosition findNextPosition() {
        return switch (currentDirection) {
            case NORTH -> new MapPosition(x, y - 1, currentDirection);
            case SOUTH -> new MapPosition(x, y + 1, currentDirection);
            case EAST -> new MapPosition(x + 1, y, currentDirection);
            case WEST -> new MapPosition(x - 1, y, currentDirection);
            default -> throw new IllegalArgumentException("Invalid direction");
        };
    }

    public StringBuilder solveMaze() throws Exception{
        if (maze.emptyExists()){
            String pathForwards = maze.checkEmptyRow();
            if (!pathForwards.isEmpty()) {
                return pathTried.append(pathForwards);
            }
        }
        MapPosition startPos = Maze.getStartPosition();
        this.x = startPos.x();
        this.y = startPos.y();
        this.currentDirection = startPos.direction();

        solve.solveGivenMaze(this, maze);
        return pathTried;
    }

    public boolean isVisited(MapPosition pos){
        return visitedPosition.contains(pos.x() + "," + pos.y());
    }

    private void markAsVisited(){
        visitedPosition.add(x + "," + y);
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    String getPath() {
        return pathTried.toString();
    }

}
