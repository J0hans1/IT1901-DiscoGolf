package discoGolf.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * The Course class represents a frisbee golf course with int numberOfHoles amount of holes
 * @author Billy Barret
 * @version 1.0
 * @since 2022-09-21
 */
public class Course {

    private int numberOfHoles;
    private final String courseName;
    private HashMap<Integer, Integer> parForHoles;
    private final ArrayList<Integer> parValues;
    private final int highestPossibleParValue = 7;

    /**
     * Create a disc golf course.
     * 
     * @param courseName - is the name of the course
     * @param parValues  - is an array of integers that represents the par value for
     *                   the hole with number == to its array index
     */
    public Course(final String courseName, final ArrayList<Integer> parValues) {
        validateCourseName(courseName);
        validateParValuesList(parValues);
        this.courseName = courseName;
        this.parValues = parValues;
        this.numberOfHoles = this.parValues.size();
        this.parForHoles = new HashMap<>();
        assignParsToHoles();
    }

    /**
     * Validates the parValues list. Throws IllegalArgumentExeption if not valid
     * 
     * @param allParValues a list of par values which should be between 2 and 7
     */
    private void validateParValuesList(final ArrayList<Integer> allParValues) {
        for (Integer par : allParValues) {
            if (par < 2 || par > highestPossibleParValue) {
                throw new IllegalArgumentException("Not a valid list");
            }
        }
    }

    /**
     * assigns integers in the array of par values, to the hole with number == to.
     * its array index
     */
    private void assignParsToHoles() {
        for (int i = 0; i < numberOfHoles; i++) {
            setParForHole(i + 1, parValues.get(i));
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
    public ArrayList<Integer> getParValues() {
        return parValues;
    }

    /**
     * @return HashMap<Integer, Integer> parForHoles - contains key-value pairs for
     *         hole numbers and par values.
     */
    public HashMap<Integer, Integer> getPar() {
        return parForHoles;
    }

    /**
     * Links up the hole/par pair.
     * 
     * @param hole - the hole number
     * @param par  - the par value
     */
    public void setParForHole(final int hole, final int par) {
        if (hole < 1 || par < 2 || par > highestPossibleParValue) {
            throw new IllegalArgumentException("Not valid hole or par number");
        }
        parForHoles.put(hole, par);
    }

    /**
     * Returns the par value at a specific hole/index of the array.
     * 
     * @param hole - the hole number
     * @return int - the par value assigned to the specific hole
     */
    public int getParForHole(final int hole) {
        return parForHoles.get(hole);
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
        return "Course [numberOfHoles=" + numberOfHoles + ", courseName=" + courseName + ", parForHoles=" + parForHoles
                + ", parValues=" + parValues + "]";
    }
}
