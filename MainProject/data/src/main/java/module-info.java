module data {

    /**
     * <h1>Module - Data</h1>
     *
     * Module containing all data-classes. Highly dependent on the logic-module
     * for Error handling and validating the data.
     */

    requires logic;

    exports com.data.clients;
    exports com.data.work;

    opens com.data.clients to logic;
}