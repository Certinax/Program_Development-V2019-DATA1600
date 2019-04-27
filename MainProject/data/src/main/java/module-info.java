module data {

    requires logic;

    exports com.data.clients;
    opens com.data.clients to logic;
}