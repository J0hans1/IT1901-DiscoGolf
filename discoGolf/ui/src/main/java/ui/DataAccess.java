package ui;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

import discoGolf.core.Scorecard;
import discoGolf.json.DiscoGolfPersistence;

// import org.apache.http.HttpException;

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
      HttpRequest request = HttpRequest.newBuilder()
      .uri(new URI(baseURL + "/post2/" + scorecardString))
      .POST(BodyPublishers.ofString(""))
      .build();

      final HttpResponse<String> response = HttpClient.newBuilder()
      .build()
      .send(request, HttpResponse.BodyHandlers.ofString());

      responseBody = response.body();
    } catch (Exception e) {
      e.printStackTrace();
      // throw new HttpException("Error posting scorecard");
    }

    return response.body();
  }
}
