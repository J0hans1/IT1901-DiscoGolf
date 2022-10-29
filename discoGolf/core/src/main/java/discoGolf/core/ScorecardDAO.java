package discoGolf.core;

public class ScorecardDAO implements ScorecardInterface {
  private final String playerName;
  private final Course course;
  private final int bestHole;
  private final int totalScore;

  /**
     * - constructs a scorecard object for deserializing a json object
     * 
     * @param course     is the course the player picked at the main menu
     * @param playerName is the name of the player
     * @param totalScore totalscore for the scorecard
     * @param throwsList all the thrwos+par for each hole
     */
    public ScorecardDAO(Course course, String playerName, int totalScore, int bestHole) {
      this.playerName = playerName;
      this.course = course;
      this.totalScore = totalScore;
      this.bestHole = bestHole;
  }

  @Override
  public String getPlayerName() {
    return playerName;
  }

  @Override
  public Course getCourse() {
    return course;
  }

  @Override
  public String getCourseName() {
    return course.getCourseName();
  }

  @Override
  public int getTotalScore() {
    return totalScore;
  }

  @Override
  public int getBestHoleScore() {
    return bestHole;
  }
  
}
