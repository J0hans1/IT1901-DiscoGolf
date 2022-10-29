package discoGolf.core;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * The Course class represents a frisbee golf course with int numberOfHoles amount of holes
 * @author Billy Barret
 * @version 1.0
 * @since 2022-09-21
 */
public class Course {

    private int numberOfHoles;
    private final String courseName;
    private final ArrayList<Hole> courseHoles;

    /**
     * Create a disc golf course.
     * 
     * @param courseName - is the name of the course
     * @param parValues  - is an array of integers that represents the par value for
     *                   the hole with number == to its array index
     */
    public Course(String courseName, ArrayList<Integer> parValues) {
        validateCourseName(courseName);
        validateParValuesList(parValues);
        this.courseName = courseName;
        this.courseHoles = parValues.stream()
                            .map(p -> new Hole(p))
                            .collect(Collectors.toCollection(ArrayList::new));
        this.numberOfHoles = this.courseHoles.size();
    }

    /**
     * Validates the parValues list. Throws IllegalArgumentExeption if not valid
     * @param allParValues a list of par values which should be between 2 and 7
     * and not empty
     */
    private void validateParValuesList(final ArrayList<Integer> allParValues) {
        if (allParValues.isEmpty()) {
            throw new IllegalArgumentException("Not a valid list");
        }
        for (Integer par : allParValues) {
            if (par < 2 || par > 7) {
                throw new IllegalArgumentException("Not a valid list");
            }
        }
    }
    
    /**
     * @return String courseName - containing the name of the course
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @return ArrayList<Integer> parValues - containing the par values for each
     *         hole
     */
    public ArrayList<Hole> getCourseHoles() {
        return courseHoles;
    }

     /**
     * @return ArrayList<Integer> parValues - containing the par values for each
     *         hole
     */
    public ArrayList<Integer> getParValues() {
        return courseHoles.stream()
        .map(p -> p.getPar())
        .collect(Collectors.toCollection(ArrayList::new));
    }


    /**
     * Links up the hole/par pair.
     * 
     * @param hole - the hole number
     * @param par  - the par value
     */
    public void setHole(Hole hole, int holeNumber) {
        if (courseHoles.size() < holeNumber) {
            throw new IllegalArgumentException("The hole does not excist");
        }
        this.courseHoles.add(holeNumber - 1, hole);
    }

    /**
     * Returns the par value at a specific hole/index of the array.
     * @param holeNumber - the hole number
     * @return int - the par value assigned to the specific hole
     */
    public Hole getHole(int holeNumber) {
        if (courseHoles.size() <= holeNumber) {
            throw new IllegalArgumentException("The course does not have that many holes");
        }
        return courseHoles.get(holeNumber-1);
    }

    /**
     * Returns the par value at a specific hole/index of the array.
     * @param hole - the hole 
     * @return int - the par value assigned to the specific hole
     */
    public int getHoleNumber(Hole hole) {
        if (!courseHoles.contains(hole)) {
            throw new IllegalArgumentException("The hole does not excist in the course");
        }
        return courseHoles.indexOf(hole) + 1;
    }

    /**
     * Get number of holes at site.
     * 
     * @return int
     */
    public int getNumberOfHoles() {
        return numberOfHoles;
    }

    /**
     * @param name String value from the value in the input field of the main page
     * @throws IllegalArgumentException Throws if name doesnt fit the format "name1
     *                                  name2 (optinal) name3(optinal)..."
     */
    private void validateCourseName(final String name) {
        boolean regexCheck = Pattern.matches("[ÆØÅæøåa-zA-Z0-9]\s?(([ÆØÅæøåa-zA-Z0-9]+\s?)?)*", name);
        if (!regexCheck) {
            throw new IllegalArgumentException("Illegal input to name field");
        }
    }

    @Override
    public final String toString() {
        return "Course [numberOfHoles=" + numberOfHoles + ", courseName=" + courseName +  " ]";
    }
}
