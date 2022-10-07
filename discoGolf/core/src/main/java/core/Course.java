package core;

import java.util.ArrayList;
import java.util.HashMap;

public class Course {

    private int numberOfHoles;
    private String courseName;
    private HashMap<Integer, Integer> parForHoles;
    private ArrayList<Integer> parValues;

    
    /**
     * Create a disc golf course
     * @param courseName - is the name of the course
     * @param parForHoles - is an array of integers that represents the par value for the hole with number == to its array index
     */
    public Course(String courseName, ArrayList<Integer> parValues) {
        this.numberOfHoles = parValues.size();
        this.courseName = courseName;
        this.parValues = parValues;
        this.parForHoles = new HashMap<>();
        assignParsToHoles();
    }


    /**
     * assigns integers in the array of par values, to the hole with number == to its array index
     */
    public void assignParsToHoles(){
        for (int i = 0; i < numberOfHoles; i++) {
            setParForHole(i + 1 , parValues.get(i));
        }
    }


    
    /** 
     * @return String courseName - containing the name of the course
     */
    public String getCourseName() {
        return courseName;
    }


    
    /**
     * @return HashMap<Integer, Integer> parForHoles - contains key-value pairs for hole numbers and par values
     */
    public HashMap<Integer, Integer> getPar() { 
        return parForHoles;
    }


    
    /** 
     * Links up the hole/par pair
     * @param hole - the hole number
     * @param par - the par value
     */
    public void setParForHole(int hole, int par) {
        if (hole < 1 || par < 2 || par > 7) {
            throw new IllegalArgumentException("Not a valid hole or par number");
        }
        parForHoles.put(hole, par);
    }


    /** 
     * Returns the par value at a specific hole/index of the array
     * @param hole - the hole number
     * @return int - the par value assigned to the specific hole
     */
    public int getParForHole(int hole) {
        return parForHoles.get(hole);
    }
    
    
    /** 
     * Get number of holes at site
     * @return int
     */
    public int getNumberOfHoles() {
        return numberOfHoles;
    }
    

    /** 
     * Writes the object on string format
     * @return String
     */
    @Override
    public String toString() {
        return getCourseName() + ": " + getNumberOfHoles() + " holes";
    }
}
