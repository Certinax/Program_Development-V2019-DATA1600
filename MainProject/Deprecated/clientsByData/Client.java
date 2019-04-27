package com.data.clients;

import java.util.UUID;

public abstract class Client {

    private String name;
    private String firstname;
    private String lastname;
    private String address;
    private int zipcode;
    private String city;
    private boolean employer = false;

    // Id generator
    private UUID id;
    //private static final AtomicInteger idGenerator = new AtomicInteger(1000);

    // Constructor for Employers with only one name
    public Client(String name, String address, int zipcode, String city) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        employer = true;
    }

    // Constructor for Employees with first and lastname
    public Client(String firstname, String lastname, String address, int zipcode, String city) {
        this.id = UUID.randomUUID();
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
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
                    '}';
        }
    }

    public UUID getId() {
        return id;
    }

    public String getFirstname() { return firstname; }

    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZipcode() { return zipcode; }

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

