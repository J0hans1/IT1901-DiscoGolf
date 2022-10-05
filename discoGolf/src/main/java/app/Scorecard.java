package app;

import java.util.ArrayList;

public class Scorecard {

    private ArrayList<Integer> throwsList = new ArrayList<>();
    private int currentHole;
    private String nameOfPlayer;
    private Course currentCourse;

    /**
    - constructs a scorecard object that vil be saved in the database
     * @param course is the course the player picked at the main menu
     * @param nameOfPlayer is the name of the player
     */
    public Scorecard(Course course, String nameOfPlayer) {
        this.nameOfPlayer = nameOfPlayer;
        this.currentCourse = course;
        this.currentHole = 1;
        throwsList = currentCourse.getPar().values().stream().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
    
    /**
     * @return the name of player which is a attrivute of the scorecard
    */
    public String getNameOfPlayer() {
        return nameOfPlayer;
    }


    /*
    * @return the total amount of throws minus the total of all the pars of each hole 
    */
    public int getTotalScore() {
        int total = (int) throwsList.stream().mapToInt(Integer::intValue).sum() - (int) currentCourse.getPar().values().stream().mapToInt(Integer::intValue).sum();
        return total;
    }


    /*
    * @return the name of course the player is playing on
    */
    public String getCourseName() {
        return currentCourse.getCourseName();
    }


    /*
    * @return the current hole number the player is playing on
    */
    public int getCurrentHole() {
        return currentHole;
    }

    /*
     * @return course of the scorecard
     */
    public Course getCourse() {
        return currentCourse;
    }
    
    /*
    * @return the current amount of throws the player has made on the current hole
    */
    public int getCurrentHoleThrows() {
        return throwsList.get(getCurrentHole() - 1);
    }


    /*
    * @return the current par of the current hole
    */
    public int getCurrentHolePar() {
        return currentCourse.getParForHole(getCurrentHole());
    }


    /**
    * @return the size of the current course by streaming the courses par list and counting the amount of elements
    */
    public int getCourseSize() {
        return currentCourse.getPar().size();
    }


    /*
    * adds one to the current hole number if the player is not on the last hole
    */
    public void nextHole() {
        if (getCurrentHole() == throwsList.size()) {
            throw new IllegalStateException("Can't go to nextHole because next hole doesn't excist");
        }
        currentHole++;
    }


    /*
    * removes one from the current hole number if the player is not on the first hole
    */
    public void previousHole() {
        if (getCurrentHole() == 1) {
            throw new IllegalStateException("Cannot go to a negative hole number");
        }
        currentHole--;      
    }

    /*
    * adds one to the current amount of throws the player has made on the current hole
    */
    public void addThrow() {
        throwsList.set(getCurrentHole() - 1, getCurrentHoleThrows() + 1);
    }


    /*
    * removes one from the current amount of throws the player has made on the current hole
    */
    public void removeThrow() {
        if (getCurrentHoleThrows() == 1) {
            throw new IllegalStateException("Cannot have 0 throws");
        }
        throwsList.set(getCurrentHole() - 1, getCurrentHoleThrows() - 1);
    }
}
