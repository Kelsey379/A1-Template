import static org.junit.jupiter.api.Assertions.*;
import ca.mcmaster.se2aa4.mazerunner.TurnRightCommand;
import ca.mcmaster.se2aa4.mazerunner.TurnLeftCommand;
import ca.mcmaster.se2aa4.mazerunner.MoveForwardCommand;
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
    void testInitialXValueIs1() {
        assertEquals(1, parseMaze.getX());
    }

    @Test
    void testInitialYValueIs1() {
        assertEquals(1, parseMaze.getY());
    }

    @Test
    void testInitialDirectionIsWest() {
        assertEquals(DirectionOrientation.Direction.WEST, parseMaze.getCurrentDirection());
    }

    @Test
    void testMoveForward() {
        // ACT
        Command moveForwardCommand = new MoveForwardCommand();
        parseMaze.executeCommand(moveForwardCommand);

        // ASSERT
        assertTrue(parseMaze.getPath().contains("F"));
    }

    @Test
    void testTurnRightChangeDirection() {
        // ACT
        Command turnRightCommand = new TurnRightCommand();
        parseMaze.executeCommand(turnRightCommand);

        //ASSERT
        assertEquals(DirectionOrientation.Direction.NORTH, parseMaze.getCurrentDirection());
    }

    @Test
    void testTurnRightPathContainsR() {
        // ACT
        Command turnRightCommand = new TurnRightCommand();
        parseMaze.executeCommand(turnRightCommand);

        //ASSERT
        assertTrue(parseMaze.getPath().contains("R"));
    }

    @Test
    void testTurnLeftChangesDirection() {
        // ACT
        Command turnLeftCommand = new TurnLeftCommand();
        parseMaze.executeCommand(turnLeftCommand);

        //ASSERT
        assertEquals(DirectionOrientation.Direction.SOUTH, parseMaze.getCurrentDirection());
    }

    @Test
    void testTurnLeftPathContainsL() {
        // ACT
        Command turnLeftCommand = new TurnLeftCommand();
        parseMaze.executeCommand(turnLeftCommand);

        // ASSERT
        assertTrue(parseMaze.getPath().contains("L"));
    }

    @Test
    void testSolveMaze() throws Exception {
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
        Command moveForwardCommand = new MoveForwardCommand();
        parseMaze.executeCommand(moveForwardCommand);
        assertTrue(parseMaze.getPath().contains("F"));
    }

    @Test
    void testFindNextPositionXIs1() throws Exception {
        MapPosition nextPosition = parseMaze.findNextPosition();
        // ASSERT
        assertEquals(1, nextPosition.x());
    }

    @Test
    void testFindNextPositionYIs0() throws Exception {
        MapPosition nextPosition = parseMaze.findNextPosition();
        assertEquals(0, nextPosition.y());
    }

    @Test
    void testFindNextPositionDirectionIsWest() throws Exception {
        MapPosition nextPosition = parseMaze.findNextPosition();
        // ASSERT
        assertEquals(DirectionOrientation.Direction.WEST, nextPosition.direction());
    }
}
