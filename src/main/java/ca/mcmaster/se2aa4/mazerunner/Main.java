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
        } catch (Exception e) {
            logger.error("/!\\ An error has occured /!\\");
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

        return options;
    }

}
