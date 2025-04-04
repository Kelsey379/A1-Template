package ca.mcmaster.se2aa4.mazerunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MazeIterator implements Iterator<DirectionOrientation.Direction> {
    private final ParseMaze parseMaze;
    private final Maze maze;
    private final Set<String> visitedPositions = new HashSet<>();
    private String currentState;
    private final Logger logger = LogManager.getLogger();
    private boolean isStuck = false;

    private final Command moveForwardCommand = new MoveForwardCommand();
    private final Command turnLeftCommand = new TurnLeftCommand();
    private final Command turnRightCommand = new TurnRightCommand();

    public MazeIterator(ParseMaze parseMaze, Maze maze) {
        this.parseMaze = parseMaze;
        this.maze = maze;
        this.currentState = parseMaze.getX() + "," + parseMaze.getY() + "," + parseMaze.getCurrentDirection();
    }

    @Override
    public boolean hasNext() {
        // make sure there are still moves to make
        try {
            // check if exit has been found or if we're stuck (no exit)
            if (isStuck || atExit()) {
                return false;
            }

            // detect loops
            if (visitedPositions.contains(currentState)) {
                logger.info("Infinite loop detected, breaking out");
                isStuck = true;
                return false;
            }

            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DirectionOrientation.Direction next() {
        visitedPositions.add(currentState);
        // store current facing direction
        DirectionOrientation.Direction currentDirection = parseMaze.getCurrentDirection();

        // move with RH method
        parseMaze.executeCommand(turnRightCommand);
        if (canMove()) {
            parseMaze.executeCommand(moveForwardCommand);
            updateState();
            return currentDirection;
        }

        // if right is wall, turn back and check move forward
        parseMaze.executeCommand(turnLeftCommand);
        if (canMove()) {
            parseMaze.executeCommand(moveForwardCommand);
            updateState();
            return currentDirection;
        }

        // if forward is wall, try left
        parseMaze.executeCommand(turnLeftCommand);
        if (canMove()) {
            parseMaze.executeCommand(moveForwardCommand);
            updateState();
            return currentDirection;
        }

        // if still blocked, turn left again to turn around (u-turn)
        parseMaze.executeCommand(turnLeftCommand);
        if (canMove()) {
            parseMaze.executeCommand(moveForwardCommand);
            updateState();
            return currentDirection;
        }

        // if still no moves, we're stuck/looping
        logger.info("No possible moves, maze cannot be solved");
        isStuck = true;
        return currentDirection;
    }

    private boolean atExit() {
        try {
            MapPosition exitLocation = Maze.getEndPosition();
            return parseMaze.getX() == exitLocation.x() && parseMaze.getY() == exitLocation.y();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean canMove() {
        MapPosition nextPosition = parseMaze.findNextPosition();
        return maze.isInBounds(nextPosition.x(), nextPosition.y()) && !maze.isWall(nextPosition.x(), nextPosition.y());
    }

    private void updateState() {
        currentState = parseMaze.getX() + "," + parseMaze.getY() + "," + parseMaze.getCurrentDirection();
    }
}