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
    public Course(int numberOfHoles, String courseName, ArrayList<Integer> parValues) {
        this.numberOfHoles = numberOfHoles;
        this.courseName = courseName;
        this.parValues = parValues;
        this.parForHoles = new HashMap<>();
        assignParsToHoles();
    }


    /*
    - assigns integers in the array of par values, to the hole with number == to its array index
    */
    public void assignParsToHoles(){
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
    ? hva brukes denne til og hva er grunnen til at den heter get par?
    */
    public HashMap<Integer, Integer> getPar() {
        return parForHoles;
    }


    /*
    - links up the hole/par pair
    */
    public void setParForHole(int hole, int par) {
        if (hole < 1 || par < 3) {
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
