package app;

import java.util.ArrayList;

public class Scorecard {
    private ArrayList<Integer> scores = new ArrayList<Integer>();
    private int currentHole;
    private String scorecardName;
    private String nameOfCourses;

    public int getTotalScore() {
        int total = (int) scores.stream().mapToInt(Integer::intValue).sum();
        return total;
    }

    public int getCurrentHole() {
        return currentHole + 1;
    }

    public int currentHoleScore() {
        return scores.get(currentHole);
    }

    public String getCurrentCourses() {
        return nameOfCourses;
    }

    public String getCurrentScorecardName() {
        return scorecardName;
    }


    public static void main(String[] args) {
        Scorecard scorecard = new Scorecard();
        scorecard.scores.add(3);
        scorecard.scores.add(4);
        scorecard.scores.add(5);
        System.out.println(scorecard.getTotalScore());
    }
}
