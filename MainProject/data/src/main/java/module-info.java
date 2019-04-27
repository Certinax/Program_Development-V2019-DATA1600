module data {

    exports com.data.client;
    requires logic;

    opens com.data.client to logic;
}