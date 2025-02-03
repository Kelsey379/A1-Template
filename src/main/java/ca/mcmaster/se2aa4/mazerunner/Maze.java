package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Maze {

    static List<List<Boolean>> maze = new ArrayList<>();

    private static MapPosition startPosition;
    private static MapPosition endPosition;
    private static DirectionOrientation.Direction defaultDirection;

    public boolean isWall(int x, int y) {
        boolean isWall = !maze.get(y).get(x);
        return isWall;
    }

    public boolean isInBounds (int x, int y) {
        return x >= 0 && x < maze.getFirst().size() && y >= 0 && y < maze.size();
    }

    public Maze(String mazeFile) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(mazeFile));
        String line;
        int nonEmptyLineLength = -1;

        while ((line = reader.readLine()) != null) {
            List<Boolean> mazeLine = new ArrayList<>();
            if (line.trim().isEmpty()) {
                if (nonEmptyLineLength != -1) {
                    for (int i = 0; i < nonEmptyLineLength; i++) {
                        mazeLine.add(true);
                    }
                }
            } else {
                nonEmptyLineLength = line.length();
                for (int idx = 0; idx < line.length(); idx++) {
                    if (line.charAt(idx) == '#') {
                        mazeLine.add(false);
                    } else if (line.charAt(idx) == ' ') {
                        mazeLine.add(true);
                    }
                }
            }
            maze.add(mazeLine);
        }

    }

    // Locate the maze starting point
    public static MapPosition getStartPosition() throws Exception {
        for (int i = 0; i<maze.size(); i++ ) {
            if (maze.get(i).getFirst()) {
                defaultDirection = DirectionOrientation.Direction.EAST;
                startPosition = new MapPosition(0,i, defaultDirection);
                return startPosition;
            }
            if (maze.get(i).getLast()){
                defaultDirection = DirectionOrientation.Direction.WEST;
                startPosition = new MapPosition(maze.get(i).size() -1,i, defaultDirection);
                return startPosition;
            }
        }
        throw new Exception("Maze has no entry points");

    }

    // Find the exit point for the maze
    public static MapPosition getEndPosition() throws Exception{
        for (int j = 0; j<maze.size(); j++ ){
            if(maze.get(j).getFirst() && !(startPosition.x() == 0 && startPosition.y() == j)) {
                endPosition = new MapPosition(0, j, defaultDirection);
                return endPosition;
            }
            if (maze.get(j).getLast() && !(startPosition.x() == maze.get(j).size() - 1 && startPosition.y() == j)) {
                endPosition = new MapPosition(maze.get(j).size() - 1, j, defaultDirection);
                return endPosition;
            }
        }
        throw new Exception("Maze has no exit points");
    }

    // For MVP, check for empty rows, if empty return the forward path to reach the exit
    public String checkEmptyRow() {
        for (List<Boolean> row : maze) {
            if (row.stream().allMatch(Boolean.TRUE::equals)) {
                StringBuilder pathForwards = new StringBuilder();
                for (int i = 0; i < row.size()-1; i++) {
                    pathForwards.append("F");
                }
                return pathForwards.toString();
            }

        }
        return "";
    }

    //Check if there is an empty row
     public Boolean emptyExists(){
        String pathOut = checkEmptyRow();
         return !Objects.equals(pathOut, "");
     }

}
