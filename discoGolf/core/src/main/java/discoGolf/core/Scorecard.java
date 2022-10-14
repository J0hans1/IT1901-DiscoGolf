package discoGolf.core;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Scorecard {
    private ArrayList<Integer> throwsList = new ArrayList<>();
    private int currentHole;
    private final String playerName;
    private final Course course;

    /**
    - constructs a scorecard object that vil be saved in the database
     * @param course is the course the player picked at the main menu
     * @param playerName is the name of the player
     */
    public Scorecard(Course course, String playerName) {
        System.out.println("SCORECARD");
        validateMainPageCourse(course);
        validateMainPageName(playerName);

        this.playerName = playerName;
        this.course = course;
        this.currentHole = 1;
        throwsList = course.getPar().values().stream().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
    
    /**
     * @return the name of player which is a attrivute of the scorecard
    */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * @return throwsList - containing the throws of the player at holenumber = index + 1
     */
    public ArrayList<Integer> getThrowsList() {
        return new ArrayList<Integer>(throwsList);
    }


    /**
    * @return the total amount of throws minus the total of all the pars of each hole 
    */
    public int getTotalScore() {
        int total = (int) throwsList.stream().mapToInt(Integer::intValue).sum() - (int) course.getPar().values().stream().mapToInt(Integer::intValue).sum();
        return total;
    }


    /**
    * @return the name of course the player is playing on
    */
    public String getCourseName() {
        return course.getCourseName();
    }


    /**
    * @return the current hole number the player is playing on
    */
    public int getCurrentHole() {
        return currentHole;
    }

    /**
     * @return course of the scorecard
     */
    public Course getCourse() {
        return course;
    }
    
    /**
    * @return the current amount of throws the player has made on the current hole
    */
    public int getCurrentHoleThrows() {
        return throwsList.get(getCurrentHole() - 1);
    }


    /**
    * @return the current par of the current hole
    */
    public int getCurrentHolePar() {
        return course.getParForHole(getCurrentHole());
    }


    /**
    * @return the size of the current course by streaming the courses par list and counting the amount of elements
    */
    public int getCourseSize() {
        return course.getPar().size();
    }


    /**
    * adds one to the current hole number if the player is not on the last hole
    */
    public void nextHole() {
        if (getCurrentHole() == throwsList.size()) {
            throw new IllegalStateException("Can't go to nextHole because next hole doesn't exist");
        }
        currentHole++;
    }


    /**
    * removes one from the current hole number if the player is not on the first hole
    */
    public void previousHole() {
        if (getCurrentHole() == 1) {
            throw new IllegalStateException("Cannot go to a negative hole number");
        }
        currentHole--;      
    }

    /**
    * adds one to the current amount of throws the player has made on the current hole
    */
    public void addThrow() {
        throwsList.set(getCurrentHole() - 1, getCurrentHoleThrows() + 1);
    }


    /**
    * removes one from the current amount of throws the player has made on the current hole
    */
    public void removeThrow() {
        if (getCurrentHoleThrows() == 1) {
            throw new IllegalStateException("Cannot have 0 throws");
        }
        throwsList.set(getCurrentHole() - 1, getCurrentHoleThrows() - 1);
    }

    /**
     * @param course Course object representing the chosen course in the main page
     * @throws IllegalStateException Throws if no course is selected
     */
    private void validateMainPageCourse(Course course){
        if (course == null){
            throw new IllegalStateException("No course selected!");
        }
    }

    /**
     * @param name String value from the value in the input field of the main page
     * @throws IllegalArgumentException Throws if name doesnt fit the format "name1 name2 (optinal) name3(optinal)..."
     */
    private void validateMainPageName(String name){
        boolean regexCheck = Pattern.matches("[a-zA-Z0-9]\s?(([a-zA-Z0-9]+\s?)?)*", name);
        if (!regexCheck){
            throw new IllegalArgumentException("Illegal input to name field");
        }
    }
}
