module logic {

    /**
     * <h1>Module - Logic</h1>
     *
     * The Logic module contains program logic that is non-dependent on other modules.
     * This module SHAL NOT require any modules or libraries that cannot be easily integrated
     * through Maven (or similar applications).
     *
     */

    requires javafx.controls;
    requires javafx.fxml;

    exports com.logic.io.reader;
    exports com.logic.io.writer;
    exports com.logic.customTextFields;
    exports com.logic.utilities;
    exports com.logic.utilities.exceptions;
    exports com.logic.utilities.validators;
    exports com.logic.concurrency;
    exports com.logic.filePaths;
}