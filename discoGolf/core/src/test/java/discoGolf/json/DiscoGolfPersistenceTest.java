package discoGolf.json;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import discoGolf.core.Course;
import discoGolf.core.DatabaseHandler;
import discoGolf.core.Scorecard;

public class DiscoGolfPersistenceTest {
    private Course course;
    private Scorecard scorecard;
    private DatabaseHandler data;
    private DiscoGolfPersistence persistence;

    @BeforeEach
    public void setUp() {
        course = new Course("Lade", new ArrayList<>(Arrays.asList(3,4,5,4,3,5,3,5,4)));
        scorecard = new Scorecard(course, "Ulrik");
        
    }

    @Test 
    public void testSaveScoreCard() {
        
    }
}
