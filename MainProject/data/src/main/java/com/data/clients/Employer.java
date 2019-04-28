package com.data.clients;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <h1>Employer</h1>
 *
 * Class for representing employers
 * Utilizes the Builder-pattern to create objects
 *
 * @author Mathias Lund Ahrn, Fredrik Pedersen
 * @since 24-04-2019
 */

public class Employer extends Client implements Serializable {

    private String name;
    private boolean privateSector;
    private ArrayList<String> joblist;

    public Employer() {} //Default constructor used by the CSV Reader to create objects

    private Employer(Builder builder) {
        super(builder);
        name = builder.name;
        privateSector = builder.privateSector;
        joblist = builder.joblist;
    }

    public static class Builder extends Client.Builder<Builder> {
        // Required parameters
        private final String name;
        private final boolean privateSector;

        // Optional parameters
        private ArrayList<String> joblist = null;

        // Builder for required parameters
        public Builder(String name, String address, int zipcode, String city, Boolean privateSector, String industry) {
            super(address, zipcode, city, industry);
            this.name = name;
            this.privateSector = privateSector;
        }

        // Builders for optional parameters
        public Builder joblist(ArrayList<String> joblist) {
            this.joblist = joblist;
            return self();
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public Employer build() {
            return new Employer(this);
        }
    }

    public Boolean getPrivateSector() {
        return privateSector;
    }

    public void setPrivateSector(Boolean privateSector) {
        this.privateSector = privateSector;
    }

    public ArrayList<String> getJoblist() {
        return joblist;
    }

    public void setJoblist(ArrayList<String> joblist) {
        this.joblist = joblist;
    }


    @Override
    public String toString() {
        return super.toString() +
                "Employer{" +
                "name='" + name + '\'' +
                "sector='" + privateSector + '\'' +
                ", joblist=" + joblist +
                '}';
    }
}
