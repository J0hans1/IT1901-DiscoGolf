package discogolf.restapi;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import discoGolf.core.Course;
import discoGolf.core.Data;
import discoGolf.core.Scorecard;
import discoGolf.json.DiscoGolfPersistence;


@WebMvcTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {DiscoRestController.class, DiscoRestService.class, DiscoRestApplication.class})
public class DiscoRestApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper;

    private static final String testAPIName = "nameUsedForTestingRestAPI";

    private static final String getAllURLTest = DiscoRestController.getAllURLTest;
    private static final String addScorecardURLTest = DiscoRestController.addScorecardURLTest;

    @BeforeEach
    public void setup() {
        DiscoGolfPersistence persistence = new DiscoGolfPersistence(); // Persistence created to get the mapper
        mapper = persistence.getMapper();
    }

    @Test
    public void testAddScorecard() throws Exception {
        //create scorecard
        ArrayList<Integer> pars = new ArrayList<>();
        pars.add(3);
        Course course = new Course("Dragvoll", pars);
        Scorecard scorecard = new Scorecard(course, testAPIName);
        //post scorecard
        RequestBuilder request = MockMvcRequestBuilders.put(addScorecardURLTest)
                .contentType("application/json")
                .content(mapper.writeValueAsString(scorecard));
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testGet() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(getAllURLTest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        Data data = mapper.readValue(content, Data.class);
        //assert not null
        assert(data != null);
    }

    @Test
    public void testAddAndGetScorecard() throws Exception {
        ArrayList<Integer> pars = new ArrayList<>();
        pars.add(3);
        Course course = new Course("Dragvoll", pars);
        Scorecard scorecard = new Scorecard(course, testAPIName);
        //post scorecard
        RequestBuilder request = MockMvcRequestBuilders.put(addScorecardURLTest)
                .contentType("application/json")
                .content(mapper.writeValueAsString(scorecard));
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated());
        //get scorecard
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(getAllURLTest))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();

        Data data = mapper.readValue(content, Data.class);

        //assert not null
        assert(data != null);

        //assert that the scorecard with the name testAPIName is in the data
        assert(data.getData().stream().anyMatch(s -> s.getPlayerName().equals(testAPIName)));
    }

    @AfterEach
    public void tearDown() throws Exception {
        //delete scorecard
        RequestBuilder request = MockMvcRequestBuilders.delete(DiscoRestController.deleteDatabaseURL)
                .contentType("application/json")
                .content(mapper.writeValueAsString(testAPIName));
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}