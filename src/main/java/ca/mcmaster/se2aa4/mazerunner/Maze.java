package ca.mcmaster.se2aa4.mazerunner;

import ca.mcmaster.se2aa4.mazerunner.wip.MapPosition;

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
//        line = reader.readLine();

        while ((line = reader.readLine()) != null) {
            List<Boolean> mazeLine = new ArrayList<>();
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    mazeLine.add(false);
                    System.out.print("false ");
                } else if (line.charAt(idx) == ' ') {
                    mazeLine.add(true);
                    System.out.print("true ");
                }
            }

// TO DO: fix this so that it will work with an empty row
//            if (isEmpty) {
//                for (int i = 0; i<mazeLine.size(); i++) {
//                    mazeLine.set(i, true);
//                }
//            }
            maze.add(mazeLine);
            System.out.println(System.lineSeparator());
        }
    }


    public static MapPosition getStartPosition() throws Exception {
        for (int i = 0; i<maze.size(); i++ ) {
            if (maze.get(i).get(0)) {
                startPosition = new MapPosition(0,i);
                return startPosition;
            }
            if (maze.get(i).get(maze.get(i).size() -1)){
                startPosition = new MapPosition(maze.get(i).size() -1,i);
                return startPosition;
            }
        }
        throw new Exception("Maze has no entry points");

    }

    public static void setStartPosition(MapPosition startPosition) {
        Maze.startPosition = startPosition;
    }

    public static MapPosition getEndPosition() throws Exception{
        boolean foundEnd = false;
        for (int j = 0; j<maze.size(); j++ ){
            if(maze.get(j).get(0) && !(startPosition.x() == 0 && startPosition.y() == j)) {
                endPosition = new MapPosition(0, j);
                return endPosition;
            }
  ;          if (maze.get(j).get(maze.get(j).size() - 1) && !(startPosition.x() == maze.get(j).size() - 1 && startPosition.y() == j)) {
                endPosition = new MapPosition(maze.get(j).size() - 1, j);
                return endPosition;
            }
        }
        throw new Exception("Maze has no exit points");
    }

    public static void setEndPosition(MapPosition endPosition) {
        Maze.endPosition = endPosition;
    }

}
