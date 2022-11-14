package discogolf.restapi;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
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

    private static final String getAllURL = "/data";
    private static final String addScorecardURL = "/add-scorecard";

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
    public Data data() throws IOException, URISyntaxException {
        Data data = new Data();
        data.setData(service.data());
        return data;
    }

    /**
     * Posts a scorecard to the database.
     * @param scorecard the scorecard to be posted.
     * @throws URISyntaxException If the URI is not valid
     * @throws IOException If JSON/JAVA conversion fails
     */
    @PutMapping(value = addScorecardURL)
    @ResponseStatus(HttpStatus.CREATED)
    public void submit(@RequestBody ScorecardInterface scorecard) throws IOException, URISyntaxException {
        service.post(scorecard);
    }
}