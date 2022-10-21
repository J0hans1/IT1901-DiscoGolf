package discoGolf.core;

import java.util.ArrayList;
import java.util.Comparator;

public class LeaderboardComparator implements Comparator<Scorecard> {

  /**
   * Method comparing two Scorecard objects. The method first
   * compare based on total score, then best hole, and finally on name. 
   */
  @Override
  public int compare(Scorecard card1, Scorecard card2) {
    if (card1.getTotalScore() == card2.getTotalScore()) {
      if (bestHole(card1) == bestHole(card2)) {
        return card1.getPlayerName().compareTo(card2.getPlayerName());
      }
      return bestHole(card1) - bestHole(card2);
    }
    return card1.getTotalScore() - card2.getTotalScore();
  }
  
  /**
   * Method that finds the best hole on a Scorecard
   * @param scorecard the scorecard that is analyzed
   * @return the best hole in the Scorecard object
   */
  private int bestHole(Scorecard scorecard) {
    ArrayList<Integer> throwsList = scorecard.getThrowsList();
    ArrayList<Integer> parValues = scorecard.getCourse().getParValues();
    int bestHole = throwsList.get(0) - parValues.get(0);

    for (int i = 0; i < throwsList.size(); i++) {
      int holeSocre = throwsList.get(i) - parValues.get(i) ;  
      if (holeSocre < bestHole) {
        bestHole = holeSocre;
      }
    }
    return bestHole;
  }
}
