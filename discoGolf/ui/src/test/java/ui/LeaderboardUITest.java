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
import javafx.scene.text.Text;


/**
 * Test class for testing Main page of disco golf app using TestFX
 * @author Ulrik Isdahl and Jakob Opland
 * @version 1.0
 * @since 2022-10-15
 */
public class LeaderboardUITest extends ApplicationTest {

  private Course lade;
  private Course dragvoll;
  @FXML
  private LeaderboardPageController controller;
  @FXML
  private ComboBox selectCourseDropdown;
  @FXML
  private Button homeButton;

  
  /**
    Method for starting the javafx application
    @param stage javafx Stage object
    */
  @Override
  public void start(Stage stage) throws Exception {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Leaderboard.fxml"));
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
      this.selectCourseDropdown = lookup("#selectCourseDropdown").query();
      this.homeButton = lookup("#homeButton").query();
  }

  /**
  * Test that the initialized items in the controller are not null
  */
  @Test
  @DisplayName("Test that the initialized items in controller are not null")
  public void testContollerItems(){
      assertNotNull(this.controller);
      assertNotNull(this.selectCourseDropdown);
      assertNotNull(this.homeButton);
  }

  /**
   * A feedback text should be displayed when there are no games recorded
   */
  @Test
  public void noGamesRecorded(){
    Text noCourseFeedback = lookup("#noCourseFeedback").query();
    assertEquals("No games recorded on this course", noCourseFeedback.getText());
  }

  /**
   * selectCourseDropdown should have two items, one for each course
   */
  @Test
  public void selectCourseDropdownItems(){
    assertEquals(2, this.selectCourseDropdown.getItems().size());
    assertEquals(this.selectCourseDropdown.getItems().get(0), this.lade.getCourseName());
    assertEquals(this.selectCourseDropdown.getItems().get(1), this.dragvoll.getCourseName());
  }
    
}
