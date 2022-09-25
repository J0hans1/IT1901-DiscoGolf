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
        this.currentHole = 0;
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
        return currentHole + 1;
    }
    

    /*
    * @return the current amount of throws the player has made on the current hole
    */
    public int getCurrentHoleThrows() {
        return throwsList.get(currentHole);
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
        if (currentHole < throwsList.size() - 1) {
            currentHole++;
        }
    }


    /*
    * removes one from the current hole number if the player is not on the first hole
    */
    public void previousHole() {
        if (currentHole > 0) {
            currentHole--;
        }
    }


    /*
    * adds one to the current amount of throws the player has made on the current hole
    */
    public void addThrow() {
        throwsList.set(currentHole, getCurrentHoleThrows() + 1);
    }


    /*
    * removes one from the current amount of throws the player has made on the current hole
    */
    public void removeThrow() {
        if (getCurrentHoleThrows() > 0) {
            throwsList.set(currentHole, getCurrentHoleThrows() - 1);
        }
    }
}
