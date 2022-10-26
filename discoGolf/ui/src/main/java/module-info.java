module calc.ui {
    requires discoGolf.core;
    requires javafx.controls;
    requires javafx.fxml;
    requires fxutil;
    requires java.net.http;

    opens ui to javafx.graphics, javafx.fxml;
}
