package discoGolf.core;

/**
 * The states of this class keep track of a saved scorecard with only the necessary values for the
 * database.
 *
 * @see Course course is used to decide how many holes there are and the par values of those holes
 *      in the scorecard.
 * @author Jakob Opland
 * @version 1.2
 * @see Course course is used to decide how many holes there are and the par
 *      values of those holes in the scorecard
 * @since 2022-10-29
 */
public class ScorecardDAO implements ScorecardInterface {
    private String playerName;
    private Course course;
    private int bestHole;
    private int totalScore;

  /**
   * constructs a scorecard object for deserializing a json object.
   *
   * @param course is the course the player picked at the main menu.
   * @param playerName is the name of the player.
   * @param totalScore totalscore for the scorecard.
   * @param bestHole best hole for scorecard.
   */
  public ScorecardDAO(Course course, String playerName, int totalScore, int bestHole) {
    this.playerName = playerName;
    this.course = course;
    this.totalScore = totalScore;
    this.bestHole = bestHole;
  }

  /**
   * empty constructor used by jackson for deserializing purposes.
   */
  public ScorecardDAO() {

    }

  /**
   * the player name connectec to the scoreacard.
   *
   * @return the player name for the scorecard.
   */
  @Override
  public String getPlayerName() {
    return playerName;
  }

  /**
   * the course connected to the course.
   *
   * @return the Course object for the scorecard.
   */
  @Override
  public Course getCourse() {
    return course;
  }

  /**
   * the name for the course belonging to the scorecard.
   *
   * @return the course name.
   */
  @Override
  public String getCourseName() {
    return course.getCourseName();
  }

  /**
   * the total score for all holes combined.
   *
   * @return the total score for the scorecard object.
   */
  @Override
  public int getScore() {
    return totalScore;
  }

  /**
   * best Hole is the best individual score on the scorecard.
   *
   * @return the best hole score.
   */
  @Override
  public int getBestHole() {
    return bestHole;
  }

}
