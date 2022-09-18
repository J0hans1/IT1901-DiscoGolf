package app;

import java.util.HashMap;

public class Course {
    
    private int numberOfHoles;

    private HashMap<Integer, Integer> parForHoles;

    public Course(int numberOfHoles) {
        this.numberOfHoles = numberOfHoles;
        parForHoles = new HashMap<>();
    }

    public void setParForHole(int hole, int par) {
        parForHoles.put(hole, par);
    }

    public int getParForHole(int hole) {
        return parForHoles.get(hole);
    }
}
