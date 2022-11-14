package ui;

import java.io.IOException;
import discoGolf.core.Scorecard;
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
    private DataAccess access = new DataAccess();

    @FXML
    public Label currentCourseLabel, displayPlayerName, currentHole, currentScore, totalScoreLabel, currentHoleParLabel;
    @FXML
    public Button previousHoleButton, nextHoleButton, submitBtn, removeThrowButton;
    @FXML 
    private Parent root;
    @FXML 
    private Scene scene;
    @FXML 
    private Stage stage;

    
    /** 
     * create a scorecard object for current play
     * refreshes all labels by running refreshDisplay()
     * @param newScorecard the scorecard object to be used for the current play
     * @see refreshDisplay()
     */
    public void getPreviousControllerInfo(Scorecard newScorecard) {      
        scorecard = newScorecard;
        displayPlayerName.setText("Name: " + scorecard.getPlayerName());    
        currentCourseLabel.setText("Course: " + scorecard.getCourseName());
        refreshDisplay();
    }

    /**
     * Sends the scorecard data to the database via the DataAccess class and then RestAPI
     * @param event the event that triggers the the method
     * @throws IOException if conversion from scorecard.java to .json fails
     */
    public void handleSubmit(ActionEvent event) throws IOException {
        try {
            access.RequestPostingScorecard(scorecard);
            goBackToMainPage(event);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    /**
     * cancel a started scorecard and go back to the main page
     * @param event the event that triggers the the method
     */
    @FXML
    public void cancelGame(ActionEvent event) {
        goBackToMainPage(event);
    }

    /**
     * Takes user to the main page
     * @param event the event that triggers the the method
     */
    private void goBackToMainPage(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MainPage.fxml"));
            root = fxmlLoader.load();
            MainPageController mainPageController = fxmlLoader.getController();
            mainPageController.displayScorecardFeedback();
            stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
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
        removeThrowButton.setDisable(scorecard.getCurrentHoleThrows() == 1);
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
        scorecard.getCurrentHoleInstance().addThrow();
        refreshDisplay();
    }


    /**
     * Removes throws at the current hole in the scorecard object
     * Refreshes the UI by running refreshDisplay() after the change
     * @see refreshDisplay()
     */
    public void removeThrow() {
        scorecard.getCurrentHoleInstance().removeThrow();
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
        totalScoreLabel.setText("Total Score: " + Integer.toString(scorecard.getScore())); 
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
        totalScoreLabel.setText("Total Score: " + Integer.toString(scorecard.getScore()));
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
