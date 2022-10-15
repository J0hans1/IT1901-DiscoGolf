package ui;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.ComboBoxMatchers;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.util.WaitForAsyncUtils;

import discoGolf.core.Course;
import discoGolf.core.Scorecard;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class DiscoGolfAppTest extends ApplicationTest {

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
        // Scorecard scorecard = new Scorecard(null, null);

        Platform.runLater(()->{ //Synchronize the test with the JavaFX thread
            lade = new Course("Lade",new ArrayList<>(Arrays.asList(3,4,3,4,3,4,3,4,3)));
            //dragvoll = new Course("Dragvoll", new ArrayList<>(Arrays.asList(3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4)));
            this.scorecard = new Scorecard(lade, "Ulrik");
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

    private void updateInfoDisplay(){
        this.currentHoleParLabel.setText("Par: " + Integer.toString(this.scorecard.getCurrentHolePar()));
        this.previousHoleButton.setText("Prev Hole: " + Integer.toString(this.scorecard.getCurrentHole() - 1));
        this.nextHoleButton.setText("Next Hole: " + Integer.toString(this.scorecard.getCurrentHole() + 1));
        this.currentHole.setText("Current Hole: " + Integer.toString(this.scorecard.getCurrentHole()));
        this.currentScore.setText(Integer.toString(this.scorecard.getCurrentHoleThrows()));
    }

    private void handleBtnVisibilty(){
        this.previousHoleButton.setVisible(scorecard.getCurrentHole() != 1);                     
        this.nextHoleButton.setVisible(scorecard.getCurrentHole() != scorecard.getCourseSize()); 
        this.submitBtn.setVisible(scorecard.getCurrentHole() == scorecard.getCourseSize());
    }

    @Test
    public void testButton(){
        setUpItems();

        clickOn("#addThrowButton");
        clickOn("#addThrowButton");
        WaitForAsyncUtils.waitForFxEvents();
        
        System.out.println("HER: ");
        System.out.println(this.currentHole.getText());
        System.out.println(this.currentScore.getText());
    }



/////////////////////////////////////////////
//     @FXML
//     public TextField playerNameTextField;
//     private MainPageController controller;
//     @FXML
//     private ComboBox<String> pickCourseMenu;
//     private Course lade;
//     private Course dragvoll;

//     /**
//      Method for starting the javafx application
//      @param stage javafx Stage object
//      */
//     @Override
//     public void start(Stage stage) throws Exception {
//         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPageTest.fxml"));
//         Parent parent = fxmlLoader.load();
//         stage.setScene(new Scene(parent));
//         this.controller = fxmlLoader.getController();
//         stage.show();
//     }

//     @BeforeEach
//     public void setUpItems(){
//         // Scorecard scorecard = new Scorecard(null, null);
//         lade = new Course("Lade",new ArrayList<>(Arrays.asList(3,4,3,4,3,4,3,4,3)));
//         dragvoll = new Course("Dragvoll", new ArrayList<>(Arrays.asList(3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4)));
//         this.pickCourseMenu = lookup("#pickCourseMenu").query();
//     }

//     @Test
//    // @DisplayName("Test that the initialized items in controller are not null")
//     public void testContollerItems(){
//         assertNotNull(this.controller);
//     }
    
//     @Test
//     public void testCourseSelection(){
//         assertEquals(pickCourseMenu.getItems().get(0), lade.getCourseName());
//         assertEquals(pickCourseMenu.getItems().get(1), dragvoll.getCourseName());
//     }

    
//     // @Test
//     // public void checkSelectedcCourse(){
//     //     clickOn("#fxmlNameOfPlayer").write("Ulrik");
//     //     this.playerNameTextField = lookup("#fxmlNameOfPlayer").query();
//     //     WaitForAsyncUtils.waitForFxEvents();
//     //     System.out.println("VALUE:");
//     //     System.out.println(this.playerNameTextField.getText());
//     // } 

     // @Test
        // public void testDropdownSize(){
        //     assertEquals(2, this.controller.getItems().size());
        // }

}
