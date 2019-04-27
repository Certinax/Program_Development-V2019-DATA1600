module logic {

    requires javafx.controls;
    requires javafx.fxml;
    requires data;

    exports com.logic;
    exports com.logic.io.reader;
    exports com.logic.io.writer;
    exports com.logic.customTextFields;
    exports com.logic.utilities.exceptions;
    exports com.logic.utilities.validators;
    exports com.logic.concurrency;
}