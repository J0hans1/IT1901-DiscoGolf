package ui;

import java.io.IOException;
import java.net.URISyntaxException;

import discoGolf.core.Course;
import discoGolf.core.Scorecard;
import discoGolf.json.DiscoGolfPersistence;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ScorecardPageController {
    
    private Scorecard scorecard;
    private Course currentCourse;
    private boolean hasBeenClicked = false;

    @FXML
    public Label currentCourseLabel, displayPlayerName, currentHole, currentScore, totalScoreLabel, currentHoleParLabel;
    @FXML
    public Button previousHoleButton, nextHoleButton, submitBtn;

    
    /** 
     * create a scorecard object for current play
     * updates the name label to the name of the player
     * set currentCourse?
     * update course label to show name of current course
     * refreshes all labels by running refreshDisplay()
     * @param playerName is a String containing the name of the player, declared at the main page
     * @param selectedCourse is the course that we selected at the main page
     */
    public void getPreviousControllerInfo(Scorecard newScorecard) {      
        scorecard = newScorecard;
        displayPlayerName.setText("Name: " + scorecard.getPlayerName());    
        currentCourseLabel.setText("Course: " + scorecard.getCourseName());
        refreshDisplay();
    }
    

    /**
     * refreshes the content of the display, by changing the labels content, button labels and button visibility
     */
    private void refreshDisplay(){
        updateInfoDisplay();
        handleBtnVisibilty();
    }

    /**
     * Sends the scorecard data to a DatabaseHandler object
     * @throws URISyntaxException
     * @throws IOException
     */
    public void handleSubmit() throws IOException, URISyntaxException{
        if(hasBeenClicked == false){
            try {
                DiscoGolfPersistence db = new DiscoGolfPersistence();
                db.sendScorecardToDatabase(scorecard);
                hasBeenClicked = true;
            } catch (IOException e) {
                System.out.println("Error: " + e);
                e.printStackTrace();
            }
        }
    }

    /**
    * updates button labels at the previous and next hole display
    */
    private void handleBtnVisibilty(){
        previousHoleButton.setVisible(scorecard.getCurrentHole() != 1);                     
        nextHoleButton.setVisible(scorecard.getCurrentHole() != scorecard.getCourseSize()); 
        submitBtn.setVisible(scorecard.getCurrentHole() == scorecard.getCourseSize());
    }


    /**
    * updates labels at the Hole display (hole number, score, par)
    */
    private void updateInfoDisplay() {
        currentHoleParLabel.setText("Par: " + Integer.toString(scorecard.getCurrentHolePar()));
        previousHoleButton.setText("Prev Hole: " + Integer.toString(scorecard.getCurrentHole() - 1));
        nextHoleButton.setText("Next Hole: " + Integer.toString(scorecard.getCurrentHole() + 1));
        currentHole.setText("Current Hole: " + Integer.toString(scorecard.getCurrentHole()));
        currentScore.setText(Integer.toString(scorecard.getCurrentHoleThrows()));
    }


    /**
    * add throws/attempts at current hole
    * refreshes the display
    */
    public void addThrow() { 
        scorecard.addThrow();
        refreshDisplay();
    }


    /**
    * removes throws(attempts) at current hole
    * refreshes the display
    */
    public void removeThrow() {
        scorecard.removeThrow();
        refreshDisplay();
    }


    /**
    * Moves to the next hole
    * update hole number and button labels
    * resets current throws label to the par for next hole
    * updates total score
    */
    public void nextHole() {
        scorecard.nextHole();
        refreshDisplay();
        totalScoreLabel.setText("Total Score: " + Integer.toString(scorecard.getTotalScore())); 
    }


    /**
    * Moves to the previous hole
    * updates hole number and button labels
    * resets current throws label to the number of throws made at previous hole. 
    */
    public void previousHole() {
        scorecard.previousHole();
        refreshDisplay();
        totalScoreLabel.setText("Total Score: " + Integer.toString(scorecard.getTotalScore()));
        printCurrent();
    }


    /**
    * prints current state of the scorecard
    */
    private void printCurrent() {
        System.out.println("Current hole: " + scorecard.getCurrentHole());
        System.out.println("Current Score: " + scorecard.getCurrentHoleThrows()); 
    }
}
