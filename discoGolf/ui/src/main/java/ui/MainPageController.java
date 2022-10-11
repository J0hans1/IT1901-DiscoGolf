package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import discoGolf.core.Course;
import discoGolf.core.Scorecard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader; 
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class MainPageController {

    private List<Course> availableCourses = new ArrayList<>();
    private String nameOfPlayer;
    
    @FXML 
    private Parent root;
    @FXML 
    private Scene scene;
    @FXML 
    private Stage stage;
    @FXML
    public Button playButton;
    @FXML
    public TextField fxmlNameOfPlayer;
    @FXML
    public ComboBox<String> pickCourseMenu;


    /*
    - sets name of the player 
    */
    public void setNameOfPlayer() {
        this.nameOfPlayer = fxmlNameOfPlayer.getText();
    }

    /*
    - 
    */
    public void addCourseToList(String course) {
        pickCourseMenu.getItems().add(course);
    }


    /*
    * initializes template courses (lade and Dragvoll) to be available as a course for the player to choose
     */
    public void initialize() {
        Course Lade = new Course("Lade",new ArrayList<>(Arrays.asList(3,4,3,4,3,4,3,4,3)));
        Course Dragvoll = new Course("Dragvoll", new ArrayList<>(Arrays.asList(3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4)));

        availableCourses.add(Lade);
        availableCourses.add(Dragvoll);

        pickCourseMenu.getItems().add(Lade.toString());
        pickCourseMenu.getItems().add(Dragvoll.toString());
    }

    /*
    -
    */
    public Course findSelectedCourse() {
        String selectedCourse = pickCourseMenu.getValue();
        for (Course course : availableCourses) {
            if (course.toString().equals(selectedCourse)) {
                return course;
            }
        }
        return null;
    }

    /*
    -
    */
    public void changeSceneToScorecard(ActionEvent event) {
        setNameOfPlayer();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Scorecard.fxml"));
            root = fxmlLoader.load();

            ScorecardPageController nextController = fxmlLoader.getController();

            System.out.println(findSelectedCourse());
            Scorecard newScorecard = new Scorecard(findSelectedCourse(), nameOfPlayer);
            nextController.getPreviousControllerInfo(newScorecard); //Need to add selectedCourse
            stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println("Faed to create new Window." + e);
        }
    }
}

