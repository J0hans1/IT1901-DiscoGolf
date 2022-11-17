//package ui;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import javafx.scene.input.KeyCode;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.testfx.framework.junit5.ApplicationTest;
//
//import discoGolf.core.Course;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.stage.Stage;
//
//
///**
// * Test class for testing Main page of disco golf app using TestFX
// * @author Ulrik Isdahl, Markus Johansen and Jakob Opland
// * @version 2.0
// * @since 2022-10-15
// */
//public class ScorecardUIAppTest extends ApplicationTest {
//
//    private String courseNameLade, courseNameDragvoll, playerName;
//    private Course lade;
//    private Course dragvoll;
//    @FXML
//    private MainPageController controller;
//
//    /**
//     Method for starting the javafx application
//     @param stage javafx Stage object
//     */
//    @Override
//    public void start(Stage stage) throws Exception {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
//        Parent parent = fxmlLoader.load();
//        stage.setScene(new Scene(parent));
//        this.controller = fxmlLoader.getController();
//        stage.show();
//    }
//
//    /**
//     * Sets up internal variables for the test
//     */
//    @BeforeEach
//    public void setUpItems(){
//        this.lade = new Course("Lade",new ArrayList<>(Arrays.asList(3,4,3,4,3,4,3,4,3)));
//        this.dragvoll = new Course("Dragvoll", new ArrayList<>(Arrays.asList(3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4)));
//        this.playerName = "Bob";
//        this.courseNameLade = "Lade";
//        this.courseNameDragvoll = "Dragvoll";
//    }
//
//    /**
//    * Check if the name written into #playerNameTextField is displayed when the play-button is clicked
//    */
//    @Test
//    public void scorecardPageName(){
//        setupPageLade(this.playerName);
//        Label l = (Label) lookup("#displayPlayerName").query();
//        assertEquals("Name: " + this.playerName, l.getText());
//    }
//
//    /**
//    * Check if the selected course name is Lade
//    */
//    @Test
//    public void scorecardCourseNameLade(){
//        setupPageLade(this.playerName);
//        Label l = (Label) lookup("#currentCourseLabel").query();
//        assertEquals("Course: " + this.courseNameLade, l.getText());
//    } 
//
//    /**
//    * Check if the selected course name is Lade
//    */
//    @Test
//    public void scorecardCourseNameDragvoll(){
//        setupPageDragvoll(this.playerName);
//        Label l = (Label) lookup("#currentCourseLabel").query();
//        assertEquals("Course: " + this.courseNameDragvoll, l.getText());
//    } 
//
//    /**
//    * removeThrowButton should be disabled when the current throw number is one
//    */
//    @Test
//    public void disableRemoveThrowButton(){
//        setupPageLade(this.playerName);
//        for (int i = 0; i < this.lade.getParValues().get(0) - 1; i++) {
//            clickOn("#removeThrowButton");
//        }
//        Button removeThrowButton = lookup("#removeThrowButton").query();
//        assertTrue(removeThrowButton.isDisable());
//
//        clickOn("#nextHoleButton");
//        clickOn("#nextHoleButton");
//
//        for (int i = 0; i < this.lade.getParValues().get(2) - 1; i++) {
//            clickOn("#removeThrowButton");
//        }
//        Button removeThrowButton2 = lookup("#removeThrowButton").query();
//        assertTrue(removeThrowButton2.isDisable());
//
//        clickOn("#nextHoleButton");
//
//        for (int i = 0; i < this.lade.getParValues().get(3) - 1; i++) {
//            clickOn("#removeThrowButton");
//        }
//        Button removeThrowButton3 = lookup("#removeThrowButton").query();
//        assertTrue(removeThrowButton3.isDisable());
//    }
//
//    /**
//    * previousHoleButton should be invisible when current hole equals 1
//    */
//    @Test
//    public void invisiblePreviousHoleButton(){
//        setupPageLade(this.playerName);
//        Button previousHoleButton = lookup("#previousHoleButton").query();
//        assertFalse(previousHoleButton.isVisible());
//
//        clickOn("#nextHoleButton");
//
//        Button previousHoleButtonVisible = lookup("#previousHoleButton").query();
//        assertTrue(previousHoleButtonVisible.isVisible());
//    }
//
//    /**
//     * nextHoleButton should be invisible at the last hole of the course
//     */
//
//    @Test
//    public void testIncrementTotalScore(){
//        setupPageLade(this.playerName);
//        int increment = 10;
//        for (int i = 0; i < increment; i++){
//            clickOn("#addThrowButton");
//        }
//        clickOn("#nextHoleButton");
//        Label l = (Label) lookup("#totalScoreLabel").query();
//        assertEquals("Total Score: " + increment, l.getText());
//    }
//
//    /**
//     * Total score should equal to the negative number of the amount of times nextHoleButton has been pressed
//     */
//    @Test
//    public void testDecrementTotalScore(){
//        setupPageLade(this.playerName);
//        int decrement = 3;
//        clickOn("#nextHoleButton");
//        for (int i = 0; i < decrement; i++){
//            clickOn("#removeThrowButton");
//        }
//        clickOn("#nextHoleButton");
//        Label l = (Label) lookup("#totalScoreLabel").query();
//        assertEquals("Total Score: -" + decrement, l.getText());
//    }
//
//    /**
//     * The submit button should bed visible at the last hole of the course
//     */
//    @Test
//    public void submitButton(){
//        setupPageLade(this.playerName);
//        iterateThroughEntireCourse(this.lade);
//        Button submitButton = lookup("Submit").query();
//        assertEquals("Submit", submitButton.getText());
//        assertTrue(submitButton.isVisible());
//    }
//
//    /**
//     * nextHoleButton should be invisible at the last hole of the Lade course
//     */
//    @Test
//    public void invisibleNextHoleButtonLade(){
//        setupPageLade(this.playerName);
//        iterateThroughEntireCourse(this.lade);
//        Button nextHoleButton = (Button) lookup("#nextHoleButton").query();
//        assertFalse(nextHoleButton.isVisible());
//    }
//
//    /**
//     * nextHoleBUtton should be invisible at the last hole of the Dragvoll course
//     */
//    @Test 
//    public void invisibleNextHoleButtonDragvoll(){
//        setupPageDragvoll(this.playerName);
//        iterateThroughEntireCourse(this.dragvoll);
//        Button nextHoleButton = (Button) lookup("#nextHoleButton").query();
//        assertFalse(nextHoleButton.isVisible());
//    }
//
//    /**
//     * Iterates through all holes for a course. Result is that the final hole of the page is displayed
//     * @param course The course that will be iterated through
//     */
//    private void iterateThroughEntireCourse(Course course){
//        for (int i = 0; i < course.getNumberOfHoles() - 1; i++){
//            clickOn("#nextHoleButton");
//        }
//    }
//
//    /**
//    * Helper method for taking the test to the scorecard page. 
//    * Lade is selected as course.
//    * @param name name of player
//    */ 
//    private void setupPageLade(String name){
//        clickOn("#playerNameTextField").write(name);
//        clickOn("#pickCourseMenu");
//        type(KeyCode.DOWN);
//        type(KeyCode.ENTER); //selects the Lade course
//        clickOn("#playButton");
//    }
//
//    /**
//    * Helper method for taking the test to the scorecard page. 
//    * Dragvoll is selected as course.
//    * @param name name of player
//    */ 
//    private void setupPageDragvoll(String name){
//        clickOn("#playerNameTextField").write(name);
//        clickOn("#pickCourseMenu");
//        type(KeyCode.DOWN);
//        type(KeyCode.DOWN);
//        type(KeyCode.ENTER);
//        clickOn("#playButton");
//    }
//}