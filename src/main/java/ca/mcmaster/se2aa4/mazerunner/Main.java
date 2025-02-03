package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import apache cli for part #1
import org.apache.commons.cli.*;

public class Main {

    static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        logger.info("** Starting Maze Runner");


        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine = null;

        String mazeFile = null;
        String pathToCheck = null;
        String isEmptyRow = "";

        try {
            // Try to get the maze file, otherwise catch exception
            commandLine = parser.parse(parseInput(), args);
            mazeFile = null;
            if (commandLine.hasOption('i')) {
                mazeFile = commandLine.getOptionValue('i');
            }

            logger.info("**** Reading the maze from file " + mazeFile);
            BufferedReader reader = new BufferedReader(new FileReader(mazeFile));
            String line;

            Maze maze = new Maze(mazeFile);

            // Get the start and end positions and log them for information purposes (helps with debugging)
            MapPosition startPosition = maze.getStartPosition();
            MapPosition endPosition = maze.getEndPosition();

            if (commandLine.hasOption('p')) {
                ParseMaze parseMaze = new ParseMaze(maze, startPosition.x(), startPosition.y(), startPosition.direction(), new RightHandMethod());
                pathToCheck  = commandLine.getOptionValue('p');
                Boolean emptyRowExists = maze.emptyExists();
                CheckPath.checkPath(parseMaze, maze, pathToCheck);
                if(CheckPath.checkPath(parseMaze, maze, pathToCheck)) {
                    System.out.println("Correct Path");
                } else {
                    System.out.println("Incorrect Path");
                }
            } else{
                // Try check for empty paths, if they exist print the output to exit the maze
                Boolean emptyRowExists = maze.emptyExists();
                if(emptyRowExists) {
                    isEmptyRow = maze.checkEmptyRow();
                    System.out.println(PathInput.factorizePath(isEmptyRow));
                }

                if (isEmptyRow.isEmpty()) {
                    logger.info("**** Parsing maze with right-hand rule method");
                    ParseMaze parseMaze = new ParseMaze(maze, startPosition.x(), startPosition.y(), startPosition.direction(), new RightHandMethod());
                    parseMaze.solveMaze();
                    PathInput pathInput = new PathInput();
                    System.out.println(PathInput.factorizePath(parseMaze.getPath()));
                }
            }

        } catch (Exception e){
            logger.error("/!\\ An error has occurred /!\\" + e.getMessage());
            logger.info("**** Computing path");
            logger.error("PATH NOT COMPUTED");
            logger.info("** End of MazeRunner");
        }
    }

    // Parse the user input to find the maze file and input that will be checked
    public static Options parseInput(){
        Options options = new Options();

        Option mazeFilePath = new Option("i", true, "Path to the file containing the maze");
        mazeFilePath.setRequired(true);
        options.addOption(mazeFilePath);

        Option path = new Option("p", true, "Path to be checked");
        path.setRequired(false);
        options.addOption(path);

        return options;
    }

}
