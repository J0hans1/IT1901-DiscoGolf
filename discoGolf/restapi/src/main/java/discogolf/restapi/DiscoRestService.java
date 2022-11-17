package discogolf.restapi;

import discoGolf.core.Data;
import discoGolf.core.ScorecardInterface;
import discoGolf.json.DiscoGolfPersistence;
import java.io.IOException;
import java.net.URISyntaxException;
import org.springframework.stereotype.Service;

/**
 * The DiscoRestService class is the Service for the RestAPI. 
 * It is responsible for performing the requests from the client and manipulating the database.
 *
 * @author Markus Johansen and Billy Barret.
 * @version 1.0.
 * @since 2022-10-13.
 * @see DiscoRestController
 */
@Service
public class DiscoRestService {

  private DiscoGolfPersistence persistence;
  private static final String TemporaryDatabasePath = "/discoGolfTest.json";

  /**
   * returns all scorecards in the database as an arrayList, by reading the database file.
   *
   * @return all scorecards in the database.
   * @throws URISyntaxException if the URI is not valid.
   * @throws IOException if JSON/JAVA conversion fails.
   */
  public Data getData(boolean isTest) throws IOException, URISyntaxException {
    if (isTest) {
      persistence = new DiscoGolfPersistence(TemporaryDatabasePath);
    } else {
      persistence = new DiscoGolfPersistence();
    }
    return persistence.readData();
  }

  /**
   * Posts a scorecard to the database.
   *
   * @param s the scorecard to be posted.
   * @throws URISyntaxException if the URI is not valid.
   * @throws IOException if JSON/JAVA conversion fails.
   */
  public void addScorecard(ScorecardInterface s, boolean isTest)
      throws IOException, URISyntaxException {
    if (isTest) {
      persistence = new DiscoGolfPersistence(TemporaryDatabasePath);
    } else {
      persistence = new DiscoGolfPersistence();
    }
    persistence.sendScorecardToDatabase(s);
  }

  /**
   * Deletes the database.
   *
   * @throws URISyntaxException if the URI is not valid.
   * @throws IOException if JSON/JAVA conversion fails.
   */
  public void deleteDatabase() throws IOException, URISyntaxException {
    persistence = new DiscoGolfPersistence(TemporaryDatabasePath);
    persistence.deleteDatabase();
  }
}


