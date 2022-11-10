package discoGolf.core;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * J-unit test for ScorecardDAO class
 * @author Jakob Opland
 * @version 1.0
 * @since 2022-10-31
 */
public class ScorecardDAOTest {
    private ScorecardDAO scorecard;
    private Course course;

    @BeforeEach
    public void setUp() {
        course = new Course("Lade", new ArrayList<>(Arrays.asList(3, 4, 5, 3, 4, 5, 3, 4, 5)));
        scorecard = new ScorecardDAO(course, "Maren", 10, 1);
    }
    
    /**
     * Test constructor by setting course, playerName, totalScore and bestHole. 
     * Also, test for invalid input and if the values are sat correct.
    */
    @Test
    public void testConstructor() {
        assertEquals("Maren", scorecard.getPlayerName());
        assertEquals(10, scorecard.getScore());
        assertEquals(1, scorecard.getBestHole());
        assertEquals("Lade", scorecard.getCourseName());
        assertEquals(9, scorecard.getCourse().getNumberOfHoles());
    }

}
