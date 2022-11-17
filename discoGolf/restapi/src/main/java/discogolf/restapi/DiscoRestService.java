package discogolf.restapi;

import org.springframework.stereotype.Service;


import java.io.IOException;
import java.net.URISyntaxException;

import discoGolf.core.Data;
import discoGolf.core.ScorecardInterface;
import discoGolf.json.DiscoGolfPersistence;

@Service
public class DiscoRestService {
    /**
     * lets the RestserverController manipulate and access the database.
     */
    private DiscoGolfPersistence persistence;

    private static final String TemporaryDatabasePath = "/discoGolfTest.json";

    /**
     * returns all scorecards in the database.
     * @return all scorecards in the database.
     * @throws URISyntaxException
     * @throws IOException
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
     * posts a scorecard to the database.
     * @param scorecard the scorecard to be posted.
     * @throws URISyntaxException
     * @throws IOException
     */
    public void addScorecard(ScorecardInterface s, boolean isTest) throws IOException, URISyntaxException {
        if (isTest) {
            persistence = new DiscoGolfPersistence(TemporaryDatabasePath);
        } else {
            persistence = new DiscoGolfPersistence();
        }
        persistence.sendScorecardToDatabase(s);
    }

    public void deleteDatabase() throws IOException, URISyntaxException {
        persistence = new DiscoGolfPersistence(TemporaryDatabasePath);
        persistence.deleteDatabase();
    }
}


