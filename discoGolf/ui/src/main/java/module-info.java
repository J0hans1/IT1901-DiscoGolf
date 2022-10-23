module calc.ui {
    requires discoGolf.core;
    requires javafx.controls;
    requires javafx.fxml;
    requires fxutil;

    opens ui to javafx.graphics, javafx.fxml, javafx.base;
}
