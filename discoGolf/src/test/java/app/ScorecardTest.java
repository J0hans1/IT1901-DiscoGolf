package app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * TestFX Scorecard class test
 */
public class ScorecardTest {
    private Scorecard scorecard;

    @BeforeEach
    public void setUp() {
        scorecard = new Scorecard("Jakob", 9);
    }
    
    @Test
    public void testController() {
        assertEquals("Jakob", scorecard.getNameOfPlayer());
        assertEquals(1, scorecard.getCurrentHole());
    }

    @Test
    public void testWholeScore() {
        for (int i = 0; i < 7; i++) {
            scorecard.addThrow();
        }
        scorecard.removeThrow();
        assertEquals(6, scorecard.getCurrentHoleScore());
        
        for (int i = 0; i < 5; i++) {
            scorecard.removeThrow();
        }
        scorecard.addThrow();
        assertEquals(2, scorecard.getCurrentHoleScore());
    }

    @Test
    public void testTotalScore() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                scorecard.addThrow();
            }
            scorecard.removeThrow();
            scorecard.nextHole();
        }
        assertEquals(36, scorecard.getTotalScore());
        assertEquals(9, scorecard.getCurrentHole());
        assertEquals(4, scorecard.getCurrentHoleScore());
    }
}

