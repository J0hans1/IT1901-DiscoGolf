package discoGolf.json;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import discoGolf.core.Course;
import discoGolf.core.Data;
import discoGolf.core.Hole;
import discoGolf.core.Scorecard;
import discoGolf.core.ScorecardInterface;

/**
 * J-unit test for persistence of discoGolf app
 * @author Markus Johansen
 * @version 1.0
 * @since 2022-10-08
 */
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
                scorecard.getCurrentHoleInstance().addThrow();
            }
            scorecard.nextHole();
        }
    }

    /**
     * compare two scorecard object to see if they are equal
     * @param card1 scorecard object 1 
     * @param card2 scorecard ibject 2
     */
    private void compareScorecardObjects(ScorecardInterface card1, ScorecardInterface card2) {
        assertEquals(card1.getPlayerName(), card2.getPlayerName());
        assertEquals(card1.getTotalScore(), card2.getTotalScore());
        assertEquals(card1.getBestHoleScore(), card2.getBestHoleScore());
        assertEquals(card1.getCourse().getCourseName(), card2.getCourse().getCourseName());

        Iterator<Hole> courseHoles1 = card1.getCourse().getCourseHoles().iterator();
        Iterator<Hole> courseHoles2 = card2.getCourse().getCourseHoles().iterator();
        while(courseHoles1.hasNext()) {
            assertEquals(courseHoles1.next().getPar(), courseHoles2.next().getPar());
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
            ScorecardInterface scorecard2 = data.getData().get(data.getData().size()-1);
            compareScorecardObjects(scorecard, scorecard2);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    /**
     * Make sure to delete the scorecard that testSaveAndReadScorecard() writes to 
     * database.json, so it doesnt write a new scorecard everytime mvn test is runned.
     * @throws IOException
     * @throws URISyntaxException
     */
    @AfterEach
    public void deleteAddedObject() throws IOException, URISyntaxException {
        ArrayList<ScorecardInterface> scorecards = data.getData();
        scorecards.remove(scorecards.size()-1);
        data.setData(scorecards);
        persistence.saveData(data);
    }
}

