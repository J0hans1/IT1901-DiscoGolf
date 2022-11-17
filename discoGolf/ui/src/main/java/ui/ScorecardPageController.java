package ui;

import discoGolf.core.Scorecard;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 * JavaFX controller for displaying the scorecard ui.
 *
 * @author @Billy Barret
 * @version 1.0
 * @since 2022-09-21
 */
public class ScorecardPageController {

  private Scorecard scorecard;
  private DataAccess access = new DataAccess();

  @FXML
  public Label currentCourseLabel;
  @FXML
  public Label displayPlayerName;
  @FXML
  public Label currentHole;
  @FXML
  public Label currentScore;
  @FXML
  public Label totalScoreLabel;
  @FXML
  public Label currentHoleParLabel;
  @FXML
  public Button previousHoleButton;
  @FXML
  public Button nextHoleButton;
  @FXML
  public Button submitBtn;
  @FXML
  public Button removeThrowButton;
  @FXML
  private Parent root;
  @FXML
  private Scene scene;
  @FXML
  private Stage stage;

  /**
   * create a scorecard object for current play.
   * refreshes all labels by running refreshDisplay().
   *
   * @param newScorecard the scorecard object to be used for the current play
   */
  public void getPreviousControllerInfo(Scorecard newScorecard) {
    scorecard = newScorecard;
    displayPlayerName.setText("Name: " + scorecard.getPlayerName());
    currentCourseLabel.setText("Course: " + scorecard.getCourseName());
    refreshDisplay();
  }

  /**
   * Sends the scorecard data to the database via the DataAccess class.
   *
   * @param event the event that triggers the the method
   * @throws IOException if conversion from scorecard.java to .json fails
   */
  public void handleSubmit(ActionEvent event) throws IOException {
    try {
      System.out.println(access.requestPostingScorecard(scorecard));
      goBackToMainPage(event, false);
    } catch (Exception e) {
      System.out.println("Error: " + e);
    }
  }

  /**
   * Cancel a started scorecard and go back to the main page.
   *
   * @param event the event that triggers the the method
   */
  @FXML
  public void handleCancel(ActionEvent event) {
    goBackToMainPage(event, true);
  }

  /**
   * Takes user to the main page.
   *
   * @param event the event that triggers the the method
   */
  private void goBackToMainPage(ActionEvent event, boolean isCancel) {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MainPage.fxml"));
      root = fxmlLoader.load();
      if (!isCancel) {
        MainPageController mainPageController = fxmlLoader.getController();
        mainPageController.displayScorecardFeedback();
      }
      stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Refreshes the content of the display.
   * Changes the labels content, button-labels and button visibility.
   */
  private void refreshDisplay() {
    updateInfoDisplay();
    handleBtnVisibilty();
  }

  /**
   * Handles wether the next, previous and submit buttons should be visible or not.
   */
  private void handleBtnVisibilty() {
    previousHoleButton.setVisible(scorecard.getCurrentHole() != 1);
    nextHoleButton.setVisible(scorecard.getCurrentHole() != scorecard.getCourseSize());
    submitBtn.setVisible(scorecard.getCurrentHole() == scorecard.getCourseSize());
    removeThrowButton.setDisable(scorecard.getCurrentHoleThrows() == 1);
  }

  /**
   * Updates textlabels and buttonlabels at the Hole display.
   */
  private void updateInfoDisplay() {
    currentHoleParLabel.setText("Par: " + Integer.toString(scorecard.getCurrentHolePar()));
    previousHoleButton.setText("Prev Hole: " + Integer.toString(scorecard.getCurrentHole() - 1));
    nextHoleButton.setText("Next Hole: " + Integer.toString(scorecard.getCurrentHole() + 1));
    currentHole.setText("Current Hole: " + Integer.toString(scorecard.getCurrentHole()) + "/" + Integer.toString(scorecard.getCourseSize()));
    currentScore.setText(Integer.toString(scorecard.getCurrentHoleThrows()));
  }

  /**
   * Adds throws to the current hole.
   * Refreshes the UI by.
   * running refreshDisplay() after the change.
   */
  public void addThrow() {
    scorecard.getCurrentHoleInstance().addThrow();
    refreshDisplay();
  }

  /**
   * Removes throws at the current hole.
   * Refreshes the UI by running refreshDisplay() after the change.
   */
  public void removeThrow() {
    scorecard.getCurrentHoleInstance().removeThrow();
    refreshDisplay();
  }

  /**
   * Moves to the next hole.
   * Refreshes the UI by running refreshDisplay().
   */
  public void nextHole() {
    scorecard.nextHole();
    refreshDisplay();
    totalScoreLabel.setText("Total Score: " + Integer.toString(scorecard.getScore()));
  }

  /**
   * Moves to the previous hole.
   * Refreshes the UI by running refreshDisplay().
   */
  public void previousHole() {
    scorecard.previousHole();
    refreshDisplay();
    totalScoreLabel.setText("Total Score: " + Integer.toString(scorecard.getScore()));
  }
}
