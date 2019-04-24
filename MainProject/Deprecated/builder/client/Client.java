package client;

public abstract class Client {

    private String name;
    private String firstname;
    private String lastname;
    private String address;
    private int zipcode;
    private String city;
    private boolean employer = false;

    abstract static class Builder<T extends Builder<T>> {
        private String name;
        private String firstname;
        private String lastname;
        private String address;
        private int zipcode;
        private String city;


        public Builder(String name, String address, int zipcode, String city) {
            this.name = name;
            this.address = address;
            this.zipcode = zipcode;
            this.city = city;
        }


        public Builder(String firstname, String lastname, String address, int zipcode, String city) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.address = address;
            this.zipcode = zipcode;
            this.city = city;
        }

        abstract Client build();

        protected abstract T self();
    }

    protected Client(Builder<?> builder) {
        this.name = builder.name;
        this.address = builder.address;
        this.zipcode = builder.zipcode;
        this.city = builder.city;
    }

    @Override
    public String toString() {
        if(!employer) {
            return "Client{" +
                    "firstname='" + firstname + '\'' +
                    ", lastname='" + lastname + '\'' +
                    ", address='" + address + '\'' +
                    ", zipcode=" + zipcode +
                    ", city='" + city + '\'' +
                    '}';
        } else {
            return "Client{" +
                    "name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", zipcode=" + zipcode +
                    ", city='" + city + '\'' +
                    ", employer=" + employer +
                    '}';
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
