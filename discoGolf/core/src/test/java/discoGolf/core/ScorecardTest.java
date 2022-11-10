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
     * Test constructor by setting course and playerName, and test for invalid input
     */
    @Test
    public void testConstructor() {
        assertEquals("Jakob", scorecard.getPlayerName());
        assertEquals(1, scorecard.getCurrentHole());
        assertEquals(0, scorecard.getScore());
        assertEquals(3, scorecard.getCurrentHolePar());
        assertEquals(3, scorecard.getCurrentHoleThrows());
        assertEquals("Lade", scorecard.getCourseName());
        assertThrows(IllegalStateException.class, () -> {
            new Scorecard(null, "Jørn");
        }, "Need a valoid course object");
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
     * Test nextHole() and previousHole() methods in scorecard, test that 
     * you cannot go to previous hole when hole number is 1 or course size
     */
    @Test
    public void testNextAndPreviousHole() {
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
     * Test getBetsHoleScore which is should return the hole on 
     * the scorecard with best individual score. 
     */
    @Test
    public void testGetBestHoleScore() {
       assertEquals(0, scorecard.getBestHole());
       scorecard.nextHole();
       scorecard.getCurrentHoleInstance().removeThrow();
       assertEquals(-1, scorecard.getBestHole());
       scorecard.getCurrentHoleInstance().removeThrow();
       scorecard.getCurrentHoleInstance().removeThrow();
       assertEquals(-3, scorecard.getBestHole());
    }

    /**
     * Test getTotalScore() method, and that the totalscore is correct after
     * updating the scorecard with throws
    */
    @Test
    public void testGetTotalScore() {
       assertEquals(0, scorecard.getScore());
       for (int i = 0; i < 4; i++) {
           scorecard.getCurrentHoleInstance().addThrow();
       }
       assertEquals(4, scorecard.getScore());
       scorecard.nextHole();
       scorecard.getCurrentHoleInstance().removeThrow();
       assertEquals(3, scorecard.getScore());
       for (int i = 0; i < 7; i++) {
           for (int j = 0; j < 5; j++) {
               scorecard.getCurrentHoleInstance().addThrow();
           }
           scorecard.nextHole();
       }
       assertEquals(38, scorecard.getScore());
    }
}
