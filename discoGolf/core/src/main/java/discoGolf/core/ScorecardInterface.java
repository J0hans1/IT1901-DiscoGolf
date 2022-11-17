package discoGolf.core;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Interface for a scorecard object with getters for the necessary fields that are saved and stored
 * in the database.
 *
 * @author Jakob Opland
 * @version 1.2
 * @since 2022-10-29
 */

@JsonDeserialize(as = ScorecardDAO.class)
public interface ScorecardInterface {

  /**
   * the player name for the scorecard.
   *
   * @return the player name.
   */
  public String getPlayerName();

  /**
   * the course object for the scorecard.
   *
   * @return the Course object for the scorecard.
   */
  public Course getCourse();

  /**
   * the course name for the Course in the scorecard.
   *
   * @return the course name for the Course in the scorecard.
   */
  public String getCourseName();

  /**
   * the total score for the scorecard object.
   *
   * @return the total score for the scorecard object.
   */
  public int getScore();

  /**
   * the best individual hole score for the scorecard.
   *
   * @return the best individual hole score for the scorecard.
   */
  public int getBestHole();

}
