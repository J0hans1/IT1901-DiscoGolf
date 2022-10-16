package discoGolf.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * J-unit test for Scorecard class
 * @author Ulrik Isdahl and Jakob Opland
 * @version 1.0
 * @since 2022-09-28
 */
public class ScorecardTest {
    private Scorecard scorecard;
    private Course course;

    @BeforeEach
    public void setUp() {
        course = new Course("Lade", new ArrayList<>(Arrays.asList(3, 4, 5, 3, 4, 5, 3, 4, 5)));
        scorecard = new Scorecard(course, "Jakob");
    }
    
    /**
     * Test constructor 1 by setting course and playerName, and test for invalid input
     */
    @Test
    public void testConstructor1() {
        assertEquals("Jakob", scorecard.getPlayerName());
        assertEquals(1, scorecard.getCurrentHole());
        assertEquals(0, scorecard.getTotalScore());
        assertEquals(new ArrayList<>(Arrays.asList(3, 4, 5, 3, 4, 5, 3, 4, 5)), scorecard.getThrowsList());

        assertThrows(IllegalStateException.class, () -> {
            new Scorecard(null, "Jørn");
        }, "Need to select a course");
        assertThrows(IllegalArgumentException.class, () -> {
            new Scorecard(course, "Jørn.?");
        }, "Name can only contain letters and numbers");
        assertThrows(IllegalArgumentException.class, () -> {
            new Scorecard(course, "");
        }, "Name cannot be empty");
        assertThrows(IllegalArgumentException.class, () -> {
            new Scorecard(course, "//@");
        }, "Not a valid name");
    }

    /**
     * Test constructor 2 by setting course, playerName and throwsList, and test for invalid input
     */
    @Test
    public void testConstructor2() {
        scorecard = new Scorecard(course, "Markus", new ArrayList<>(Arrays.asList(3, 4, 5, 3, 4, 5, 3, 4, 5)));
        assertEquals("Markus", scorecard.getPlayerName());
        assertEquals(9, scorecard.getCurrentHole());
        //assertEquals(, scorecard.getTotalScore());
        assertEquals(new ArrayList<>(Arrays.asList(3, 4, 5, 3, 4, 5, 3, 4, 5)), scorecard.getThrowsList());

        assertThrows(IllegalArgumentException.class, () -> {
            new Scorecard(course, "jakob", new ArrayList<>(Arrays.asList(3, 4, 5, 3, 4, 5, 3, 4, 5, 6)));
        }, "Throws list has not correct length");
        assertThrows(IllegalArgumentException.class, () -> {
            new Scorecard(course, "jakob", new ArrayList<>(Arrays.asList(3, 4)));
        }, "Throws list is too short");
        assertThrows(IllegalArgumentException.class, () -> {
            new Scorecard(course, "jakob", new ArrayList<>(Arrays.asList(3, 4, 5, 3, 4, 5, 0, 4, 5)));
        }, "Cannot have 0 throws on hole 7");
    }

    /**
     * Test nextHole() and previousHole() methods in scorecard, test that 
     * you cannot go to previous hole when hole number is 1 or course size
     */
    @Test
    public void testCurrentHole() {
        assertThrows(IllegalStateException.class, () -> {
            scorecard.previousHole();
        }, "cannot go to previous hole when current hole number is 1");
        for (int i = 0; i < 8; i++) {
            scorecard.nextHole();
        }
        assertEquals(9, scorecard.getCurrentHole());
        assertThrows(IllegalStateException.class, () -> {
            scorecard.nextHole();
        }, "cannot go to next hole because it doesnt exist");
        for (int i = 0; i < 5; i++) {
            scorecard.previousHole();
        }
        assertEquals(4, scorecard.getCurrentHole());
    }


    /**
     * Test removeThrow() and addThrow() methods, should throw exception when
     * current throws are 1 and you try to remove throw
     */
    @Test
    public void testHoleThrows() {
        for (int i = 0; i < 2; i++) {
            scorecard.removeThrow();
        }
        assertThrows(IllegalStateException.class, () -> {
            scorecard.removeThrow();
        }, "Need at least 1 throw");
        for (int i = 0; i < 5; i++) {
            scorecard.addThrow();
        }
        assertEquals(6, scorecard.getCurrentHoleThrows()
        , "Throws always start on the hole's par, so here it starts on 3");
    }

    /**
     * Test getTotalScore() method, and that the totalscore is correct after
     * updating the scorecard with throws
     */
    @Test
    public void testTotalScore() {
        assertEquals(0, scorecard.getTotalScore());
        for (int i = 0; i < 4; i++) {
            scorecard.addThrow();
        }
        assertEquals(4, scorecard.getTotalScore());
        scorecard.nextHole();
        scorecard.removeThrow();
        assertEquals(3, scorecard.getTotalScore());
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                scorecard.addThrow();
            }
            scorecard.nextHole();
        }
        assertEquals(38, scorecard.getTotalScore());
    }
}
