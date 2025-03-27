import static org.junit.jupiter.api.Assertions.*;

import ca.mcmaster.se2aa4.mazerunner.PathInput;
import org.junit.jupiter.api.Test;

public class PathInputTest {

    @Test
    public void testCanonizedPath() {
        String input = "4R3L2F";
        String output = PathInput.canonizedPath(input);
        assertEquals("RRRRLLLFF", output);
    }

    @Test
    public void testFactorizePath() {
        String input = "RRFLLLFR";
        String output = PathInput.factorizePath(input);
        assertEquals("2RF3LFR", output);
    }

    @Test
    public void testStraightCorrection() {
        String input = "RL";
        String output = PathInput.canonizedPath(input);
        assertEquals("", output);
    }
}
