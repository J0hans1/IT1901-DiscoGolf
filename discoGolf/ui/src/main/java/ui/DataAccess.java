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
  String baseURL = "http://localhost:8080";
  DiscoGolfPersistence persistence = new DiscoGolfPersistence();

  public String postDefaultScorecard(){
    String responseBody = "";
    try {
      // Create a request
      HttpRequest request = HttpRequest.newBuilder()
      .uri(new URI(baseURL + "/post1"))
      .POST(BodyPublishers.ofString(""))
      .build();

      // Send the request and get the response
      final HttpResponse<String> response = HttpClient.newBuilder()
      .build()
      .send(request, HttpResponse.BodyHandlers.ofString());

      // save the response body as a string
      responseBody = response.body();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return responseBody;
  }


  /**
   * Forms an HTTP request that will post a scorecard to the database, via the restAPI
   * @param scorecard the scorecard to be posted. 
   * @throws IOException
   */
  public void RequestPostingScorecard(Scorecard scorecard) throws IOException {
    String scorecardString = persistence.scorecardToJson(scorecard);

    try {
      // Create a request 
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(baseURL + "/post2"))
      .header("Content-type", "application/json")
      .POST(BodyPublishers.ofString(scorecardString))
      .build();

      //send the request and get the response
      client.send(request, HttpResponse.BodyHandlers.ofString());
      } catch (Exception e) {
      e.printStackTrace();
      // throw new HttpException("Error posting scorecard");
    }
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
      .uri(new URI(baseURL + "/get"))
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
      // throw new HttpException("Error fetching database");
    }
    return data;
  }
}
