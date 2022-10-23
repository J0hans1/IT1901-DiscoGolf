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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;

/**
 * JavaFX controller to display the leaderboard page of the JavaFX application
 * @author Ulrik Isdahl and Jakob Oppland
 * @version 1.0
 * @since 2022-09-21
 */
public class LeaderboardPageController {
  private Leaderboard leaderboard;

  @FXML 
  private Parent root;
  @FXML 
  private Scene scene;
  @FXML 
  private Stage stage;
  @FXML  
  private TableView<ScorecardsModel> leaderboardTableView;
  @FXML 
  private TableColumn<ScorecardsModel, Integer> position, totalScore; 
  @FXML 
  private TableColumn<ScorecardsModel, String> playerName;  
  @FXML
  private Text noCourseFeedback;
  @FXML
  public Button homeButton;
  @FXML
  public ComboBox<String> selectCourseDropdown;
  
  /**
   * Initialize page by reading data saved and creating a new Leaderboard object.
   * Add the two different courses Lade and Dragvoll. Display the Lade leaderboard 
   * (default chosen course value) by calling displayLeadeboard.
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

  /**
   * Create a new Observable list, store the current value 
   * (the chosen course) from the comboBox, and displays the 
   * corret leaderboard from database based on this value. 
   */
  public void displayLeaderboard() {
    ObservableList<ScorecardsModel> scorecardsModel = FXCollections.observableArrayList();
    String chosenCourse = selectCourseDropdown.getValue();
    ArrayList<Scorecard> chosenCourseList = leaderboard.getLeaderboardForCourse(chosenCourse);

    noCourseFeedback.visibleProperty().set(false);
    if (chosenCourseList.isEmpty()) {
      noCourseFeedback.visibleProperty().set(true);
    }

    for (int i = 0; i < chosenCourseList.size(); i++) {
      Scorecard scorecard = chosenCourseList.get(i);
      scorecardsModel.add(new ScorecardsModel(i+1, scorecard.getPlayerName(), scorecard.getTotalScore()));
    }
    position.setCellValueFactory(new PropertyValueFactory<>("Position"));
    playerName.setCellValueFactory(new PropertyValueFactory<>("PlayerName"));
    totalScore.setCellValueFactory(new PropertyValueFactory<>("TotalScore"));
    leaderboardTableView.setItems(scorecardsModel);
  }

  /**
     * Loads the Main page
     * @param event is the event that triggers the change of scenes
     * @throws URISyntaxException
     * @throws IOException if reading the fxml file failed
     */
  @FXML
  public void handleHomeButton(ActionEvent event) throws URISyntaxException {
    try {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MainPage.fxml"));
        root = fxmlLoader.load();
        stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        System.out.println("Failed to create new Window." + e);
    }
  }
}
