package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import discoGolf.core.Course;
import discoGolf.core.Scorecard;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;


public class DiscoGolfAppTest extends ApplicationTest {
    
    private MainPageController controller;
    private ComboBox<String> pickCourseMenu;
    private Course lade;

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
        // Scorecard scorecard = new Scorecard(null, null);
        lade = new Course("Lade",new ArrayList<>(Arrays.asList(3,4,3,4,3,4,3,4,3)));
        pickCourseMenu = lookup("#pickCourseMenu").query();
    }

    @Test
   // @DisplayName("Test that the initialized items in controller are not null")
    public void testContollerItems(){
        assertNotNull(this.controller);
    }
    
    @Test
    public void testCourseSelection(){
        assertEquals(pickCourseMenu.getItems().get(0), lade.toString());
        assertEquals(pickCourseMenu.getItems().get(1), lade.toString());
    }

    @Test 
    public void testUnsuccessfulPlay(){
        clickOn("#fxmlNameOfPlayer").write("Bob");
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("#playButton");
    }

    // @Test 
    // public void checkSelectedcCourse(){
    //     clickOn("#fxmlNameOfPlayer").write("Ulrik");
    //     WaitForAsyncUtils.waitForFxEvents();
    //     clickOn("#pickCourseMenu");
    //     WaitForAsyncUtils.waitForFxEvents();
    //     //clickOn(pickCourseMenu.getItems().get(0));
    // }

    // @Test
    // public void failTest(){
    // }



    //Skrive test for findSelectedCourse - 


}
