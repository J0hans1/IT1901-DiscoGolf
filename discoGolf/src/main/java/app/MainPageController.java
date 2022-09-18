package app;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MainPageController {

    @FXML
    Button playButton;

    @FXML
    TextField fxmlNameOfPlayer;

    private String nameOfPlayer;

    @FXML
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
        pickCourseMenu.getItems().addAll("Lade", "Dragvoll", "testies");
    }

}
