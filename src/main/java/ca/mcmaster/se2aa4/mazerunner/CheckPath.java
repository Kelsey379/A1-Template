package ca.mcmaster.se2aa4.mazerunner;
import static ca.mcmaster.se2aa4.mazerunner.Maze.getEndPosition;

public class CheckPath {

    //WIP - need to get this to actually break when the path is incorrect
    public static boolean checkPath(ParseMaze parseMaze, Maze maze, String path) throws Exception {
        boolean correct = false;
        for (char action: path.toCharArray()) {
            switch (action){
                case 'R':
                    parseMaze.turnRight();
                    if (parseMaze.getX() == getEndPosition().x() && parseMaze.getY() == getEndPosition().y()) {
                        System.out.println("Correct Path Reached!");
                        correct = true;
                        return true;
                    }
                    break;
                case 'L':
                    parseMaze.turnLeft();
                    if (parseMaze.getX() == getEndPosition().x() && parseMaze.getY() == getEndPosition().y()) {
                        System.out.println("Correct Path Reached!");
                        correct = true;
                        return true;
                    }
                    break;
                case 'F':
                    if (canMove(parseMaze, maze)){
                        parseMaze.moveForward();
                        if (parseMaze.getX() == getEndPosition().x() && parseMaze.getY() == getEndPosition().y()) {
                            System.out.println("Correct Path Reached!");
                            correct = true;
                            return true;
                        }
                        break;
                    } else {
                        if(!correct){
                            System.out.println("Incorrect Path");
                            correct = false;
                            return false;
                        }
                    }
                    break;
                default:
                    if(!correct){
                        System.out.println("Incorrect Path");
                        correct = false;
                        return false;
                    }
            }
        }
        System.out.println("correct Path: " + correct);
        return correct;
    }


    private static boolean canMove(ParseMaze parseMaze, Maze maze) {
        MapPosition nextPosition = parseMaze.findNextPosition();
        return maze.isInBounds(nextPosition.x(), nextPosition.y()) && !maze.isWall(nextPosition.x(), nextPosition.y());
    }
}
