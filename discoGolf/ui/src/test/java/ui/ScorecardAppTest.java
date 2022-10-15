// package ui;

// import static org.junit.Assert.assertEquals;

// import org.junit.Test;
// import org.junit.jupiter.api.BeforeEach;
// import org.testfx.framework.junit5.ApplicationTest;

// import discoGolf.core.Course;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.stage.Stage;

// public class ScorecardAppTest extends ApplicationTest{
//      /**
//      Method for starting the javafx application
//      @param stage javafx Stage object
//      */

//     private ScorecardPageController controller;
//     private Course c;

//     @Override
//     public void start(Stage stage) throws Exception {
//         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ScorecardTest.fxml"));
//         Parent parent = fxmlLoader.load();
//         stage.setScene(new Scene(parent));
//         this.controller = fxmlLoader.getController();
//         stage.show();
//     }

//     @BeforeEach
//     public void setup(){
//         this.c = new Course(null, null);
//     }

//     @Test
//     public void testUI() throws InterruptedException{
//         clickOn("#addThrowButton");
//         Thread.sleep(1000);
//         assertEquals(new String("Hei"), this.c);
//     }
// }




package ui;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.ComboBoxMatchers;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.util.WaitForAsyncUtils;

import discoGolf.core.Course;
import discoGolf.core.Scorecard;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class ScorecardAppTest extends ApplicationTest {
    
    private Course lade;
    private Course dragvoll;
    private ScorecardPageController controller;

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
        lade = new Course("Lade",new ArrayList<>(Arrays.asList(3,4,3,4,3,4,3,4,3)));
        dragvoll = new Course("Dragvoll", new ArrayList<>(Arrays.asList(3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4)));
    }

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

    // @Test 
    // public void testUnsuccessfulSubmit(){
    //     assertThrows(IllegalStateException.class, ()->{
    //         clickOn("#fxmlNameOfPlayer").write("Bob");
    //         clickOn("#playButton");
    //         WaitForAsyncUtils.waitForFxEvents();
    //     });
    // }


   
}
