package discogolf.restapi;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import discoGolf.core.Data;
import discoGolf.core.ScorecardInterface;

/**
 * The DiscoRestController class is the RestController for the RestAPI.
 * It is responsible for handling the requests from the client.
 * @author Markus Johansen and Billy Barret
 * @version 1.0
 * @since 2022-10-13
 * @see DiscoRestService
 * @see DiscoRestApplication
 */
@RestController
public class DiscoRestController {

    static final String getAllURL = "/data";
    static final String addScorecardURL = "/add-scorecard";
    static final String deleteDatabaseURL = "/delete-database";
    static final String getAllURLTest = "/test" + getAllURL;
    static final String addScorecardURLTest = "/test" + addScorecardURL;
    /**
     * Connects the RestserverService to the RestserverController.
     */
    @Autowired
    private DiscoRestService service;

    /**
     * Get all scorecards from the database as a Data object
     * @return Data object containing scorecards.
     * @throws URISyntaxException If the URI is not valid
     * @throws IOException If JSON/JAVA conversion fails
     */
    @GetMapping(getAllURL)
    public Data getData() throws IOException, URISyntaxException {
        return service.getData(false);
    }

      /**
     * Creates a new RestserverController object.
     * @return All scorecards in the database.
     * @throws URISyntaxException
     * @throws IOException
     */
    @GetMapping(getAllURLTest)
    public Data getDataTest() throws IOException, URISyntaxException {
        return service.getData(true);
    }

    /**
     * Posts a scorecard to the database.
     * @param scorecard the scorecard to be posted.
     * @return true if the scorecard was posted successfully.
     * @throws URISyntaxException
     * @throws IOException
     */
    @PutMapping(addScorecardURL)
    @ResponseStatus(HttpStatus.CREATED)
    public boolean addScorecard(@RequestBody ScorecardInterface scorecard) throws IOException, URISyntaxException {
        service.addScorecard(scorecard, false);
        return true;
    }

    /**
     * Posts a scorecard to the database.
     * @param scorecard the scorecard to be posted.
     * @throws URISyntaxException
     * @throws IOException
     */
    @PutMapping(addScorecardURLTest)
    @ResponseStatus(HttpStatus.CREATED)
    public boolean addScorecardTest(@RequestBody ScorecardInterface scorecard) throws IOException, URISyntaxException {
        service.addScorecard(scorecard, true);
        return true;
    }

    @DeleteMapping(deleteDatabaseURL)
    @ResponseStatus(HttpStatus.OK)
    public void deleteDatabase() throws IOException, URISyntaxException {
        service.deleteDatabase();
    }
}