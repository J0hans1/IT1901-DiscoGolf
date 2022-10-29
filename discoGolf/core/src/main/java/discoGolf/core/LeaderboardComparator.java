package discoGolf.core;

import java.util.Comparator;

public class LeaderboardComparator implements Comparator<ScorecardInterface> {

  /**
   * Method comparing two Scorecard objects. The method first
   * compare based on total score, then best hole, and finally on name. 
   */
  @Override
  public int compare(ScorecardInterface card1, ScorecardInterface card2) {
    if (card1.getTotalScore() == card2.getTotalScore()) {
      if (card1.getBestHoleScore() == card2.getBestHoleScore()) {
        return card1.getPlayerName().compareTo(card2.getPlayerName());
      }
      return card1.getBestHoleScore() - card2.getBestHoleScore();
    }
    return card1.getTotalScore() - card2.getTotalScore();
  }
}
