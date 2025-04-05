package ca.mcmaster.se2aa4.mazerunner;

public class TurnRightCommand implements Command {
    @Override
    public void execute (ParseMaze maze) {
        maze.currentDirection = maze.currentDirection.rightTurn();
        maze.pathTried.append('R');
    }
}
