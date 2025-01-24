package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//import apache cli for part #1
import org.apache.commons.cli.*;

public class Main {

    private static final Logger logger = LogManager.getLogger();

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
            if (commandLine.hasOption('p')) {
                pathToCheck  = commandLine.getOptionValue('p');
            }

            logger.info("**** Reading the maze from file " + mazeFile);
            BufferedReader reader = new BufferedReader(new FileReader(mazeFile));
            String line;

            // This code will be used to confirm the input path later
            // It has been commented for now as the full functionality has yet to be implemented

//            logger.info ("Received input path: " + pathToCheck);
//            String inputPath = PathInput.canonizedPath(pathToCheck);
//            logger.info("Canonized input path: " + inputPath);

        } catch (Exception e) {
            logger.error("/!\\ An error has occurred /!\\");
        }

        try{
            Maze maze = new Maze(mazeFile);

            // Get the start and end positions and log them for information purposes (helps with debugging)
            MapPosition startPosition = maze.getStartPosition();
            MapPosition endPosition = maze.getEndPosition();

            //These are used to confirm the start and end positions as needed
//            logger.info("Start Position: (" + startPosition.x() + ", " + startPosition.y() + ")");
//            logger.info("End Position: (" + endPosition.x() + ", " + endPosition.y() + ")");

            // Try check for empty paths, if they exist print the output to exit the maze
            Boolean emptyRowExists = maze.emptyExists();
            if(emptyRowExists) {
                isEmptyRow = maze.checkEmptyRow();
                System.out.println(isEmptyRow);
            }
        } catch (Exception e){
            logger.error("/!\\ An error has occurred /!\\" + e.getMessage());
        }
        if (isEmptyRow == "") {
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
