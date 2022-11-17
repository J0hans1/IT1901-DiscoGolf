package discoGolf.core;

import java.util.Comparator;

/**
 * LeadboardComparator that sort two ScorecardInterface objects based on total score, then best hole
 * and finally name.
 *
 * @author Jakob Opland and Ulrik Isdahl.
 * @version 1.0
 * @since 2022-10-12
 */
public class LeaderboardComparator implements Comparator<ScorecardInterface> {

  /**
   * Method comparing two Scorecard objects. The method first compare based on total score, then
   * best hole, and finally on name.
   *
   * @param card1 ScorecardInterface object to compare with card2.
   * @param card2 ScorecardInterface object to compare with card1.
   */
  @Override
  public int compare(ScorecardInterface card1, ScorecardInterface card2) {
    if (card1.getScore() == card2.getScore()) {
      if (card1.getBestHole() == card2.getBestHole()) {
        return card1.getPlayerName().compareTo(card2.getPlayerName());
      }
      return card1.getBestHole() - card2.getBestHole();
    }
    return card1.getScore() - card2.getScore();
  }
}
