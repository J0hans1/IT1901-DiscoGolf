package ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import discoGolf.core.Course;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DiscoGolfAppTest extends ApplicationTest {

    private Course lade;
    private Course dragvoll;
    @FXML
    public ComboBox<String> pickCourseMenu;
    @FXML
    private MainPageController controller;
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPageTest.fxml"));
        Parent parent = fxmlLoader.load();
        stage.setScene(new Scene(parent));
        this.controller = fxmlLoader.getController();
        stage.show();
    }

    @BeforeEach
    public void setUpItems(){
        lade = new Course("Lade",new ArrayList<>(Arrays.asList(3,4,3,4,3,4,3,4,3)));
        dragvoll = new Course("Dragvoll", new ArrayList<>(Arrays.asList(3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4)));
        this.pickCourseMenu = lookup("#pickCourseMenu").query();
    }

    @Test
    @DisplayName("Test that the initialized items in controller are not null")
    public void testContollerItems(){
        assertNotNull(this.controller);
        assertNotNull(lookup("Play"));
        assertNotNull(this.pickCourseMenu);
    }
    
    @Test
    @DisplayName("Test that the combobox has the correct values")
    public void testCourseSelection(){
        assertEquals(pickCourseMenu.getItems().get(0), lade.getCourseName());
        assertEquals(pickCourseMenu.getItems().get(1), dragvoll.getCourseName());
    }

    @Test
    @DisplayName("Test that it works to write values into the input field")
    public void testInputField(){
        clickOn("#fxmlNameOfPlayer").write("Bob");
        TextField nameInput = lookup("#fxmlNameOfPlayer").query();
        WaitForAsyncUtils.waitForFxEvents();
        assertNotNull(nameInput);
    }

    
    @Test
    @DisplayName("Test tha size of the combobox")
    public void testDropdownSize(){
        assertEquals(2, this.pickCourseMenu.getItems().size());
    }

}
