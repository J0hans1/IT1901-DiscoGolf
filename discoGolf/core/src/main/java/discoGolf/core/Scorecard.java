package discoGolf.core;

import java.util.regex.Pattern;

/**
 * The states of this class keep track of the current stats of a player on the Course course
 * @see Course course is used to decide how many holes there are and the par values of those holes in the scorecard
 * @author Billy Barret, Ulrik Isdahl and @Jakob Opland
 * @version 1.2
 * @since 2022-09-18
 */
public class Scorecard implements ScorecardInterface {
    private int currentHoleNumber; 
    private final String playerName;
    private final Course course;

    /**
     * - constructs a scorecard object that vil be saved in the database
     * @param course     is the course the player picked at the main menu
     * @param playerName is the name of the player
     */
    public Scorecard(Course course, String playerName) {
        validateMainPageCourse(course);
        validateMainPageName(playerName);
        this.playerName = playerName;
        this.course = course;
        this.currentHoleNumber = 1;
    }

    /**
     * @return player name for the scorecard
     */
    @Override
    public String getPlayerName() {
        return playerName;
    }

    /**
     * @return Course object for the scorecard
     */
    @Override
    public Course getCourse() {
        return course;
    }

    /**
     * @return Course name for course in the scorecard
     */
    @Override
    public String getCourseName() {
        return course.getCourseName();
    }

    /**
     * @return the total score for the scorecard by adding all the holes score together.
     */
    @Override
    public int getTotalScore() {
        return course.getCourseHoles().stream().mapToInt(hole -> hole.getHoleScore()).sum();
    }

    /**
     * @return the best individual hole score for the scorecard
     */
    @Override
    public int getBestHoleScore() {
        return this.getCourse().getCourseHoles().stream().mapToInt(p ->  p.getHoleScore()).min().getAsInt();
    }

    /**
     * @return the current hole object the player is playing on.
     */
    public Hole getCurrentHoleInstance() {
        return course.getHole(currentHoleNumber);
    }

    /**
     * @return the current hole number the player is playing on.
     */
    public int getCurrentHole() {
        return currentHoleNumber;
    }

    /**
     * @return the current amount of throws the player has made on the current hole.
     */
    public int getCurrentHoleThrows() {
        return getCurrentHoleInstance().getHoleThrows();
    }

    /**
     * @return the par of the current hole
     */
    public int getCurrentHolePar() {
        return getCurrentHoleInstance().getPar();
    }
    
    /**
     * @return the size of the current course by getting the 
     * numberOfHoles field from course.
     */
    public int getCourseSize() {
        return course.getNumberOfHoles();
    }

    /**
     * adds one to the current hole number if the player is not on the last hole.
     * @throws IllegalArgumentException if the player is at the last hole
     */
    public void nextHole() {
        if (getCurrentHole() == getCourseSize()) {
            throw new IllegalStateException("Can't go to nextHole because next hole doesn't exist");
        }
        currentHoleNumber++;
    }

    /**
     * removes one from the current hole number if the player is not on the first hole.
     * @throws IllegalArgumentException if the player is at the first hole
     */
    public void previousHole() {
        if (getCurrentHole() == 1) {
            throw new IllegalStateException("Cannot go to a negative hole number");
        }
        currentHoleNumber--;
    }

    /**
     * @param course Course object representing the chosen course in the main page
     * @throws IllegalStateException Throws if no course is selected
     */
    private void validateMainPageCourse(Course course) {
        if (course == null) {
            throw new IllegalStateException("No course selected!");
        }
    }

    /**
     * @param name String value from the value in the input field of the main page
     * @throws IllegalArgumentException Throws if name doesnt fit the format "name1
     *                                  name2 (optinal) name3(optinal)..."
     */
    private void validateMainPageName(String name) {
        boolean regexCheck = Pattern.matches("[ÆØÅæøåa-zA-Z0-9]\s?(([ÆØÅæøåa-zA-Z0-9]+\s?)?)*", name);
        if (!regexCheck) {
            throw new IllegalArgumentException("Illegal input to name field");
        }
    }
}
