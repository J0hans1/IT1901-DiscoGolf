package ui;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import discoGolf.core.Data;
import discoGolf.core.Leaderboard;
import discoGolf.core.Scorecard;
import discoGolf.json.DiscoGolfPersistence;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * JavaFX controller to display the leaderboard page of the JavaFX application
 * @author Ulrik Isdahl and Jakob Oppland
 * @version 1.0
 * @since 2022-09-21
 */
public class LeaderboardPageController {
  private Leaderboard leaderboard;
  private ObservableList<ScorecardsModel> scorecardsModel = FXCollections.observableArrayList();

  @FXML  
  private TableView<ScorecardsModel> leaderboardTableView;
  @FXML 
  private TableColumn<ScorecardsModel, Integer> position, totalScore; 
  @FXML 
  private TableColumn<ScorecardsModel, String> playerName;  
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
  @FXML
  public void initialize() throws IOException, URISyntaxException{    
    DiscoGolfPersistence db = new DiscoGolfPersistence();
    Data data = new Data();
    data = db.readData();
    this.leaderboard = new Leaderboard(data);
    selectCourseDropdown.getItems().add("Lade");
    selectCourseDropdown.getItems().add("Dragvoll");
    displayLeaderboard();
  }

  public void displayLeaderboard() {
    String chosenCourse = selectCourseDropdown.getValue();
    ArrayList<Scorecard> chosenCourseList = leaderboard.getLeaderboardForCourse(chosenCourse);
 
    for (int i = 0; i < chosenCourseList.size(); i++) {
      Scorecard scorecard = chosenCourseList.get(i);
      scorecardsModel.add(new ScorecardsModel(i+1, scorecard.getPlayerName(), scorecard.getTotalScore()));
    }
    position.setCellValueFactory(new PropertyValueFactory<>("Position"));
    playerName.setCellValueFactory(new PropertyValueFactory<>("PlayerName"));
    totalScore.setCellValueFactory(new PropertyValueFactory<>("TotalScore"));
    leaderboardTableView.setItems(scorecardsModel);
  }

}
