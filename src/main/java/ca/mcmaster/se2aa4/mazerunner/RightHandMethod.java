package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RightHandMethod implements SolveMaze {
    @Override
    public void solveGivenMaze(ParseMaze parseMaze, Maze maze) {
        final Logger logger = LogManager.getLogger();
        MazeIterator iterator = new MazeIterator(parseMaze, maze);

        try {
            while (iterator.hasNext()) {
                iterator.next(); // iterate through the maze
            }

            // check for at exit
            MapPosition exitLocation = Maze.getEndPosition();
            if (parseMaze.getX() == exitLocation.x() && parseMaze.getY() == exitLocation.y()) {
                logger.info("Successfully reached the exit at ({}, {})", exitLocation.x(), exitLocation.y());
            } else {
                logger.info("Failed to reach the exit");
            }
        } catch (Exception e) {
            logger.error("Error while solving maze: {}", e.getMessage());
        }
    }
}