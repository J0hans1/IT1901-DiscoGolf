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

/**
 * Tests for the DataAccess class
 * @author Ulrik Isdahl and Billy Barret
 * @version 1.0
 * @since 2022-11-09
 */
public class DataAccessTest {

    private TestServer server;  
    private DataAccess dataAccess;

  
    /**
     * Start the mock server and stub the expected response
     */
    @BeforeEach
    public void setUp() throws IOException {
        this.server = new TestServer();
        this.server.setup();
        this.dataAccess = new DataAccess();
    }

    /**
     * Check the corresponding names of the first five elements in the database
     */
    @Test
    public void testGetData() throws IOException {
        Data fetchedData = dataAccess.fetchDatabase();
        assertEquals(10, fetchedData.getData().size());
        for (int i = 0; i < 5; i++) {
            assertEquals("Ulrik" + i, fetchedData.getData().get(i).getPlayerName());
        }
    }

    /**
     * Stops the mock server from running after each test
     */
    @AfterEach
    public void stopWireMockServer() {
      server.closeServer();
    }
  
}
