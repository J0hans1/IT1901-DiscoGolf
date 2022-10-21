package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;


/**
 * JavaFX controller to display the leaderboard page of the JavaFX application
 * @author Ulrik Isdahl and Jakob Oppland
 * @version 1.0
 * @since 2022-09-21
 */
public class LeaderboardPageController {
  @FXML  
  public TableView leaderboardTableView;
  @FXML
  public Button homeButton;
  @FXML
  public ComboBox<String> selectCourseDropdown;


  public void initialize(){

  }



}
