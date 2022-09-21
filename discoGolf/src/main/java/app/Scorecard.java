package app;

import java.util.ArrayList;

public class Scorecard {
    private ArrayList<Integer> throwsList = new ArrayList<>();
    private int currentHole;
    private String nameOfPlayer;
    private Course currentCourse;
    
    public Scorecard(Course course, String nameOfPlayer, int numberOfHoles) {
        this.nameOfPlayer = nameOfPlayer;
        this.currentCourse = course;
        this.currentHole = 0;
        throwsList = currentCourse.getPar().values().stream().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
    
    public String getNameOfPlayer() {
        return nameOfPlayer;
    }

    public int getTotalScore() {
        int total = (int) throwsList.stream().mapToInt(Integer::intValue).sum() - (int) currentCourse.getPar().values().stream().mapToInt(Integer::intValue).sum();
        return total;
    }

    public int getCurrentHole() {
        return currentHole + 1;
    }

    public int getCurrentHoleThrows() {
        return throwsList.get(currentHole);
    }

    public int getCurrentHolePar() {
        return currentCourse.getParForHole(getCurrentHole());
    }

    public int getCourseSize() {
        return currentCourse.getPar().size();
    }

    public void nextHole() {
        if (currentHole < throwsList.size() - 1) {
            currentHole++;
        }
    }

    public void previousHole() {
        if (currentHole > 0) {
            currentHole--;
        }
    }

    public void addThrow() {
        throwsList.set(currentHole, getCurrentHoleThrows() + 1);
    }

    public void removeThrow() {
        if (getCurrentHoleThrows() > 0) {
            throwsList.set(currentHole, getCurrentHoleThrows() - 1);
        }
    }


    public static void main(String[] args) {
    }

}
