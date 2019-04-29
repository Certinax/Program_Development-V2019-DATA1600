module gui {

    /**
     * <h1>Module - GUI</h1>
     *
     * The GUI module contains all the programs View Controllers and other classes used to handle
     * the GUI. This module is highly dependent on The Logic and Data modules to function properly.
     */

    requires logic;
    requires data;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.gui.controllers to javafx.fxml;

    exports com.gui;
}