package ui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

import discoGolf.core.Data;
import discoGolf.core.Scorecard;
import discoGolf.json.DiscoGolfPersistence;

/**
 * Is the ClientSide access to the RestAPI.
 * It is responsible for sending requests to the RestAPI and receiving responses.
 * @author Markus Johansen, Billy Barret og Ulrik Isdahl
 * @version 1.0
 * @since 2022-10-03
 */
public class DataAccess {
  private static final String baseURL = "http://localhost:8080";
  private static final DiscoGolfPersistence persistence = new DiscoGolfPersistence();
  private static final String addScorecardURL = baseURL + "/add-scorecard";
  private static final String getAllURL = baseURL + "/data";

  /**
   * Sends an HTTP-POST request that will post a scorecard to the database, via the restAPI
   * @param scorecard the scorecard to be posted.
   * @throws IOException if conversion from Java to JSON fails.
   */
  public void RequestPostingScorecard(Scorecard scorecard) throws IOException {
    String scorecardString = persistence.scorecardToJson(scorecard);

    try {
      // Create a request 
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(addScorecardURL))
      .header("Content-type", "application/json")
      .PUT(BodyPublishers.ofString(scorecardString))
      .build();

      //send the request and get the response
      client.send(request, HttpResponse.BodyHandlers.ofString());
      } catch (Exception e) {
        e.printStackTrace();
        //TODO: handle exception
    }
  }


  /**
   * Forms an HTTP-GET request that will get all scorecards from the database, via the restAPI
   * @return Data object containing all scorecards in the database.
   * @throws IOException if conversion from JSON to Java fails.
   */
  public Data fetchDatabase() throws IOException {
    Data data = new Data();
    try {
      // Create a request
      HttpRequest request = HttpRequest.newBuilder()
      .uri(new URI(getAllURL))
      .GET()
      .build();

      // Send the request and get the response
      final HttpResponse<String> response = HttpClient.newBuilder()
      .build()
      .send(request, HttpResponse.BodyHandlers.ofString());

      // Parse the response body into a Data object
      data = persistence.jsonToData(response.body());
    } catch (Exception e) {
      e.printStackTrace();
      //TODO: handle exception
    }
    return data;
  }
}



