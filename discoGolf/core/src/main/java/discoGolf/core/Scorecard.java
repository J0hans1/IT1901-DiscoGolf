package discoGolf.core;

import java.util.regex.Pattern;

/**
 * The states of this class keep track of the current stats of a player on the Course course.
 *
 * @see Course course is used to decide how many holes there are and the par values of those holes
 *      in the scorecard.
 * @author Billy Barret, Ulrik Isdahl, Jakob Opland and Markus Johansen.
 * @version 1.2
 * @since 2022-09-18
 */
public class Scorecard implements ScorecardInterface {
  private int currentHoleNumber;
  private String playerName;
  private Course course;

  /**
   * Constructs a scorecard object that vil be saved in the database.
   *
   * @param course is the course the player picked at the main menu.
   * @param playerName is the name of the player.
   */
  public Scorecard(Course course, String playerName) {
    validateMainPageCourse(course);
    validateMainPageName(playerName);
    this.playerName = playerName;
    this.course = course;
    this.currentHoleNumber = 1;
  }

  /**
   * empty construtor used by jackson for deserializing purposes.
   */
  public Scorecard() {

  }

  /**
   * the player name for this scorecard. 
   *
   * @return player name for the scorecard.
   */
  @Override
  public String getPlayerName() {
    return playerName;
  }

  /**
   * Course object for the scorecard.
   *
   * @return Course object for the scorecard.
   */
  @Override
  public Course getCourse() {
    return course;
  }

  /**
   * Course name for course in the scorecard.
   *
   * @return Course name for course in the scorecard.
   */
  @Override
  public String getCourseName() {
    return course.getCourseName();
  }

  /**
   * add the totalscores in getCourseHoles() together.
   *
   * @return the total score for the scorecard
   */
  @Override
  public int getScore() {
    return course.getCourseHoles().stream().mapToInt(hole -> hole.getHoleScore()).sum();
  }

  /**
   * iterate through getCourseHoles and findt the best hole score.
   *
   * @return the best individual hole score for the scorecard.
   */
  @Override
  public int getBestHole() {
    return this.getCourse().getCourseHoles().stream().mapToInt(p -> p.getHoleScore()).min()
        .getAsInt();
  }

  /**
   * use getHole to find current hole. 
   *
   * @return the current hole object the player is playing on.
   */
  public Hole getCurrentHoleInstance() {
    return course.getHole(currentHoleNumber);
  }

  /**
   * the current holenumber.
   *
   * @return the current hole number the player is playing on.
   */
  public int getCurrentHole() {
    return currentHoleNumber;
  }

  /**
   * uses getCurrentHoleInstance to find total throws.
   *
   * @return the current amount of throws the player has made on the current hole.
   */
  public int getCurrentHoleThrows() {
    return getCurrentHoleInstance().getHoleThrows();
  }

  /**
   * uses getCurrentHoleInstance() to get par.
   *
   * @return the par of the current hole.
   */
  public int getCurrentHolePar() {
    return getCurrentHoleInstance().getPar();
  }

  /**
   * use course to find its size.
   *
   * @return the size of the current course by getting the numberOfHoles field from course.
   */
  public int getCourseSize() {
    return course.getNumberOfHoles();
  }

  /**
   * adds one to the current hole number if the player is not on the last hole.
   *
   * @throws IllegalArgumentException if the player is at the last hole.
   */
  public void nextHole() throws IllegalArgumentException {
    if (getCurrentHole() == getCourseSize()) {
      throw new IllegalStateException("Can't go to nextHole because next hole doesn't exist");
    }
    currentHoleNumber++;
  }

  /**
   * removes one from the current hole number if the player is not on the first hole.
   *
   * @throws IllegalArgumentException if the player is at the first hole.
   */
  public void previousHole() throws IllegalArgumentException {
    if (getCurrentHole() == 1) {
      throw new IllegalStateException("Cannot go to a negative hole number");
    }
    currentHoleNumber--;
  }

  /**
   * Validates the course object selected by the user at the main menu.SSS.
   *
   * @param course Course object representing the chosen course in the main page.
   * @throws IllegalStateException Throws if no course is selected.
   */
  private void validateMainPageCourse(Course course) throws IllegalStateException {
    if (course == null) {
      throw new IllegalStateException("No course selected!");
    }
  }

  /**
   * Validates the name of the player inputed at the main menu.
   *
   * @param name String value from the value in the input field of the main page.
   * @throws IllegalArgumentException Throws if name doesnt fit the format "name1 name2 (optinal)
   *         name3(optinal)...".
   */
  private void validateMainPageName(String name) throws IllegalArgumentException {
    boolean regexCheck = Pattern.matches("[ÆØÅæøåa-zA-Z0-9]\s?(([ÆØÅæøåa-zA-Z0-9]+\s?)?)*", name);
    if (!regexCheck) {
      throw new IllegalArgumentException("Illegal input to name field");
    }
  }
}
