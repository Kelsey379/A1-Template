package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class RightHandMethod implements SolveMaze {
    private final Set<String> visitedPositions = new HashSet<>();
    @Override
    public void solveGivenMaze(ParseMaze parseMaze, Maze maze) {
        final Logger logger = LogManager.getLogger();
        Command moveForwardCommand = new MoveForwardCommand();
        Command turnLeftCommand = new TurnLeftCommand();
        Command turnRightCommand = new TurnRightCommand();

        while (!atExit(parseMaze, maze)) {
            String currentState = parseMaze.getX() + "," + parseMaze.getY() + "," + parseMaze.getCurrentDirection();

            if (visitedPositions.contains(currentState)) {
                logger.info("Infinite loop detected, breaking out");
                break;
            }
            visitedPositions.add(currentState);

            parseMaze.executeCommand(turnRightCommand);
            if (canMove(parseMaze, maze)) {
                parseMaze.executeCommand(moveForwardCommand);
            } else {
                parseMaze.executeCommand(turnLeftCommand);
                if (canMove(parseMaze, maze)) {
                    parseMaze.executeCommand(moveForwardCommand);
                } else {
                    parseMaze.executeCommand(turnLeftCommand);
                    if (canMove(parseMaze, maze)) {
                        parseMaze.executeCommand(moveForwardCommand);
                    } else {
                        parseMaze.executeCommand(turnLeftCommand);
                        if (canMove(parseMaze, maze)) {
                            parseMaze.executeCommand(moveForwardCommand);
                        }else {
                            logger.info("Infinite loop detected, breaking out");
                            break;
                        }
                    }
                }
            }
        }
    }


    private boolean atExit(ParseMaze parseMaze, Maze maze) {
        try {
            MapPosition exitLocation = Maze.getEndPosition();
            return parseMaze.getX() == exitLocation.x() && parseMaze.getY() == exitLocation.y();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean canMove(ParseMaze parseMaze, Maze maze) {
        MapPosition nextPosition = parseMaze.findNextPosition();
        return maze.isInBounds(nextPosition.x(), nextPosition.y()) && !maze.isWall(nextPosition.x(), nextPosition.y());
    }

}
