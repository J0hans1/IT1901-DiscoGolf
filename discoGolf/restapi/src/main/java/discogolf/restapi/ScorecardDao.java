package discogolf.restapi;

import java.util.ArrayList;

import discoGolf.core.Scorecard;

public interface ScorecardDao {

    int addScorecard(String username, int score);

    default int addScorecard(int score) {
        return addScorecard("default", score);
    }

    // public int getScorecard(String username);

    // public void deleteScore(String username);

    // public String getAllScorecards();
}
