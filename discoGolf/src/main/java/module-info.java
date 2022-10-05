module app {
    requires javafx.controls;
    requires javafx.fxml;

    requires transitive com.fasterxml.jackson.databind;
    requires transitive com.fasterxml.jackson.core;

    opens app to javafx.graphics, javafx.fxml;
}
