package discogolf.restapi;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import discoGolf.core.Scorecard;

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
    public ArrayList<Scorecard> data() throws IOException, URISyntaxException {
        return service.data();
    }

    /**
     * Posts a scorecard to the database.
     * @param scorecard the scorecard to be posted.
     * @throws URISyntaxException
     * @throws IOException
     */
    @PostMapping(value = "/post")
    public void submit() throws IOException, URISyntaxException {
        service.post();
    }
}