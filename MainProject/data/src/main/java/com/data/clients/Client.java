package com.data.clients;

import java.io.Serializable;

/**
 * <h1>Client</h1>
 *
 * Abstract class for representing attributes found in both types of clients.
 * Utilizes the Builder-pattern to create objects
 *
 * @author Mathias Lund Ahrn, Fredrik Pedersen
 * @since 24-04-2019
 */

public abstract class Client implements Serializable {

    private String address;
    private int zipcode;
    private String city;
    private String industry;

    protected Client() {} //Default constructor used by the CSV Reader to create objects

    protected Client(Builder<?> builder) {
        this.address = builder.address;
        this.zipcode = builder.zipcode;
        this.city = builder.city;
        this.industry = builder.industry;
    }

    abstract static class Builder<T extends Builder<T>> {
        private String address;
        private int zipcode;
        private String city;
        private String industry;


        public Builder(String address, int zipcode, String city, String industry) {
            this.address = address;
            this.zipcode = zipcode;
            this.city = city;
            this.industry = industry;
        }

        abstract Client build();

        protected abstract T self();
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

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }


    @Override
    public String toString() {
        return "Client{" +
                "address='" + address + '\'' +
                ", zipcode=" + zipcode +
                ", city='" + city + '\'' +
                ", industry='" + industry + '\'' +
                '}';
    }
}
