package discogolf.restapi;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import discoGolf.core.ScorecardInterface;
import discoGolf.json.DiscoGolfPersistence;

/**
 * The DiscoRestService class is the Service for the RestAPI.
 * It is responsible for performing the requests from the client and manipulating the database.
 * @author Markus Johansen and Billy Barret
 * @version 1.0
 * @since 2022-10-13
 * @see DiscoRestController
 */
@Service
public class DiscoRestService {
    private DiscoGolfPersistence persistence = new DiscoGolfPersistence();

    /**
     * returns all scorecards in the database as an arrayList, by reading the database file.
     * @return all scorecards in the database.
     * @throws URISyntaxException if the URI is not valid.
     * @throws IOException if JSON/JAVA conversion fails.
     */
    public ArrayList<ScorecardInterface> data() throws IOException, URISyntaxException {
        return persistence.readData().getData();
    }

    /**
     * Posts a scorecard to the database.
     * @param s the scorecard to be posted.
     * @throws URISyntaxException if the URI is not valid.
     * @throws IOException if JSON/JAVA conversion fails.
     */
    public void post(ScorecardInterface s) throws IOException, URISyntaxException {
        persistence.sendScorecardToDatabase(s);
    }
}


