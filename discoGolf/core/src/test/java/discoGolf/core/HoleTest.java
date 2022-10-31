package discoGolf.core;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * J-unit test for Scorecard class
 * @author Jakob Opland
 * @version 1.0
 * @since 2022-10-31
 */
public class HoleTest {
    private Hole hole;
  
    @BeforeEach
    public void setUp() {
        hole = new Hole(5);
    }

    /**
     * Test constructor by setting par and test if the values are correct.
     * Also, testing for invalid input.
    */
    @Test
    public void testConstructor() {
        assertEquals(5, hole.getPar());
        assertEquals(5, hole.getHoleThrows());
        assertEquals(0, hole.getHoleScore());
        assertThrows(IllegalArgumentException.class, () -> {
          new Hole(1);
        }, "Not a valid par value");
        assertThrows(IllegalArgumentException.class, () -> {
          new Hole(8);
        }, "Not a valid par value");
    }

    /**
    * Test getTotalScore() by adding and removing throws
    */
    @Test
    public void testGetHoleScore() {
        for (int i = 0; i < 10; i++) {
            assertEquals(i, hole.getHoleScore());
            hole.addThrow();
        }
        for (int i = 0; i < 12; i++) {
          assertEquals(10-i, hole.getHoleScore());
          hole.removeThrow();
        }
        assertEquals(-2, hole.getHoleScore());
    }

    /**
    * Test removeThrow() and addThrow() methods, should throw exception when
    * holeThrows are 1 and you try to remove throw
    */
    @Test
    public void testHoleThrows() {
        for (int i = 0; i < 4; i++) {
            hole.removeThrow();
        }
        assertThrows(IllegalStateException.class, () -> {
            hole.removeThrow();
        }, "Need at least 1 throw");
        for (int i = 0; i < 5; i++) {
            hole.addThrow();
        }
        assertEquals(6, hole.getHoleThrows());
    }
}
