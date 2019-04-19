module gui {

    requires logic;
    requires data;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.gui.controllers to javafx.fxml;

    exports com.gui;
}