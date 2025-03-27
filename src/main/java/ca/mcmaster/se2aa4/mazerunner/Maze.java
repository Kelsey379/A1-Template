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
        if (!isInBounds(x, y)) {
            return true;
        }
        boolean isWall = !maze.get(x).get(y);
        return isWall;
    }

    public boolean isInBounds (int x, int y) {
        return x >= 0 && x < maze.size() && y >= 0 && y < maze.get(x).size();
    }

    public Maze(String mazeFile) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(mazeFile));
        String line;
        List<String> rawLines = new ArrayList<>();
        int maxRowLength = 0;

        while ((line = reader.readLine()) != null) {
            line = line.stripTrailing();
            if (!line.trim().isEmpty()) {
                rawLines.add(line);
                maxRowLength = Math.max(maxRowLength, line.length());
            }
        }
        reader.close();

        for (String rawLine : rawLines) {
            List<Boolean> mazeLine = new ArrayList<>();

            for (int i = 0; i < rawLine.length(); i++) {
                mazeLine.add(rawLine.charAt(i) == '#' ? false : true);
            }

            while (mazeLine.size() < maxRowLength) {
                mazeLine.add(true);
            }

            maze.add(mazeLine);
        }

        for (List<Boolean> row : maze) {
            System.out.println(row);
        }
    }

    // Locate the maze starting point
    public static MapPosition getStartPosition() throws Exception {
        for (int i = 0; i<maze.size(); i++ ) {
            if (maze.get(i).getFirst()) {
                defaultDirection = DirectionOrientation.Direction.EAST;
                startPosition = new MapPosition(i,0, defaultDirection);
                return startPosition;
            }
            if (maze.get(i).getLast()){
                defaultDirection = DirectionOrientation.Direction.WEST;
                startPosition = new MapPosition(i, maze.get(i).size() -1, defaultDirection);
                return startPosition;
            }
        }
        throw new Exception("Maze has no entry points");

    }

    // Find the exit point for the maze
    public static MapPosition getEndPosition() throws Exception{
        for (int j = 0; j<maze.size(); j++ ){
            if(maze.get(j).getFirst() && !(startPosition.x() == j && startPosition.y() == 0)) {
                endPosition = new MapPosition(j, 0, defaultDirection);
                return endPosition;
            }
            if (maze.get(j).getLast() && !(startPosition.x() == j && startPosition.y() == maze.get(j).size() -1)) {
                endPosition = new MapPosition(j, maze.get(j).size() - 1, defaultDirection);
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
