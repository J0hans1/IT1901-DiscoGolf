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
    private DiscoGolfPersistence persistence = new DiscoGolfPersistence();

    /**
     * returns all scorecards in the database.
     * @return all scorecards in the database.
     * @throws URISyntaxException
     * @throws IOException
     */
    public Data fetch() throws IOException, URISyntaxException {
        return persistence.readData();
    }

    /**
     * posts a scorecard to the database.
     * @param scorecard the scorecard to be posted.
     * @throws URISyntaxException
     * @throws IOException
     */
    public void save(ScorecardInterface s) throws IOException, URISyntaxException {
        persistence.sendScorecardToDatabase(s);
    }
}


