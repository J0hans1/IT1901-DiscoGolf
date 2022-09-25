package app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

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
    
    @Test
    @DisplayName("Test the controller when creating a Scorecard object")
    public void testController() {
        assertEquals("Jakob", scorecard.getNameOfPlayer());
        assertEquals(1, scorecard.getCurrentHole());
        assertEquals(0, scorecard.getTotalScore());
    }

    @Test
    @DisplayName("Test nextHole() and previousHole() methods")
    public void testCurrentHole() {
        scorecard.previousHole();
        assertEquals(1, scorecard.getCurrentHole()
        , "Current hole cant go under 1");
        for (int i = 0; i < 10; i++) {
            scorecard.nextHole();
        }
        assertEquals(9, scorecard.getCurrentHole()
        , "Since the number of holes is 9, current hole can't be more than 9");
        for (int i = 0; i < 5; i++) {
            scorecard.previousHole();
        }
        assertEquals(4, scorecard.getCurrentHole()
        , "The current hole is now 9-5 = 4");
    }


    @Test
    @DisplayName("Test addThrow() and removeThrow() methods")
    public void testHoleThrows() {
        for (int i = 0; i < 3; i++) {
            scorecard.addThrow();
        }
        assertEquals(6, scorecard.getCurrentHoleThrows()
        , "Throws always start on the hole's par, so here it starts on 3");
        for (int i = 0; i < 5; i++) {
            scorecard.removeThrow();
        }
        assertEquals(1, scorecard.getCurrentHoleThrows());
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

