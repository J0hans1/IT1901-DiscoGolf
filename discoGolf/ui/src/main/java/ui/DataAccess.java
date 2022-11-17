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

// import org.apache.http.HttpException;

/**
 * Transforms Data object contents to json parsable elements
 * @author Markus Johansen, Billy Barret og Ulrik Isdahl
 * @version 1.0
 * @since 2022-10-03
 */
public class DataAccess {
  private static final DiscoGolfPersistence persistence = new DiscoGolfPersistence();
  private static final String baseURL = "http://localhost:8080";
  private static final String addScorecardURL = baseURL + "/add-scorecard";
  private static final String getAllURL = baseURL + "/data";

  /**
   * Forms an HTTP request that will post a scorecard to the database, via the restAPI
   * @param scorecard the scorecard to be posted.  TODO: modify to post in comment
   * @throws IOException
   */
  public String RequestPostingScorecard(Scorecard scorecard) throws IOException {
    String scorecardString = persistence.scorecardToJson(scorecard);
    String responseString = "";

    try {
      // Create a request 
      HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(addScorecardURL))
      .header("Content-type", "application/json")
      .PUT(BodyPublishers.ofString(scorecardString))
      .build();

      //send the request and get the response
      final HttpResponse<String> response = HttpClient.newBuilder()
      .build()
      .send(request, HttpResponse.BodyHandlers.ofString());

      //save the response body in a string
      responseString = "\n\nResponse: " + response.body() + "\n\n";
    } catch (Exception e) {
        e.printStackTrace();
    }
    return responseString;
  }

  /**
   * Forms an HTTP request that will get all scorecards from the database, via the restAPI
   * @return all scorecards in the database.
   */
  public Data fetchDatabase(){
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
    }
    return data;
  }
  
}