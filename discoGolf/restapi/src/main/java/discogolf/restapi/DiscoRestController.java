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

@RestController
public class DiscoRestController {

    static final String getAllURL = "/data";
    static final String addScorecardURL = "/add-scorecard";
    /**
     * Connects the RestserverService to the RestserverController.
     */
    @Autowired
    private DiscoRestService service;
    /**
     * Creates a new RestserverController object.
     * @return All scorecards in the database.
     * @throws URISyntaxException
     * @throws IOException
     */
    @GetMapping(getAllURL)
    public Data data() throws IOException, URISyntaxException {
        return service.data();
    }

   ///**
   // * Posts a scorecard to the database.
   // * @param scorecard the scorecard to be posted.
   // * @throws URISyntaxException
   // * @throws IOException
   // */
   //@PostMapping(value = "/post1")
   //public void submit() throws IOException, URISyntaxException {
   //    service.post();
   //}

    /**
     * Posts a scorecard to the database.
     * @param scorecard the scorecard to be posted.
     * @throws URISyntaxException
     * @throws IOException
     */
    @PutMapping(value = addScorecardURL)
    @ResponseStatus(HttpStatus.CREATED)
    public void submit(@RequestBody ScorecardInterface scorecard) throws IOException, URISyntaxException {
        service.post(scorecard);
    }

}