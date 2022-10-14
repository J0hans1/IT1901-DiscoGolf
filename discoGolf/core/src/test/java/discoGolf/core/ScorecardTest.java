package discoGolf.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * TestFX Scorecard class test
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
            new Scorecard(null, "jakob");
        }, "Need to select a course");
        assertThrows(IllegalArgumentException.class, () -> {
            new Scorecard(course, "jakob.?");
        }, "Name can only contain letters and numbers");
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
        assertThrows(IllegalStateException.class, () -> {
            new Scorecard(null, "jakob");
        }, "Need to select a course");
        assertThrows(IllegalArgumentException.class, () -> {
            new Scorecard(course, "jakob.?");
        }, "Name can only contain letters and numbers");
    }

    @Test
    @DisplayName("Test nextHole() and previousHole() methods")
    public void testCurrentHole() {
        assertThrows(IllegalStateException.class, () -> {
            scorecard.previousHole();
        }, "cannot go to previous hole when current hole number is 1");
        for (int i = 0; i < 8; i++) {
            scorecard.nextHole();
        }
        assertEquals(9, scorecard.getCurrentHole()
        , "current hole number is supposed to be 9");
        assertThrows(IllegalStateException.class, () -> {
            scorecard.nextHole();
        }, "cannot go to next hole because it doesnt exist");
        for (int i = 0; i < 5; i++) {
            scorecard.previousHole();
        }
        assertEquals(4, scorecard.getCurrentHole()
        , "The current hole is now 9-5 = 4");
    }


    @Test
    @DisplayName("Test addThrow() and removeThrow() methods")
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

    @Test
    @DisplayName("Test that the correct total score is returned")
    public void testTotalScore() {
        for (int i = 0; i < 4; i++) {
            scorecard.addThrow();
        }
        assertEquals(4, scorecard.getTotalScore()
        , "The totalScore is 4, since the person had 4 throws over par");
        scorecard.nextHole();
        scorecard.removeThrow();
        assertEquals(3, scorecard.getTotalScore()
        , "The totalScore is 3, since the person had a hole with -1 score, 1 under par");
    }

}
