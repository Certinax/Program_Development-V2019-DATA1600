module data {

    requires logic;

    exports com.data.client;
    opens com.data.client to logic;
}