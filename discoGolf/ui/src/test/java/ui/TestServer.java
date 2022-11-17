package ui;

import java.io.IOException;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import discoGolf.core.Course;
import discoGolf.core.Data;
import discoGolf.core.Scorecard;
import discoGolf.json.DiscoGolfPersistence;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import java.util.ArrayList;
import java.util.Arrays;



/**
 * Creates a WireMock server that can mock responses for the requests in DataAccess
 * @author Ulrik Isdahl and Billy Barret
 * @version 1.0
 * @since 2022-11-09
 */
public class TestServer{

  private WireMockConfiguration config;
  private WireMockServer wireMockServer;

  /**
   * Set up a WireMock server at the port 8080
   */
  public TestServer(){
    this.config = WireMockConfiguration.wireMockConfig().port(8080);
    this.wireMockServer = new WireMockServer(config.portNumber());
    this.wireMockServer.start();
    WireMock.configureFor("localhost", config.portNumber());
  }

  /**
   * Add 10 scorecards to the response that the WireMock server sends when requests with "/data" URL are requested
   */
  public void setup() throws IOException{
    DiscoGolfPersistence persistence = new DiscoGolfPersistence();
    Data responseData = new Data();

    //create 5 different scorecard objects with different player names all added to data object
    for (int i = 0; i < 5; i++) {
        Scorecard scorecard = new Scorecard(
            new Course("Lade", 
            new ArrayList<>(Arrays.asList(3,4,5,4,3,5,3,5,4))), 
            "Ulrik" + i
        );
        for (int j = 0; j < i; j++) {
          scorecard.getCurrentHoleInstance().addThrow(); //Increment score for each scorecard i times such that the totalScore is different per scorecard
        }
        responseData.add(scorecard);
    }

    for (int i = 0; i < 5; i++) {
        Scorecard scorecard = new Scorecard(
            new Course("Dragvoll", 
            new ArrayList<>(Arrays.asList(3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4))), 
            "Ulrik" + i
        );
        responseData.add(scorecard);
    }

    stubFor(get(urlEqualTo("/data"))
            .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader("Content-Type", "application/json")
                    .withBody(persistence.dataToJson(responseData))));
  }

  /**
   * Stops the WireMock server from running
   */
  public void closeServer(){
    this.wireMockServer.stop();
  }

}