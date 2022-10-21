package ui;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.github.javaparser.ast.observer.Observable;

import discoGolf.core.Data;
import discoGolf.core.Leaderboard;
import discoGolf.core.Scorecard;
import discoGolf.json.DiscoGolfPersistence;
import groovy.util.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


/**
 * JavaFX controller to display the leaderboard page of the JavaFX application
 * @author Ulrik Isdahl and Jakob Oppland
 * @version 1.0
 * @since 2022-09-21
 */
public class LeaderboardPageController {
  private Leaderboard leaderboard;

  @FXML  
  private TableView<Scorecard> leaderboardTableView;
  @FXML 
  private TableColumn<Scorecard, Integer> positionColumn, totalScoreColumn; 
  @FXML 
  private TableColumn<Scorecard, String> nameColumn;  
  @FXML
  public Button homeButton;
  @FXML
  public ComboBox<String> selectCourseDropdown;

  /**
   * Initialize page by reading data saved and 
   * creating a new Leaderboard object.
   * @throws IOException
   * @throws URISyntaxException
   */
  public void initialize() throws IOException, URISyntaxException{
    DiscoGolfPersistence db = new DiscoGolfPersistence();
    Data data = new Data();
    data = db.readData();
    this.leaderboard = new Leaderboard(data);
    displayChosenLeadeboard();
  }

  public void displayChosenLeadeboard() {
    String chosenCourse = selectCourseDropdown.getValue();
    ArrayList<Scorecard> chosenCourseList = leaderboard.getLeaderboardForCourse(chosenCourse);
    //positionColumn.setCellValueFactory(new );
    for (int i = 0; i < chosenCourseList.size(); i++) {

    }
  }







}
