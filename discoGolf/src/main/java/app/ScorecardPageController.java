package app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ScorecardPageController {

    @FXML
    Label currentScore;

    private Scorecard scorecard;

    public void getPreviousControllerInfo(String nameOfPlayer, int numberOfHoles) {
        scorecard = new Scorecard(nameOfPlayer, 18); //Create new scorecard
        
        displayNameOfPlayer.setText("Name: " + scorecard.getNameOfPlayer()); //Update name of player label

        //TODO: update name of course label
        
        updateInfoDisplay(); //Update all other labels
    }

    @FXML
    Label displayNameOfPlayer;

    // private void updateInfoDisplay() {
    //     displayNameOfPlayer.setText(scorecard.getNameOfPlayer());
    // }

    private void updateInfoDisplay() { //Update all labels

        /* Needs to be updated often:
        - current hole
        - current score
        - current par
        - next hole
        - previous hole

        */
        currentHole.setText("Current Hole: " + Integer.toString(scorecard.getCurrentHole()));
        System.out.println(scorecard.getNameOfPlayer());

        printCurrent();
    }

    public void addThrow() { 
        scorecard.addThrow();
        currentScore.setText(Integer.toString(scorecard.getCurrentHoleScore()));
        printCurrent();
    }

    public void removeThrow() {
        scorecard.removeThrow();
        currentScore.setText(Integer.toString(scorecard.getCurrentHoleScore()));
        printCurrent();
    }
    
    
    @FXML
    Label currentHole;

    public void nextHole() {
        scorecard.nextHole();
        currentScore.setText(Integer.toString(scorecard.getCurrentHoleScore()));
        printCurrent();

        currentHole.setText(Integer.toString(scorecard.getCurrentHole())); //update current hole label
    }

    public void previousHole() {
        scorecard.previousHole();
        currentScore.setText(Integer.toString(scorecard.getCurrentHoleScore()));
        printCurrent();

        currentHole.setText(Integer.toString(scorecard.getCurrentHole())); //update current hole label
    }





    public void printCurrent() {
        System.out.println("Current hole: " + scorecard.getCurrentHole());
        System.out.println("Current Score: " + scorecard.getCurrentHoleScore()); 
    }

    


}
