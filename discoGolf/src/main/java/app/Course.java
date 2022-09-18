package app;

import java.util.HashMap;

public class Course {

    private int numberOfHoles;

    private String courseName;

    private HashMap<Integer, Integer> parForHoles;

    public Course(int numberOfHoles, String courseName) {
        this.numberOfHoles = numberOfHoles;
        this.courseName = courseName;
        parForHoles = new HashMap<>();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setParForHole(int hole, int par) {
        parForHoles.put(hole, par);
    }

    public int getParForHole(int hole) {
        return parForHoles.get(hole);
    }
    
    public int getNumberOfHoles() {
        return numberOfHoles;
    }

    @Override
    public String toString() {
        return getCourseName() + ": " + getNumberOfHoles() + " holes";
    }
}
