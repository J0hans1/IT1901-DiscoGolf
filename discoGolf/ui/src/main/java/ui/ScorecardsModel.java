package ui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Model for displaying scorecards in a leaderboard in the JavaFX application.
 *
 * @author Jakob Opland
 * @version 1.0
 * @since 2022-09-21
 */
public class ScorecardsModel {
  private SimpleIntegerProperty position;
  private SimpleStringProperty playerName;
  private SimpleIntegerProperty totalScore;

  /**
   * Constructor for the ScorecardsModel class.
   *
   * @param position   the position of the player in the scorecard
   * @param playerName the name of the player
   * @param totalScore the total score of the player
   */
  public ScorecardsModel(int position, String playerName, int totalScore) {
    this.position = new SimpleIntegerProperty(position);
    this.playerName = new SimpleStringProperty(playerName);
    this.totalScore = new SimpleIntegerProperty(totalScore);
  }

  /**
   * Getter for the position in leaderboard.
   *
   * @return the position of the player in the leaderboard.
   */
  public int getPosition() {
    return position.get();
  }

  /**
   * sets the position of the player in the leaderboard.
   *
   * @param position the position of the player in the leaderboard.
   */
  public void setPosition(int position) {
    this.position = new SimpleIntegerProperty(position);
  }

  /**
   * The name of the player.
   *
   * @return the name of the player.
   */
  public String getPlayerName() {
    return playerName.get();
  }

  /**
   * sets the name of the player.
   *
   * @param playerName the name of the player.
   */
  public void setPlayerName(String playerName) {
    this.playerName = new SimpleStringProperty(playerName);
  }

  /**
   * The total score of the player.
   *
   * @return the total score of the player.
   */
  public int getTotalScore() {
    return totalScore.get();
  }

  /**
   * sets the total score of the player.
   *
   * @param totalScore the total score of the player
   */
  public void setTotalScore(int totalScore) {
    this.totalScore = new SimpleIntegerProperty(totalScore);
  }

}