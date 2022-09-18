package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MainPageController {

    @FXML
    Button playButton;

    @FXML
    TextField fxmlNameOfPlayer;

    private String nameOfPlayer;

    public void setNameOfPlayer() {
        this.nameOfPlayer = fxmlNameOfPlayer.getText();
        addCourseToList(nameOfPlayer);
    }

    @FXML
    ComboBox<String> pickCourseMenu;

    public void addCourseToList(String course) {
        pickCourseMenu.getItems().add(course);
    }

    public void initialize() {
        Course Lade = new Course(9, "Lade");
        for (int hole = 1; hole <= Lade.getNumberOfHoles(); hole++) {
            Lade.setParForHole(hole, 3);
        }

        Course Dragvoll = new Course(18, "Dragvoll");
        for (int hole = 1; hole <= Dragvoll.getNumberOfHoles(); hole++) {
            Dragvoll.setParForHole(hole, 3);
        }

        pickCourseMenu.getItems().add(Lade.getCourseName() + ": " + Lade.getNumberOfHoles() + " holes");
        pickCourseMenu.getItems().add(Dragvoll.getCourseName() + ": " + Dragvoll.getNumberOfHoles() + " holes");
    }


    @FXML private Parent root;
    @FXML private Scene scene;
    @FXML private Stage stage;

    public void changeSceneToScorecard(ActionEvent event) {
        setNameOfPlayer();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Scorecard.fxml"));
            root = fxmlLoader.load();

            ScorecardPageController nextController = fxmlLoader.getController();
            nextController.getPreviousControllerInfo(nameOfPlayer, 18);

            stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println("Failed to create new Window." + e);
        }
    }
    
}

