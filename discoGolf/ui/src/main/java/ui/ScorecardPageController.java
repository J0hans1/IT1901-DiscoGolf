package ui;

import java.io.IOException;
import java.net.URISyntaxException;

import discoGolf.core.Scorecard;
import discoGolf.json.DiscoGolfPersistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Node;

/**
 * JavaFX controller for displaying the scorecard ui
 * @author @Billy Barret
 * @version 1.0
 * @since 2022-09-21
 */
public class ScorecardPageController {
    
    private Scorecard scorecard;

    @FXML
    public Label currentCourseLabel, displayPlayerName, currentHole, currentScore, totalScoreLabel, currentHoleParLabel;
    @FXML
    public Button previousHoleButton, nextHoleButton, submitBtn;
    @FXML 
    private Parent root;
    @FXML 
    private Scene scene;
    @FXML 
    private Stage stage;

    
    /** 
     * create a scorecard object for current play
     * updates the name label to the name of the player
     * set currentCourse?
     * update course label to show name of current course
     * refreshes all labels by running refreshDisplay()
     * @param playerName is a String containing the name of the player, declared at the main page
     * @param selectedCourse is the course that we selected at the main page
     * @see refreshDisplay()
     */
    public void getPreviousControllerInfo(Scorecard newScorecard) {      
        scorecard = newScorecard;
        displayPlayerName.setText("Name: " + scorecard.getPlayerName());    
        currentCourseLabel.setText("Course: " + scorecard.getCourseName());
        refreshDisplay();
    }

    /**
     * Sends the scorecard data to a DatabaseHandler object
     * @throws URISyntaxException
     * @throws IOException
     */
    public void handleSubmit(ActionEvent event) throws IOException, URISyntaxException{
        try {
            DiscoGolfPersistence db = new DiscoGolfPersistence();
            db.sendScorecardToDatabase(scorecard);

            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MainPage.fxml"));
            root = fxmlLoader.load();
            stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }

    
    /**
     * Refreshes the content of the display,
     * by changing the labels content, button labels and button visibility.
     * @see handleBtnVisibilty()
     * @see updateInfoDisplay()
     */
    private void refreshDisplay(){
        updateInfoDisplay();
        handleBtnVisibilty();
    }


    /**
     * Handles wether the next, previous and submit buttons should be visible or not
     */
    private void handleBtnVisibilty(){
        previousHoleButton.setVisible(scorecard.getCurrentHole() != 1);                     
        nextHoleButton.setVisible(scorecard.getCurrentHole() != scorecard.getCourseSize()); 
        submitBtn.setVisible(scorecard.getCurrentHole() == scorecard.getCourseSize());
    }


    /**
     * Updates textlabels and buttonlabels at the Hole display 
     */
    private void updateInfoDisplay() {
        currentHoleParLabel.setText("Par: " + Integer.toString(scorecard.getCurrentHolePar()));
        previousHoleButton.setText("Prev Hole: " + Integer.toString(scorecard.getCurrentHole() - 1));
        nextHoleButton.setText("Next Hole: " + Integer.toString(scorecard.getCurrentHole() + 1));
        currentHole.setText("Current Hole: " + Integer.toString(scorecard.getCurrentHole()));
        currentScore.setText(Integer.toString(scorecard.getCurrentHoleThrows()));
    }


    /**
     * Adds throws to the current hole in the scorecard object
     * Refreshes the UI by running refreshDisplay() after the change
     * @see refreshDisplay()
     */
    public void addThrow() { 
        scorecard.addThrow();
        refreshDisplay();
    }


    /**
     * Removes throws at the current hole in the scorecard object
     * Refreshes the UI by running refreshDisplay() after the change
     * @see refreshDisplay()
     */
    public void removeThrow() {
        scorecard.removeThrow();
        refreshDisplay();
    }


    /**
     * Moves to the next hole
     * Refreshes the UI by running refreshDisplay() after the change
     * Resets current throws label to the par for next hole
     * Updates total score
     * @see refreshDisplay()
     */
    public void nextHole() {
        scorecard.nextHole();
        refreshDisplay();
        totalScoreLabel.setText("Total Score: " + Integer.toString(scorecard.getTotalScore())); 
    }


    /**
     * Moves to the previous hole
     * Refreshes the UI by running refreshDisplay() after the change
     * Resets current throws label to the number of throws made at previous hole. 
     * @see refreshDisplay()
     */
    public void previousHole() {
        scorecard.previousHole();
        refreshDisplay();
        totalScoreLabel.setText("Total Score: " + Integer.toString(scorecard.getTotalScore()));
        printCurrent();
    }


    /**
     * Prints current state of the scorecard
     * Used for debugging
     */
    private void printCurrent() {
        System.out.println("Current hole: " + scorecard.getCurrentHole());
        System.out.println("Current Score: " + scorecard.getCurrentHoleThrows()); 
    }

}
