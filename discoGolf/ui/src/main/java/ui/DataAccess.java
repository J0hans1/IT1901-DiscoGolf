package ui;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

import discoGolf.core.Scorecard;
import discoGolf.json.DiscoGolfPersistence;

// import org.apache.http.HttpException;

/**
 * Class for accessing the database.
 * Represents the access layer of the application.
 */
public class DataAccess {
  String baseURL = "http://localhost:8080";
  DiscoGolfPersistence persistence = new DiscoGolfPersistence();

  /**
   * Forms an HTTP request that will post a scorecard to the database, via the restAPI
   * @param scorecard the scorecard to be posted. 
   */
  public String RequestPostingScorecard(Scorecard scorecard) {
    String responseBody = "";
    String scorecardString = persistence.scorecardToJson(scorecard);

    try {
      // Create a request
      HttpRequest request = HttpRequest.newBuilder()
      .uri(new URI(baseURL + "/post2/" + scorecardString))
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
      // throw new HttpException("Error posting scorecard");
    }
    return response.body();
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
