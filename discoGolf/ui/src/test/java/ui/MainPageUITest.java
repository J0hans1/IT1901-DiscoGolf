package ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.IllegalArgumentException;

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

/**
 * Test class for testing Main page of disco golf app using TestFX
 * @author Ulrik Isdahl and Jakob Opland
 * @version 1.0
 * @since 2022-10-15
 */
public class MainPageUITest extends ApplicationTest {

    private String courseNameLade, courseNameDragvoll, playerName;
    private Course lade;
    private Course dragvoll;
    @FXML
    public ComboBox<String> pickCourseMenu;
    @FXML
    private MainPageController controller;
    @FXML
    public TextField playerNameTextField;
    @FXML
    public Button playButton;

    
    /**
     Method for starting the javafx application
     @param stage javafx Stage object
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        Parent parent = fxmlLoader.load();
        stage.setScene(new Scene(parent));
        this.controller = fxmlLoader.getController();
        stage.show();
    }

    /**
     * Sets up internal variables for the test
     */
    @BeforeEach
    public void setUpItems(){
        this.lade = new Course("Lade",new ArrayList<>(Arrays.asList(3,4,3,4,3,4,3,4,3)));
        this.dragvoll = new Course("Dragvoll", new ArrayList<>(Arrays.asList(3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4)));
        this.pickCourseMenu = lookup("#pickCourseMenu").query();
        this.playButton = lookup("#playButton").query();
        this.playerNameTextField = lookup("#playerNameTextField").query();
        this.playerName = "Bob";
        this.courseNameLade = "Lade";
        this.courseNameDragvoll = "Dragvoll";
    }

    /**
    * Test that the initialized items in the controller are not null
    */
    @Test
    public void testContollerItems(){
        assertNotNull(this.controller);
        assertNotNull(this.pickCourseMenu);
        assertNotNull(this.playButton);
        assertNotNull(this.playerNameTextField);
    }

    /**
    * Find the name written into #playerNameTextField and see if it matches with the name variable
    */
    @Test
    public void findInputName(){
        String name = "Marley";
        clickOn(this.playerNameTextField).write(name);
        TextField t = (TextField) lookup("#playerNameTextField").query();
        assertEquals(name, t.getText());
    }

    /**
    * Expects error prompt to appear based on the given input strings
    * @see assertThrowsInputName
    */
    @Test
    public void illegalInputNames(){
        String illegalName1 = "/*@";
        displayIllegalNamePrompt(illegalName1);

        String illegalName2 = "";
        displayIllegalNamePrompt(illegalName2);

        String illegalInput3 = "Mark/.";
        displayIllegalNamePrompt(illegalInput3);

        String illegalInput4 = ".";
        displayIllegalNamePrompt(illegalInput3);
    }

    /**
     * The value of the first select option should be "Lade"
     */
    @Test
    public void selectCourse(){
        clickOn(this.pickCourseMenu);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER); 

        assertEquals("Lade", this.pickCourseMenu.getValue());
    }

    /**
    * Check that the comboBox contains the expected elements
    */
    @Test
    public void checkComboBoxItems(){
        assertEquals(2, this.pickCourseMenu.getItems().size());
        assertEquals(this.pickCourseMenu.getItems().get(0), this.lade.getCourseName());
        assertEquals(this.pickCourseMenu.getItems().get(1), this.dragvoll.getCourseName());
    }
  
    /**
    * When illegal name is entered the text response "Please write a valid name" should appear in a prompt
    * @param inputString string that should prompt a message in playerNameTextField
    */
    private void displayIllegalNamePrompt(String inputString){
        clickOn(this.playerNameTextField).write(inputString);
        clickOn(this.pickCourseMenu);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER); 
        clickOn(this.playButton);

        TextField response = lookup("#playerNameTextField").query();
        assertEquals("Please write a valid name ", response.getPromptText());
    }
}
