module calc.ui {
    requires core;
    requires javafx.controls;
    requires javafx.fxml;
    requires fxutil;

    opens ui to javafx.graphics, javafx.fxml;
}
