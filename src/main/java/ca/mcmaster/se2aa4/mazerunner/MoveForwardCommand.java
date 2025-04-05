package ca.mcmaster.se2aa4.mazerunner;

public class MoveForwardCommand implements Command {
    private int x;
    private int y;

    @Override
    public void execute(ParseMaze maze) {
        MapPosition nextPos = maze.findNextPosition();
        maze.x = nextPos.x();
        maze.y = nextPos.y();
        maze.pathTried.append('F');
        maze.markAsVisited();
    }
}
