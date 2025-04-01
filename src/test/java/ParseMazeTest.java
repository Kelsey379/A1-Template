import static org.junit.jupiter.api.Assertions.*;

import ca.mcmaster.se2aa4.mazerunner.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockedStatic;


public class ParseMazeTest {

    private Maze maze;
    private SolveMaze solveMaze;
    private ParseMaze parseMaze;
    private MockedStatic<Maze> mockedStaticMaze;

    @BeforeEach
    void setUp() throws Exception {
        // ARRANGE - using mockito to create a mock to test the ParseMaze functionality
        maze = Mockito.mock(Maze.class);
        solveMaze = Mockito.mock(SolveMaze.class);
        MapPosition startPos = new MapPosition(1, 1, DirectionOrientation.Direction.WEST);
        Mockito.when(maze.emptyExists()).thenReturn(false);
        mockedStaticMaze = Mockito.mockStatic(Maze.class);
        mockedStaticMaze.when(Maze::getStartPosition).thenReturn(startPos);
        parseMaze = new ParseMaze(maze, 1, 1, DirectionOrientation.Direction.WEST, solveMaze);
    }

    @AfterEach
    void tearDown() {
        if (mockedStaticMaze != null) {
            mockedStaticMaze.close();
        }
    }

    @Test
    void testInitialValues() {
        assertEquals(1, parseMaze.getX());
        assertEquals(1, parseMaze.getY());
        assertEquals(DirectionOrientation.Direction.WEST, parseMaze.getCurrentDirection());
    }

    @Test
    void testMoveForward() {
        // ACT
        parseMaze.turnRight();
        parseMaze.turnRight();
        parseMaze.moveForward();

        // ASSERT
        assertEquals(1, parseMaze.getX());
        assertEquals(2, parseMaze.getY());
        assertTrue(parseMaze.getPath().contains("F"));
    }

    @Test
    void testTurnRight() {
        parseMaze.turnRight();
        assertEquals(DirectionOrientation.Direction.NORTH, parseMaze.getCurrentDirection());
        assertTrue(parseMaze.getPath().contains("R"));
    }

    @Test
    void testTurnLeft() {
        parseMaze.turnLeft();
        assertEquals(DirectionOrientation.Direction.SOUTH, parseMaze.getCurrentDirection());
        assertTrue(parseMaze.getPath().contains("L"));
    }

    @Test
    void testSolveMaze() throws Exception { //double check this test as it sometimes seems less consistent
        // ACT
        Mockito.when(maze.emptyExists()).thenReturn(true);
        String path = "FFRFF";
        Mockito.when(maze.checkEmptyRow()).thenReturn(path);

        StringBuilder result = parseMaze.solveMaze();

        // ASSERT
        assertEquals(path, result.toString());
    }

    @Test
    void testMarkAsVisited() {
        parseMaze.moveForward();
        assertTrue(parseMaze.getPath().contains("F"));
    }

    @Test
    void testFindNextPosition() throws Exception {
        MapPosition nextPosition = parseMaze.findNextPosition();
        // ASSERT
        assertEquals(1, nextPosition.x());
        assertEquals(0, nextPosition.y());
        assertEquals(DirectionOrientation.Direction.WEST, nextPosition.direction());
    }
}