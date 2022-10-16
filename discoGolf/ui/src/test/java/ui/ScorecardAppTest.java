package ui;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import discoGolf.core.Course;
import discoGolf.core.Scorecard;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Test class for testing scorecard page of disco golf app using TestFX
 * @author Ulrik Isdahl
 * @version 1.0
 * @since 2022-10-15
 */
public class ScorecardAppTest extends ApplicationTest{

    private Course lade;
    private Scorecard scorecard;
    @FXML
    private ScorecardPageController controller;
    @FXML
    public Label currentCourseLabel, displayPlayerName, currentHole, currentScore, totalScoreLabel, currentHoleParLabel;
    @FXML
    public Button previousHoleButton, nextHoleButton, submitBtn;

    /**
     Method for starting the javafx application
     @param stage javafx Stage object
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ScorecardTest.fxml"));
        Parent parent = fxmlLoader.load();
        stage.setScene(new Scene(parent));
        this.controller = fxmlLoader.getController();
        stage.show();
    }

    @BeforeEach
    public void setUpItems(){
        this.lade = new Course("Lade",new ArrayList<>(Arrays.asList(3,4,3,4,3,4,3,4,3)));

        this.scorecard = new Scorecard(this.lade, "Ulrik");
        Platform.runLater(()->{ //Synchronize the test with the JavaFX thread
            this.displayPlayerName = lookup("#displayNameOfPlayer").query();
            this.currentCourseLabel = lookup("#currentCourseLabel").query();
            this.displayPlayerName.setText("Name: " + scorecard.getPlayerName());    
            this.currentCourseLabel.setText("Course: " + scorecard.getCourseName());

            this.currentHoleParLabel = lookup("#currentHoleParLabel").query();
            this.previousHoleButton = lookup("#previousHoleButton").query();
            this.nextHoleButton = lookup("#nextHoleButton").query();
            this.currentHole = lookup("#currentHole").query();
            this.currentScore = lookup("#currentScore").query();
            this.submitBtn = lookup("#submitBtn").query();
            
            updateInfoDisplay();
            handleBtnVisibilty();
        });
    }
    
    @Test
    @DisplayName("Test that the controller has been loaded")
    public void testController(){
        assertNotNull(this.controller);
    }
    
    @Test
    @DisplayName("Test that elements regarding scorecard values are present on the page")
    public void findPlayerDetails(){
        String playerName = this.scorecard.getPlayerName();
        WaitForAsyncUtils.waitForFxEvents();
        assertNotNull(lookup(playerName));
        
        String courseName = this.scorecard.getCourseName();
        WaitForAsyncUtils.waitForFxEvents();
        assertNotNull(lookup(courseName));
        
        Integer totalScore = this.scorecard.getTotalScore();
        WaitForAsyncUtils.waitForFxEvents();
        assertNotNull(lookup(totalScore.toString())); //Check that there is a element with the value 'totalScore' in the page
    }
    
    @Test
    @DisplayName("Test that the submitbutton is available after the end of the Lade course is reached")
    public void findSubmitButtonLadeCourse(){
        iterateThroughCourse(scorecard.getCourse());
        Button submitBtn = lookup("Submit").query();
        WaitForAsyncUtils.waitForFxEvents();
        assertNotNull(submitBtn);
    }
    
    /**
     * Updates the display of the scorecardController such that the values in the scorecard are presented on the page
     */
    private void updateInfoDisplay(){
        this.currentHoleParLabel.setText("Par: " + Integer.toString(this.scorecard.getCurrentHolePar()));
        this.previousHoleButton.setText("Prev Hole: " + Integer.toString(this.scorecard.getCurrentHole() - 1));
        this.nextHoleButton.setText("Next Hole: " + Integer.toString(this.scorecard.getCurrentHole() + 1));
        this.currentHole.setText("Current Hole: " + Integer.toString(this.scorecard.getCurrentHole()));
        this.currentScore.setText(Integer.toString(this.scorecard.getCurrentHoleThrows()));
    }

    /**
     * Updates the visibility of the submitbutton depending on getCurrentHole() value of scorecard
     */
    private void handleBtnVisibilty(){
        this.previousHoleButton.setVisible(scorecard.getCurrentHole() != 1);
        this.nextHoleButton.setVisible(scorecard.getCurrentHole() != scorecard.getCourseSize()); 
        this.submitBtn.setVisible(scorecard.getCurrentHole() == scorecard.getCourseSize());
    }

    /**
     * Clicks on the nextHoleButton course.getNumberOfHoles() times, such that the submit button will appear
     * @param course
     */
    private void iterateThroughCourse(Course course){
        for (int i = 0; i < course.getNumberOfHoles(); i++) {
            clickOn("#nextHoleButton");
        }
    }

}

