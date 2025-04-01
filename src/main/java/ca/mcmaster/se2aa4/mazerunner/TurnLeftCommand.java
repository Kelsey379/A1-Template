package ca.mcmaster.se2aa4.mazerunner;

public class TurnLeftCommand implements Command{
    @Override
    public void execute(ParseMaze maze) {
        maze.currentDirection = maze.currentDirection.leftTurn();
        maze.pathTried.append('L');
    }
}
