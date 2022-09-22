package app;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ScorecardPageController {

    /*
     * 
     */
    private Scorecard scorecard;
    private Course currentCourse;

    /*
    FXML components
    */
    @FXML
    public Label currentCourseLabel, displayNameOfPlayer, currentHole, currentScore, totalScoreLabel, currentHoleParLabel;
    @FXML
    public Button previousHoleButton, nextHoleButton, submitBtn;

    /*
    ?  what does this function do?
    */
    public void getPreviousControllerInfo(String nameOfPlayer, Course selectedCourse) {
        scorecard = new Scorecard(selectedCourse, nameOfPlayer); //Create new scorecard
        displayNameOfPlayer.setText("Name: " + scorecard.getNameOfPlayer());    //Update name of player label
        this.currentCourse = selectedCourse;                                    //Set current course
        currentCourseLabel.setText("Course: " + currentCourse.getCourseName()); //Update course label
        updateLabels();
    }
    

    /*
    - updates all labels at display
    */
    private void updateLabels(){
        updateInfoDisplay();
        handleBtnVisibilty();
    }

    /*
     * Sends the scorecard data to a DatabaseHandler object
     */
    public void handleSubmit(){
        DatabaseHandler database = new DatabaseHandler();
        try {
            database.writeToDatabse(scorecard.getNameOfPlayer(), Integer.toString(scorecard.getTotalScore()), scorecard.getCourseName());
        } catch (IOException e) {
            System.out.println("Error in writing to database");
            e.printStackTrace();
        }
        System.out.println("Data saved in database");
    }

    /*
    - updates button labels at the previous and next hole display
    */
    private void handleBtnVisibilty(){
        previousHoleButton.setVisible(scorecard.getCurrentHole() != 1);                     
        nextHoleButton.setVisible(scorecard.getCurrentHole() != scorecard.getCourseSize()); 
        submitBtn.setVisible(scorecard.getCurrentHole() == scorecard.getCourseSize());
    }


    /*
    - updates labels at the Hole display (hole number, score, par)
    */
    private void updateInfoDisplay() {
        currentHoleParLabel.setText("Par: " + Integer.toString(scorecard.getCurrentHolePar()));
        previousHoleButton.setText("Prev Hole: " + Integer.toString(scorecard.getCurrentHole() - 1));
        nextHoleButton.setText("Next Hole: " + Integer.toString(scorecard.getCurrentHole() + 1));
        currentHole.setText("Current Hole: " + Integer.toString(scorecard.getCurrentHole()));
        currentScore.setText(Integer.toString(scorecard.getCurrentHoleThrows()));
    }


    /*
    - add throws(attempts) at current hole
    */
    public void addThrow() { 
        scorecard.addThrow();
        updateLabels();
        printCurrent();
    }


    /*
    - removes throws(attempts) at current hole
    */
    public void removeThrow() {
        scorecard.removeThrow();
        updateLabels();
        printCurrent();
    }


    /*
    - Moves to the next hole
    - update hole number and button labels
    - resets current throws label to the par for next hole
    - updates total score
    */
    public void nextHole() {
        scorecard.nextHole();
        updateLabels();
        printCurrent();
        totalScoreLabel.setText("Total Score: " + Integer.toString(scorecard.getTotalScore())); //Update total score only when switching between holes
         //update current hole label
    }


    /*
    - Moves to the previous hole
    - updates hole number and button labels
    - resets current throws label to the number of throws made at previous hole. 
    */
    public void previousHole() {
        scorecard.previousHole();
        updateLabels();
        printCurrent();
        totalScoreLabel.setText("Total Score: " + Integer.toString(scorecard.getTotalScore())); //Update total score only when switching between holes
        //update current hole label
    }


    /*
    - prints current state of the scorecard
    */
    private void printCurrent() {
        System.out.println("Current hole: " + scorecard.getCurrentHole());
        System.out.println("Current Score: " + scorecard.getCurrentHoleThrows()); 
    }
}
