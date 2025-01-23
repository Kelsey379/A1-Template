package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Maze {

    static List<List<Boolean>> maze = new ArrayList<>();

    private static MapPosition startPosition;
    private static MapPosition endPosition;

    public Maze(String mazeFile) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(mazeFile));
        String line;

        while ((line = reader.readLine()) != null) {
        }
    }


    public static MapPosition getStartPosition() throws Exception {
        throw new Exception("Maze has no entry points");

    }

    public static void setStartPosition(MapPosition startPosition) {
        Maze.startPosition = startPosition;
    }

    public static MapPosition getEndPosition() throws Exception{
        throw new Exception("Maze has no exit points");
    }

    public static void setEndPosition(MapPosition endPosition) {
        Maze.endPosition = endPosition;
    }

}
