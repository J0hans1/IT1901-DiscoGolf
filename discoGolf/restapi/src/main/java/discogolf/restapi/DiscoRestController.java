package discogolf.restapi;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import discoGolf.core.Data;
import discoGolf.core.ScorecardInterface;

@RestController
public class DiscoRestController {

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
    @GetMapping("/get")
    public Data data() throws IOException, URISyntaxException {
        Data data = new Data();
        data.setData(service.data());
        return data;
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
    @RequestMapping(value = "/post2")
    @ResponseStatus(HttpStatus.CREATED)
    public void submit(@RequestBody ScorecardInterface scorecard) throws IOException, URISyntaxException {
        service.post(scorecard);
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test(@RequestBody ScorecardInterface s) {
        return "Hello " + s.getPlayerName();
    }

}