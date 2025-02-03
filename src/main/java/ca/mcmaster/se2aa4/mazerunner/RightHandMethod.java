package ca.mcmaster.se2aa4.mazerunner;

import java.util.HashSet;
import java.util.Set;

public class RightHandMethod implements SolveMaze {
    private final Set<String> visitedPositions = new HashSet<>();
    @Override
    public void solveGivenMaze(ParseMaze parseMaze, Maze maze) {
        while (!atExit(parseMaze, maze)) {
            String currentState = parseMaze.getX() + "," + parseMaze.getY() + "," + parseMaze.getCurrentDirection();

            if (visitedPositions.contains(currentState)) {
                System.out.println("Loop detected! Breaking out...");
                break;
            }
            visitedPositions.add(currentState);

            parseMaze.turnRight();
            if (canMove(parseMaze, maze)) {
                parseMaze.moveForward();
            } else {
                parseMaze.turnLeft();
                if (canMove(parseMaze, maze)) {
                    parseMaze.moveForward();
                } else {
                    parseMaze.turnLeft();
                    if (canMove(parseMaze, maze)) {
                        parseMaze.moveForward();
                    } else {
                        parseMaze.turnLeft();
                        if (canMove(parseMaze, maze)) {
                            parseMaze.moveForward();
                        }else {
                            System.out.println("Breaking ");
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
