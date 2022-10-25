package ui;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import discoGolf.core.Course;
import discoGolf.core.Scorecard;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader; 
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;

/**
 * JavaFX controller to display the main page of the JavaFX application
 * @author Billy Barret and Ulrik Isdahl
 * @version 1.0
 * @since 2022-09-21
 */
public class MainPageController {

    private List<Course> availableCourses = new ArrayList<>();
    private String playerName;
    
    @FXML 
    private Parent root;
    @FXML 
    private Scene scene;
    @FXML 
    private Stage stage;
    @FXML
    public Button playButton;
    @FXML
    public TextField playerNameTextField;
    @FXML
    public ComboBox<String> pickCourseMenu;
    @FXML 
    public Pane scorecardFeedback;


    /**
     * Sets the playername variable to the text in the playerNameTextField at the main page
     * Will be used to create a scorecard object
     */
    public void setPlayerName() {
        this.playerName = playerNameTextField.getText();
    }   


    /**
     * Adds a String to a list of Strings which represents available courses
     * @param String course - a String containing the name of a course
     */
    public void addCourseToList(String course) {
        pickCourseMenu.getItems().add(course);
    }   


    /**
     * Initializes the list of available courses and some course objects is added to it
     * Adds the names of the courses to the pickCourseMenu
     */
    @FXML
    public void initialize() {
        Course Lade = new Course("Lade",new ArrayList<>(Arrays.asList(3,4,3,4,3,4,3,4,3)));
        Course Dragvoll = new Course("Dragvoll", new ArrayList<>(Arrays.asList(3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4,3,4)));
        availableCourses.add(Lade);
        availableCourses.add(Dragvoll);
        pickCourseMenu.getItems().add(Lade.getCourseName());
        pickCourseMenu.getItems().add(Dragvoll.getCourseName());

        pickCourseMenu.setOnAction((ActionEvent e) -> {
            pickCourseMenu.setStyle("-fx-border-width: 0");
        });
    }

    /**
     * Finds the course that is selected in the pickCourseMenu
     * @return course object which name is selected in the pickCourseMenu
     * @return null if no course is selected
     */
    public Course findSelectedCourse() {
        String selectedCourse = pickCourseMenu.getValue();
        for (Course course : availableCourses) {
            if (course.getCourseName().equals(selectedCourse)) {
                return course;
            }
        }
        return null;
    }

    /**
     * Displays the scorecardFeedback pane for 2.5 seconds before making it invisible
     */
    public void displayScorecardFeedback(){
        Platform.runLater(()->{
            scorecardFeedback.setVisible(true);
        });
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2.5), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                scorecardFeedback.setVisible(false);
            }
            
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }


    /**
     * Creates a scorecard object with the playername and selected course
     * Loads the scorecard page
     * @param event is the event that triggers the change of scenes
     * @throws IOException if reading the fxml file failed
     * @throws IllegalStateException if no course is selected
     * @throws IllegalArgumentException if playerName is invalid
     */
    @FXML
    public void handleScorecardButton(ActionEvent event) {
        setPlayerName();

        if (findSelectedCourse() == null){
            pickCourseMenu.setStyle("-fx-border-color: red; -fx-border-width: 2");
            pickCourseMenu.setPromptText("Please select a course ");
        } else {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Scorecard.fxml"));
                root = fxmlLoader.load();
                
                ScorecardPageController nextController = fxmlLoader.getController();
                Scorecard newScorecard = new Scorecard(findSelectedCourse(), playerName);
                nextController.getPreviousControllerInfo(newScorecard); //Need to add selectedCourse
                stage = (Stage)((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                
            } catch (IOException e) {
                System.out.println("Failed to create new Window." + e);
            } catch (IllegalArgumentException e){
                playerNameTextField.setStyle("-fx-border-color: red; -fx-border-width: 2");
                playerNameTextField.setText("");
                playerNameTextField.setPromptText("Please write a valid name ");
            }
             
        }
    }

     /**
     * Loads the Leaderboard page 
     * @param event is the event that triggers the change of scenes
     * @throws URISyntaxException
     * @throws IOException if reading the fxml file failed
     */
    @FXML
    public void handleLeaderboardButton(ActionEvent event) throws URISyntaxException{
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Leaderboard.fxml"));
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

