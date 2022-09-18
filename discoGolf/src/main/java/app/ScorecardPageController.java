package app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ScorecardPageController {

    @FXML
    Label currentScore;

    private Scorecard scorecard;

    private Course currentCourse;

    @FXML
    Label currentCourseLabel;

    @FXML
    Label displayNameOfPlayer;

    public void getPreviousControllerInfo(String nameOfPlayer, int numberOfHoles, Course selectedCourse) {
        scorecard = new Scorecard(nameOfPlayer, numberOfHoles); //Create new scorecard
        
        displayNameOfPlayer.setText("Name: " + scorecard.getNameOfPlayer()); //Update name of player label

        this.currentCourse = selectedCourse; //Set current course
        currentCourseLabel.setText("Course: " + currentCourse.getCourseName()); //Update course label

        updateInfoDisplay(); //Update all other labels
    }

    // private void updateInfoDisplay() {
    //     displayNameOfPlayer.setText(scorecard.getNameOfPlayer());
    // }


    @FXML
    Button previousHoleButton;

    @FXML
    Button nextHoleButton;



    private void updateInfoDisplay() { //Update all labels

        /* Needs to be updated often:
        - current hole
        - current score
        - current par
        - next hole
        - previous hole
        */

        previousHoleButton.setText("Prev Hole: " + Integer.toString(scorecard.getCurrentHole() - 1));
        nextHoleButton.setText("Next Hole: " + Integer.toString(scorecard.getCurrentHole() + 1));
        currentHole.setText("Current Hole: " + Integer.toString(scorecard.getCurrentHole()));
        currentScore.setText(Integer.toString(scorecard.getCurrentHoleScore()));
        System.out.println(scorecard.getNameOfPlayer());

        printCurrent();
    }

    public void addThrow() { 
        scorecard.addThrow();
        updateInfoDisplay();
        printCurrent();
    }

    public void removeThrow() {
        scorecard.removeThrow();
        updateInfoDisplay();
        printCurrent();
    }

    @FXML
    Label currentHole;

    public void nextHole() {
        scorecard.nextHole();
        updateInfoDisplay();
        printCurrent();
         //update current hole label
    }

    public void previousHole() {
        scorecard.previousHole();
        updateInfoDisplay();
        printCurrent();
        //update current hole label
    }

    public void printCurrent() {
        System.out.println("Current hole: " + scorecard.getCurrentHole());
        System.out.println("Current Score: " + scorecard.getCurrentHoleScore()); 
    }

    


}
