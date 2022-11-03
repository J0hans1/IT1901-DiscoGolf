package ui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * 
 */
public class ScorecardsModel {
  private SimpleIntegerProperty position;
  private SimpleStringProperty playerName;
  private SimpleIntegerProperty totalScore;

  public ScorecardsModel(int position, String playerName, int totalScore) {
    this.position = new SimpleIntegerProperty(position);
    this.playerName = new SimpleStringProperty(playerName);
    this.totalScore = new SimpleIntegerProperty(totalScore);
  }

  public int getPosition() {
    return position.get();
  }

  public void setPosition(int position) {
    this.position = new SimpleIntegerProperty(position);
  }

  public String getPlayerName() {
    return playerName.get();
  }

  public void setPlayerName(String playerName) {
    this.playerName = new SimpleStringProperty(playerName);
  }

  public int getTotalScore() {
    return totalScore.get();
  }

  public void setTotalScore(int totalScore) {
    this.totalScore = new SimpleIntegerProperty(totalScore);
  }

  
}