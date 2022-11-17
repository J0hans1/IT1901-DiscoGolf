package discoGolf.json;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

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
  private DiscoGolfPersistence persistenceTest;

  @BeforeEach
  public void setUp() {
    persistence = new DiscoGolfPersistence();
    persistenceTest = new DiscoGolfPersistence("/Test.json");
    course = new Course("Lade", new ArrayList<>(Arrays.asList(3, 4, 5, 4, 3, 5, 3, 5, 4)));
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
   * 
   * @param card1 scorecard object 1
   * @param card2 scorecard ibject 2
   */
  private void compareScorecardObjects(ScorecardInterface card1, ScorecardInterface card2) {
    assertEquals(card1.getPlayerName(), card2.getPlayerName());
    assertEquals(card1.getScore(), card2.getScore());
    assertEquals(card1.getBestHole(), card2.getBestHole());
    assertEquals(card1.getCourse().getCourseName(), card2.getCourse().getCourseName());

    Iterator<Hole> courseHoles1 = card1.getCourse().getCourseHoles().iterator();
    Iterator<Hole> courseHoles2 = card2.getCourse().getCourseHoles().iterator();
    while (courseHoles1.hasNext()) {
      assertEquals(courseHoles1.next().getPar(), courseHoles2.next().getPar());
    }


  }


  /**
   * Save scorecard object with sendScoreCardToDatabase, then reads the last saved object from data
   * class and compares the two scorecard objects
   * 
   * @throws URISyntaxException
   */
  @Test
  public void testSaveAndReadScorecard() throws URISyntaxException {
    try {
      persistence.sendScorecardToDatabase(scorecard);
      data = persistence.readData();
      ScorecardInterface scorecard2 = data.getData().get(data.getData().size() - 1);
      compareScorecardObjects(scorecard, scorecard2);
      deleteAddedObject();
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }

  /**
   * Send jsonString into JsonToData and test that data object is correct, Send the data object into
   * DataToJson and test that the string returned is correct. The two strings sent into JsonToData
   * and the one returned from DataToJson should not be equal, since some information are deleted in
   * the serialize process.
   * 
   * @throws URISyntaxException
   * @throws IOException
   */
  @Test
  public void testJsonToDataAndDataToJson() throws URISyntaxException {
    String dataString =
        """
            {"data":[{"playerName":"Mari","course":{"numberOfHoles":9,"courseName":"Lade",
            "courseHoles":[{"par":3,"holeThrows":3,"holeScore":0},{"par":4,"holeThrows":4,"holeScore":0}]
            ,"parValues":[3,4]},"bestHole":-2,"courseName":"Lade","score":4},{"playerName":"Fredrik","course":
            {"numberOfHoles":9,"courseName":"Lade","courseHoles":[{"par":3,"holeThrows":3,"holeScore":0},{"par":4,"holeThrows":4,
            "holeScore":0}],"parValues":[3,4]},"bestHole":-2,"courseName":"Lade","score":2}]}
            """;
    try {
      data = persistence.jsonToData(dataString);
      ArrayList<ScorecardInterface> dataList = data.getData();
      assertEquals("Mari", dataList.get(0).getPlayerName());
      assertEquals(4, dataList.get(0).getScore());
      assertEquals(-2, dataList.get(0).getBestHole());
      assertEquals("Fredrik", dataList.get(1).getPlayerName());
      assertEquals(2, dataList.get(1).getScore());
      assertEquals(-2, dataList.get(1).getBestHole());
      String newDataString =
          """
              {"data":[{"playerName":"Mari","score":4,"bestHole":-2,"course":{"courseName":"Lade","numberOfHoles":2,"parValues":[3,4]}},{"playerName":"Fredrik","score":2,"bestHole":-2,"course":{"courseName":"Lade","numberOfHoles":2,"parValues":[3,4]}}]}""";
      assertEquals(newDataString, persistence.dataToJson(data));
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }

  /**
   * Test that scorecardToJson returns the correct string of the scorecard
   * 
   * @throws URISyntaxException
   * @throws IOException
   */
  @Test
  public void testScorecardToJson() {
    try {
      String expString =
          """
              {"playerName":"Ulrik","score":40,"bestHole":0,"course":{"courseName":"Lade","numberOfHoles":9,"parValues":[3,4,5,4,3,5,3,5,4]}}""";
      assertEquals(expString, persistence.scorecardToJson(scorecard));
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }


  /**
   * Test the method getPathString creates a new file if it does not excist, also check that it
   * returns correct path
   * 
   * @throws URISyntaxException
   * @throws IOException
   */
  @Test
  public void testGetPathString() throws URISyntaxException {
    try {
      Path expectedPath = Paths.get(System.getProperty("user.home") + "/discoGolf.json");
      Path newPath = Paths.get(System.getProperty("user.home") + "/Test.json");
      assertEquals(expectedPath.toString(), persistence.getPathString());
      assertFalse(Files.exists(newPath));
      assertEquals(newPath.toString(), persistenceTest.getPathString());
      assertTrue(Files.exists(newPath));
      persistenceTest.deleteDatabase();
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }

  /**
   * Test that file is deleted when deleteDatabase() is called
   * 
   * @throws URISyntaxException
   * @throws IOException
   */
  @Test
  public void testDeleteDatabase() throws URISyntaxException {
    try {
      Path expectedPath = Paths.get(System.getProperty("user.home") + "/Test.json");
      persistenceTest.sendScorecardToDatabase(scorecard);
      assertTrue(Files.exists(expectedPath));
      persistenceTest.deleteDatabase();
      assertFalse(Files.exists(expectedPath));
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }

  /**
   * Make sure to delete the scorecard that testSaveAndReadScorecard() writes to database.json, so
   * it doesnt write a new scorecard everytime mvn test is runned.
   * 
   * @throws IOException
   * @throws URISyntaxException
   */
  private void deleteAddedObject() throws IOException, URISyntaxException {
    ArrayList<ScorecardInterface> scorecards = data.getData();
    scorecards.remove(scorecards.size() - 1);
    data.setData(scorecards);
    persistence.saveData(data);
  }
}

