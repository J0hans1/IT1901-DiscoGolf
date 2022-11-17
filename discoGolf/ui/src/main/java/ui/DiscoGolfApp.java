package ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Is the main class of the JavaFX UI. 
 * Responsible for starting the UI.
 * Responsible for loading the FXML file.
 *
 * @author Markus Johansen, Billy Barret og Ulrik Isdahl
 * @version 1.0
 * @since 2022-10-03
 * @see MainPageController
 */
public class DiscoGolfApp extends Application {

  /**
   * Loads the FXML file and prepares it for display.
   *
   * @param stage the stage to be displayed.
   * @throws IOException if the FXML file is not found.
   */
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MainPage.fxml"));
    Parent parent = fxmlLoader.load();
    stage.setScene(new Scene(parent));
    stage.show();
  }

  /**
   * Starts the JavaFX application.
   *
   * @param args the arguments passed to the application.
   */
  public static void main(String[] args) {
    launch();
  }
}