import ca.mcmaster.se2aa4.mazerunner.DirectionOrientation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class DirectionOrientationTest {

    @Test
    public void testNorthRightTurnIsEast() {
        // ASSERT
        assertEquals(DirectionOrientation.Direction.EAST, DirectionOrientation.Direction.NORTH.rightTurn());
    }

    @Test
    public void testSouthRightTurnIsWest() {
        // ASSERT
        assertEquals(DirectionOrientation.Direction.WEST, DirectionOrientation.Direction.SOUTH.rightTurn());
    }

    @Test
    public void testEastRightTurnIsSouth() {
        //ASSERT
        assertEquals(DirectionOrientation.Direction.SOUTH, DirectionOrientation.Direction.EAST.rightTurn());
    }

    @Test
    public void testWestRightTurnIsNorth() {
        assertEquals(DirectionOrientation.Direction.NORTH, DirectionOrientation.Direction.WEST.rightTurn());
    }

    @Test
    public void testNorthLeftTurnIsWest() {
        assertEquals(DirectionOrientation.Direction.WEST, DirectionOrientation.Direction.NORTH.leftTurn());
    }

    @Test
    public void testSouthLeftTurnIsEast() {
        // ASSERT
        assertEquals(DirectionOrientation.Direction.EAST, DirectionOrientation.Direction.SOUTH.leftTurn());
    }

    @Test
    public void testEastLeftTurnIsNorth() {
        assertEquals(DirectionOrientation.Direction.NORTH, DirectionOrientation.Direction.EAST.leftTurn());
    }

    @Test
    public void testWestLeftTurnIsSouth() {
        assertEquals(DirectionOrientation.Direction.SOUTH, DirectionOrientation.Direction.WEST.leftTurn());
    }



}
