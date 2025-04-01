import ca.mcmaster.se2aa4.mazerunner.DirectionOrientation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class DirectionOrientationTest {

    @Test
    public void testRightTurn() {
        assertEquals(DirectionOrientation.Direction.EAST, DirectionOrientation.Direction.NORTH.rightTurn());
        assertEquals(DirectionOrientation.Direction.WEST, DirectionOrientation.Direction.SOUTH.rightTurn());
        assertEquals(DirectionOrientation.Direction.SOUTH, DirectionOrientation.Direction.EAST.rightTurn());
        assertEquals(DirectionOrientation.Direction.NORTH, DirectionOrientation.Direction.WEST.rightTurn());
    }

    @Test
    public void testLeftTurn() {
        assertEquals(DirectionOrientation.Direction.WEST, DirectionOrientation.Direction.NORTH.leftTurn());
        assertEquals(DirectionOrientation.Direction.EAST, DirectionOrientation.Direction.SOUTH.leftTurn());
        assertEquals(DirectionOrientation.Direction.NORTH, DirectionOrientation.Direction.EAST.leftTurn());
        assertEquals(DirectionOrientation.Direction.SOUTH, DirectionOrientation.Direction.WEST.leftTurn());
    }



}
