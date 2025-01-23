package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

import ca.mcmaster.se2aa4.mazerunner.wip.MapPosition;

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

            //Note: I have decided to leave these System.out.print statements as they are not irrelevant at this point
            // These are in use for indicating which file is being read, but this will be replaced as features are implemented
            while ((line = reader.readLine()) != null) {
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        System.out.print("WALL ");
                    } else if (line.charAt(idx) == ' ') {
                        System.out.print("PASS ");
                    }
                }
                System.out.print(System.lineSeparator());
            }

            logger.info ("Received input path: " + pathToCheck);
            String inputPath = PathInput.canonizedPath(pathToCheck);
            logger.info("Canonized input path: " + inputPath);

        } catch (Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }

        try{
            Maze maze = new Maze(mazeFile);

            MapPosition startPosition = maze.getStartPosition();
            MapPosition endPosition = maze.getEndPosition();

            System.out.println("Start Position: (" + startPosition.x() + ", " + startPosition.y() + ")");
            System.out.println("End Position: (" + endPosition.x() + ", " + endPosition.y() + ")");

        } catch (Exception e){
            logger.error("/!\\ An error has occured /!\\" + e.getMessage());
            e.printStackTrace();
        }

        logger.info("**** Computing path");
        logger.error("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }

    /**
     * Parse the user input to find the desired maze file path and the path to check
     * @return the parsed options that users have entered
     */
    public static Options parseInput(){
        Options options = new Options();

        Option mazeFilePath = new Option("i", true, "Path to the file containing the maze");
        mazeFilePath.setRequired(true);
        options.addOption(mazeFilePath);

        Option path = new Option("p", true, "Path to be checked");
        path.setRequired(true);
        options.addOption(path);

        return options;
    }

}
