import static org.junit.jupiter.api.Assertions.*;

import ca.mcmaster.se2aa4.mazerunner.DirectionOrientation;
import ca.mcmaster.se2aa4.mazerunner.MapPosition;
import ca.mcmaster.se2aa4.mazerunner.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MazeTest {

    private Maze maze;

    @BeforeEach
    public void setUp() throws Exception {
        maze = new Maze("src/test/mazeSamples/testMaze.txt");
    }

//    @Test
//    public void testIsWall() {
//
//        //assertTrue(maze.isWall(0,0));
//        assertFalse(maze.isWall(1,1));
//    }

    @Test
    public void testIsInBounds() {
        assertTrue(maze.isInBounds(1,1));
        assertFalse(maze.isInBounds(100,100));
    }

    @Test
    public void testStartPosition() throws Exception {
        MapPosition start = Maze.getStartPosition();
        assertEquals(new MapPosition(0,5, DirectionOrientation.Direction.EAST), start);
    }

//    @Test
//    public void testEndPosition() throws Exception {
//        MapPosition start = Maze.getStartPosition();
//        MapPosition end = Maze.getEndPosition();
//        assertEquals(new MapPosition(6,1, DirectionOrientation.Direction.EAST), end);
//    }


}
