package discoGolf.json;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import discoGolf.core.Course;
import discoGolf.core.Data;
import discoGolf.core.Scorecard;

public class DiscoGolfPersistenceTest {
    private Course course;
    private Scorecard scorecard;
    private Data data;
    private DiscoGolfPersistence persistence;

    @BeforeEach
    public void setUp() {
        persistence = new DiscoGolfPersistence();
        course = new Course("Lade", new ArrayList<>(Arrays.asList(3,4,5,4,3,5,3,5,4)));
        scorecard = new Scorecard(course, "Ulrik");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                scorecard.addThrow();
            }
            scorecard.nextHole();
        }
    }

    /**
     * compare two scorecard object to see if they are equal
     * @param card1 scorecard object 1 
     * @param card2 scorecard ibject 2
     */
    private void compareScorecardObjects(Scorecard card1, Scorecard card2) {
        assertEquals(card1.getPlayerName(), card2.getPlayerName());
        assertEquals(card1.getTotalScore(), card2.getTotalScore());

        Iterator<Integer> throwsList1 = card1.getThrowsList().iterator();
        Iterator<Integer> throwsList2 = card2.getThrowsList().iterator();
        while(throwsList1.hasNext()) {
            assertEquals(throwsList1.next(), throwsList2.next());
        }

        compareCourseObjects(card1.getCourse(), card2.getCourse());
    }

    /**
     * compare two course objects to see if they are equal
     * @param course1 course object 1
     * @param course2 course object 2
     */
    private void compareCourseObjects(Course course1, Course course2) {
        assertEquals(course1.getCourseName(), course2.getCourseName());

        Iterator<Integer> parValues1 = course1.getParValues().iterator();
        Iterator<Integer> parValues2 = course2.getParValues().iterator();
        while(parValues1.hasNext()) {
            assertEquals(parValues1.next(), parValues2.next());
        }
    }


    /**
     * Save scorecard object with sendScoreCardToDatabase, then reads 
     * the last saved object from data class and compares the two scorecard objects
     * @throws URISyntaxException
     */
    @Test 
    public void testSaveAndReadScorecard() throws URISyntaxException {
        try {
            persistence.sendScorecardToDatabase(scorecard);
            data = persistence.readData();
            Scorecard scorecard2 = data.getData().get(data.getData().size()-1);
            compareScorecardObjects(scorecard, scorecard2);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }
}
