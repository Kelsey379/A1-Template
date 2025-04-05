import static org.junit.jupiter.api.Assertions.*;

import ca.mcmaster.se2aa4.mazerunner.PathInput;
import org.junit.jupiter.api.Test;

public class PathInputTest {

    @Test
    public void testPathConvertedToCanonizedForm() {
        // ARRANGE
        String input = "4R3L2F";
        // ACT
        String output = PathInput.canonizedPath(input);
        // ASSERT
        assertEquals("RRRRLLLFF", output);
    }

    @Test
    public void testPathCanBeFactorized() {
        // ARRANGE
        String input = "RRFLLLFR";
        // ACT
        String output = PathInput.factorizePath(input);
        // ASSERT
        assertEquals("2RF3LFR", output);
    }

    @Test
    public void testConvertRightLeftToStraight() {
        // ARRANGE
        String input = "RL";
        // ACT
        String output = PathInput.canonizedPath(input);
        // ASSERT
        assertEquals("", output);
    }
}
