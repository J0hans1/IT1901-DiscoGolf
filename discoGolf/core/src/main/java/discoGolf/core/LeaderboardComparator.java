package discoGolf.core;

import java.util.Comparator;

public class LeaderboardComparator implements Comparator<Scorecard> {

  @Override
  public int compare(Scorecard card1, Scorecard card2) {
    if (card1.getTotalScore() == card2.getTotalScore()) {
      int card1BestHole = 1;
      int card2BestHole = 2;
    
      return card1BestHole - card2BestHole;
    }
    return card1.getTotalScore() - card2.getTotalScore();
  }  
}
