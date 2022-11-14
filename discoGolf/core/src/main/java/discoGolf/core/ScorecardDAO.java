package discoGolf.core;

/**
 * The states of this class keep track of a saved scorecard with only the necessary values for the database
 * @see Course course is used to decide how many holes there are and the par values of those holes in the scorecard
 * @author Jakob Opland
 * @version 1.2
 * @since 2022-10-29
 */
public class ScorecardDAO implements ScorecardInterface {
  private String playerName;
  private Course course;
  private int bestHole;
  private int totalScore;

  /**
   * constructs a scorecard object for deserializing a json object
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

  /**
   * empty constructor used by jackson for deserializing purposes
   */
  public ScorecardDAO() {

  }

  /**
   * @return the player name for the scorecard
   */
  @Override
  public String getPlayerName() {
    return playerName;
  }

  /**
   * @return the Course object for the scorecard
   */
  @Override
  public Course getCourse() {
    return course;
  }

  /**
   * @return the course name for the Course in the scorecard
   */
  @Override
  public String getCourseName() {
    return course.getCourseName();
  }

  /**
   * @return the total score for the scorecard object
   */
  @Override
  public int getScore() {
    return totalScore;
  }

  /**
   * @return the best individual hole score in the scorecard
   */
  @Override
  public int getBestHole() {
    return bestHole;
  }
  
}
