package app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ScorecardPageController {

    //*Global variables
    private Scorecard scorecard;
    private Course currentCourse;

    //*FXML components
    @FXML
    public Label currentCourseLabel, displayNameOfPlayer, currentHole, currentScore, totalScoreLabel;

    @FXML
    public Button previousHoleButton, nextHoleButton, submitBtn;

    /*
    Hva gjør denne?
    */
    
    public void getPreviousControllerInfo(String nameOfPlayer, int numberOfHoles, Course selectedCourse) {
        scorecard = new Scorecard(selectedCourse, nameOfPlayer, numberOfHoles); //Create new scorecard
        
        displayNameOfPlayer.setText("Name: " + scorecard.getNameOfPlayer()); //Update name of player label
        
        this.currentCourse = selectedCourse; //Set current course
        currentCourseLabel.setText("Course: " + currentCourse.getCourseName()); //Update course label
        
        updateInfoDisplay(); //Update all other labels
    }
    
    @FXML
    Label currentHoleParLabel;

    
    private void updateInfoDisplay() { //Update all labels

        /*
        Needs to be updated often:
        - current hole
        - current score
        - current par
        - next hole
        - previous hole
        */


        previousHoleButton.setVisible(scorecard.getCurrentHole() != 1); //check if previous hole button should be hidden
        nextHoleButton.setVisible(scorecard.getCurrentHole() != scorecard.getCourseSize()); //check if next hole button should be hidden

        currentHoleParLabel.setText("Par: " + Integer.toString(scorecard.getCurrentHolePar()));
        previousHoleButton.setText("Prev Hole: " + Integer.toString(scorecard.getCurrentHole() - 1));
        nextHoleButton.setText("Next Hole: " + Integer.toString(scorecard.getCurrentHole() + 1));
        currentHole.setText("Current Hole: " + Integer.toString(scorecard.getCurrentHole()));
        currentScore.setText(Integer.toString(scorecard.getCurrentHoleScore()));
        System.out.println(scorecard.getNameOfPlayer());

        printCurrent();
    }


    /*
     * show submit button
     * 
    */

    /*
    ! Updates the Total score label, after the the "next-hole button" has been clicked
    */
    public void handleTotalScore(){

    }

    /*
    * add throws(attempts) at current hole
    ! will be used in calculation of total score, when next hole is clicked
    */
    public void addThrow() { 
        scorecard.addThrow();
        updateInfoDisplay();
        printCurrent();
    }

    /*
    * removes throws(attempts) at current hole
    ! will be used in calculation of total score, when next hole is clicked
    */
    public void removeThrow() {
        scorecard.removeThrow();
        updateInfoDisplay();
        printCurrent();
    }


    /*
    * Moves one to the next hole
    * must update hole number
    ! must update total score
    * must reset current throws label to zero for next hole
    ! must be hidden if youre at the last hole, then show the submit button in its place.
    */
    public void nextHole() {
        scorecard.nextHole();
        updateInfoDisplay();
        printCurrent();
        totalScoreLabel.setText("Total Score: " + Integer.toString(scorecard.getTotalScore())); //Update total score only when switching between holes
         //update current hole label
    }

    /*
    * Moves one to the next hole
    * must update hole number
    ! must update total score
    ! must reset current throws label to the number of throws made at previous hole. 
    ! if hole number is 1, then this button must be hidden
    */

    public void previousHole() {
        scorecard.previousHole();
        updateInfoDisplay();
        printCurrent();
        totalScoreLabel.setText("Total Score: " + Integer.toString(scorecard.getTotalScore())); //Update total score only when switching between holes
        //update current hole label
    }

    /*
    ! Prints the current state of the scorecard:
    ! total score
    * current hole throws
    * current hole
    */
    public void printCurrent() {
        System.out.println("Current hole: " + scorecard.getCurrentHole());
        System.out.println("Current Score: " + scorecard.getCurrentHoleScore()); 
    }
}
