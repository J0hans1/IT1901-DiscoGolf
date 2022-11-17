package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import discoGolf.core.Course;
import discoGolf.core.Data;
import discoGolf.core.Scorecard;
import discoGolf.json.DiscoGolfPersistence;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class DataAccessTest {

    private WireMockConfiguration config;
    private WireMockServer wireMockServer;
  
    private DataAccess dataAccess;

  
    @BeforeEach
    public void setUp() throws IOException {
        config = WireMockConfiguration.wireMockConfig().port(8080);
        wireMockServer = new WireMockServer(config.portNumber());
        wireMockServer.start();
        WireMock.configureFor("localhost", config.portNumber());
        dataAccess = new DataAccess();
    }    

    @Test
    public void testGetData() throws IOException {
        DiscoGolfPersistence persistence = new DiscoGolfPersistence();
        Data responseData = new Data();
        //create 5 different scorecard objects with different player names all added to data object
        for (int i = 0; i < 5; i++) {
            Scorecard scorecard = new Scorecard(
                new Course("Lade", 
                new ArrayList<>(Arrays.asList(3,4,5,4,3,5,3,5,4))), 
                "Ulrik" + i
            );

            responseData.add(scorecard);
        }
        stubFor(get(urlEqualTo("/data"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(persistence.dataToJson(responseData))));
        Data fetchedData = dataAccess.fetchDatabase();
        System.out.println(persistence.dataToJson(responseData));
        assertEquals(5, fetchedData.getData().size());
        for (int i = 0; i < 5; i++) {
            assertEquals("Ulrik" + i, fetchedData.getData().get(i).getPlayerName());
        }
    }

    @AfterEach
    public void stopWireMockServer() {
      wireMockServer.stop();
    }
  
}
