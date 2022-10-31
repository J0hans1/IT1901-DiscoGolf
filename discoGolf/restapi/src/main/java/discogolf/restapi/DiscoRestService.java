package discogolf.restapi;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import discoGolf.core.Course;
import discoGolf.core.Scorecard;
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
    public ArrayList<Scorecard> data() throws IOException, URISyntaxException {
        return persistence.readData().getData();
    }

    /**
     * posts a scorecard to the database.
     * @param scorecard the scorecard to be posted.
     * @throws URISyntaxException
     * @throws IOException
     */
    public void post(Scorecard s) throws IOException, URISyntaxException {
        persistence.sendScorecardToDatabase(s);
    }

    /**
     * posts a scorecard to the database.
     * @param scorecard the scorecard to be posted.
     * @throws URISyntaxException
     * @throws IOException
     */
    public void post() throws IOException, URISyntaxException {
        ArrayList<Integer> pars = new ArrayList<>();
        pars.add(3);
        Course course = new Course("øya Golfklubb", pars);
        Scorecard s = new Scorecard(course, "Ulrik Iversen");
        persistence.sendScorecardToDatabase(s);
    }
}


