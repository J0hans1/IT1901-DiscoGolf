package app;

import java.util.ArrayList;

public class Scorecard {
    private ArrayList<Integer> scores = new ArrayList<>();
    private int currentHole;
    private String nameOfCourses;
    private String nameOfPlayer;
    
    public Scorecard(String nameOfPlayer, int numberOfHoles) {
        for (int i = 0; i < numberOfHoles; i++) {
            scores.add(0);
        }
        this.nameOfPlayer = nameOfPlayer;
        this.currentHole = 0;
    }
    
    public String getNameOfPlayer() {
        return nameOfPlayer;
    }

    public int getTotalScore() {
        int total = (int) scores.stream().mapToInt(Integer::intValue).sum();
        return total;
    }

    public int getCurrentHole() {
        return currentHole + 1;
    }

    public String getCurrentCourses() {
        return nameOfCourses;
    }

    public int getCurrentHoleScore() {
        return scores.get(currentHole);
    }

    public void nextHole() {
        if (currentHole < scores.size() - 1) {
            currentHole++;
        }
    }

    public void previousHole() {
        if (currentHole > 0) {
            currentHole--;
        }
    }


    public void addThrow() {
        scores.set(currentHole, getCurrentHoleScore() + 1);
    }

    public void removeThrow() {
        if (getCurrentHoleScore() > 0) {
            scores.set(currentHole, getCurrentHoleScore() - 1);
        }
    }


    public static void main(String[] args) {
    }

}
