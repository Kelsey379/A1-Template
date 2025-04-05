package ca.mcmaster.se2aa4.mazerunner;
import static ca.mcmaster.se2aa4.mazerunner.Maze.getEndPosition;

public class CheckPath {

    public static boolean checkPath(ParseMaze parseMaze, Maze maze, String path) throws Exception {
        boolean correct = false;
        Command turnRightCommand = new TurnRightCommand();
        Command turnLeftCommand = new TurnLeftCommand();
        Command moveForwardCommand = new MoveForwardCommand();

        for (char action: path.toCharArray()) {
            switch (action){
                case 'R':
                    parseMaze.executeCommand(turnRightCommand);
                    if (parseMaze.getX() == getEndPosition().x() && parseMaze.getY() == getEndPosition().y()) {
                        return true;
                    }
                    break;
                case 'L':
                    parseMaze.executeCommand(turnLeftCommand);
                    if (parseMaze.getX() == getEndPosition().x() && parseMaze.getY() == getEndPosition().y()) {
                        return true;
                    }
                    break;
                case 'F':
                    if (canMove(parseMaze, maze)){
                        parseMaze.executeCommand(moveForwardCommand);
                        if (parseMaze.getX() == getEndPosition().x() && parseMaze.getY() == getEndPosition().y()) {
                            return true;
                        }
                        break;
                    } else {
                        if(!correct){
                            return false;
                        }
                    }
                    break;
                default:
                    if(!correct){
                        return false;
                    }
            }
        }
        return correct;
    }


    private static boolean canMove(ParseMaze parseMaze, Maze maze) {
        MapPosition nextPosition = parseMaze.findNextPosition();
        return maze.isInBounds(nextPosition.x(), nextPosition.y()) && !maze.isWall(nextPosition.x(), nextPosition.y());
    }
}
