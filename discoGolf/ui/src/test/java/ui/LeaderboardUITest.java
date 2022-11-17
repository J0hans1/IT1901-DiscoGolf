package ui;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import javafx.scene.input.KeyCode;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import discoGolf.core.Course;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.io.IOException;
import discoGolf.core.Course;

/**
 * Test class for testing leaderboard page of disco golf app using TestFX
 * @author Ulrik Isdahl and Billy Barret
 * @version 1.0
 * @since 2022-11-09
 */
public class LeaderboardUITest extends ApplicationTest {
  private Course lade;
  private Course dragvoll;
  @FXML
  private MainPageController controller;//LeaderboardPageController controller;
  @FXML
  private LeaderboardPageController leaderboardController;
  @FXML
  private ComboBox<String> selectCourseDropdown;
  @FXML
  private Button homeButton;
  
  private TestServer server;
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
  * Sets up internal variables for the test. Starts the mock server. 
  */
  @BeforeEach
  public void setUpItems() throws IOException{
    this.lade = new Course("Lade",new ArrayList<>(Arrays.asList(3,4,3,4,3,4,3,4,3)));
    this.dragvoll = new Course("Dragvoll", new ArrayList<>(Arrays.asList(3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4)));
    this.server = new TestServer();
    this.server.setup();
    clickOn("#leaderboardButton");  
    initializeElements();
  }

  /**
   * Stops the mock server from running after each test
   */
  @AfterEach
  public void closeServer(){
    this.server.closeServer();
  }

  /**
   * There should be five rows of scorecards in the lade leaderboard
   */
  @Test
  public void testGetData() throws IOException {
    TableView<ScorecardsModel> tw = lookup("#leaderboardTableView").query();
    assertEquals(5, tw.getItems().size());
  }
 
  /**
   * There should be five rows of scorecards in the dragvoll leaderboard
   */
  @Test
  public void numberOfScorecardsDragvoll(){
    clickOn("#selectCourseDropdown");
    type(KeyCode.DOWN);
    type(KeyCode.ENTER);
    TableView<ScorecardsModel> tw = lookup("#leaderboardTableView").query();
    assertEquals(5, tw.getItems().size());
  }

  /**
   * The player name of the first row should be "Ulrik0"
   */
  @Test
  public void bestRankedScorecard(){
    TableView<ScorecardsModel> tw = lookup("#leaderboardTableView").query();
    assertEquals("Ulrik0", tw.getItems().get(0).getPlayerName());
  }

  /**
  * Check that the initialized items in the controller are not null
  */
  @Test
  @DisplayName("Test that the initialized items in controller are not null")
  public void testContollerItems(){
      assertNotNull(this.leaderboardController);
      assertNotNull(this.selectCourseDropdown);
      assertNotNull(this.homeButton);
  }

  /**
   * selectCourseDropdown should have two items, one for each course
   */
  @Test
  public void selectCourseDropdownItems(){
    assertEquals(2, this.selectCourseDropdown.getItems().size());
    assertEquals(this.selectCourseDropdown.getItems().get(0), "Lade");
    assertEquals(this.selectCourseDropdown.getItems().get(1), "Dragvoll");
  }

  /**
   * Saves the elements in the LeaderboardPageController as variables
   */
  private void initializeElements(){
    this.selectCourseDropdown = lookup("#selectCourseDropdown").query();
    this.homeButton = lookup("#homeButton").query();
    instansiateLeaderboardPageController();
  }

  /**
   * Loads the LeaderBoardPageController and saves it as a variable
   */
  private void instansiateLeaderboardPageController(){
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Leaderboard.fxml"));
      fxmlLoader.load();
      this.leaderboardController = fxmlLoader.getController();
    } catch (IOException e){
      e.printStackTrace();
    }
  }
    
}
