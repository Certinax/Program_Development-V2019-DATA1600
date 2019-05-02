module data {

    /**
     * <h1>Module - Data</h1>
     *
     * Module containing all data-classes. Highly dependent on the logic-module
     * for Error handling and validating the data.
     */

    requires logic;
    requires javafx.graphics;

    exports com.data;
    exports com.data.clients;
    exports com.data.work;
    exports com.data.factory;

    opens com.data.clients to logic;
    opens com.data.work to logic;
}