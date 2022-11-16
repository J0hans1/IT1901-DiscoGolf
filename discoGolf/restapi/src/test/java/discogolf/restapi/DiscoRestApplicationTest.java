package discogolf.restapi;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import discoGolf.core.Course;
import discoGolf.core.Data;
import discoGolf.core.Scorecard;
import discoGolf.core.ScorecardInterface;
import discoGolf.json.DiscoGolfPersistence;


@WebMvcTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {DiscoRestController.class, DiscoRestService.class, DiscoRestApplication.class})
public class DiscoRestApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper;

    private static final String testAPIName = "nameUsedForTestingRestAPI";

    @BeforeEach
    public void setup() {
        DiscoGolfPersistence persistence = new DiscoGolfPersistence();
        mapper = persistence.getMapper();
    }

    public void AddScorecardToPersistence() {
        //initiate mockmvc
        DiscoGolfPersistence persistence = new DiscoGolfPersistence();
        //add scorecard to mock database
        try {

            ArrayList<Integer> pars = new ArrayList<>();
            pars.add(3);
            Course course = new Course("Dragvoll", pars);
            Scorecard scorecard = new Scorecard(course, testAPIName);
            persistence.sendScorecardToDatabase(scorecard);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
    @Test
    public void testGet() throws Exception {

        AddScorecardToPersistence();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(DiscoRestController.getAllURL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        Data data = mapper.readValue(content, Data.class);
        //assert not null
        assert(data != null);
        //assert that the scorecard is in the database
        boolean found = false;
        for (ScorecardInterface s : data.getData()) {
            if (s.getPlayerName().equals(testAPIName)) {
                found = true;
            }
        }
        assert(found);
    }

    @Test
    public void testAddAndGetScorecard() throws Exception {
        //create scorecard
        ArrayList<Integer> pars = new ArrayList<>();
        pars.add(3);
        Course course = new Course("Dragvoll", pars);
        Scorecard scorecard = new Scorecard(course, testAPIName);
        //post scorecard
        RequestBuilder request = MockMvcRequestBuilders.put(DiscoRestController.addScorecardURL)
                .contentType("application/json")
                .content(mapper.writeValueAsString(scorecard));
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated());
        //assert that the scorecard is in the database through get request
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(DiscoRestController.getAllURL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        Data data = mapper.readValue(content, Data.class);
        //assert not null
        assert(data != null);
        //assert that the scorecard is in the database
        boolean found = false;
        for (ScorecardInterface s : data.getData()) {
            if (s.getPlayerName().equals(testAPIName)) {
                found = true;
            }
        }
        assert(found);
        //delete scorecard from database using persistence
        removeScorecardFromPersistence(scorecard);

    }

    public void removeScorecardFromPersistence(ScorecardInterface scorecard) throws IOException, URISyntaxException {
        DiscoGolfPersistence persistence = new DiscoGolfPersistence();
        persistence.removeScorecardwithName(scorecard.getPlayerName());
    }
}