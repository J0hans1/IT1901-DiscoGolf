package app;

import java.util.ArrayList;
import java.util.HashMap;

public class Course {
    private int numberOfHoles;
    private String courseName;
    private HashMap<Integer, Integer> parForHoles;
    private ArrayList<Integer> parValues;

    
    /*
    - Create a disc golf course
    - numberOfHoles decides the number of holes in the course
    - courseName is the name of the course
    - parForHoles is an array of integers that represents the par value for the hole with number == to its array index
    */
    public Course(String courseName, ArrayList<Integer> parValues) {
        this.courseName = courseName;
        if (validateParValuesList(parValues)) {
            this.parValues = parValues;
        }
        this.numberOfHoles = this.parValues.size();
        this.parForHoles = new HashMap<>();
        assignParsToHoles();
    }

    /**
     * Validates the parValues list and throws IllegalArgumentExeption if not valid
     * @param parValues a list of par values which should be between 2 and 7
     * @return true if the input is valid
     */
    private boolean validateParValuesList(ArrayList<Integer> parValues) {
        for (Integer par : parValues) {
            if (par < 2 || par > 7) {
                throw new IllegalArgumentException("Not a valid parValues list");
            }
        }
        return true;
    }


    /*
    - assigns integers in the array of par values, to the hole with number == to its array index
    */
    private void assignParsToHoles(){
        for (int i = 0; i < numberOfHoles; i++) {
            setParForHole(i + 1 , parValues.get(i));
        }
    }


    /*
    - getter for name
    */
    public String getCourseName() {
        return courseName;
    }


    /*
    return hole/par values
    */
    public HashMap<Integer, Integer> getPar() {
        return parForHoles;
    }


    /*
    - links up the hole/par pair
    */
    public void setParForHole(int hole, int par) {
        if (hole < 1 || par < 2 || par > 7) {
            throw new IllegalArgumentException("Not a valid hole or par number");
        }
        parForHoles.put(hole, par);
    }


    /*
    - returns the par value at a specific hole/index of the array
    */
    public int getParForHole(int hole) {
        return parForHoles.get(hole);
    }
    

    /*
    - get number of holes at site
    */
    public int getNumberOfHoles() {
        return numberOfHoles;
    }


    /*
    - To string method
    */
    @Override
    public String toString() {
        return getCourseName() + ": " + getNumberOfHoles() + " holes";
    }
}
