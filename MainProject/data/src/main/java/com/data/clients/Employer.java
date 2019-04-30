package com.data.clients;

import com.data.CSVWriteable;
import com.data.handlers.IdManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * <h1>Employer</h1>
 *
 * Class for representing employers Utilizes the Builder-pattern to create
 * objects
 *
 * @author Mathias Lund Ahrn, Fredrik Pedersen
 * @since 24-04-2019
 */

public class Employer extends Client implements Serializable, CSVWriteable {

    private int employerNumber;
    private String employerId;
    private String name;
    private boolean privateSector;
    private ArrayList<String> joblist;

    private Employer() {
    } // Default constructor used by the CSV Reader to create objects

    private Employer(Builder builder) {
        super(builder);
        employerId = builder.employerId;
        employerNumber = builder.employerNumber;
        name = builder.name;
        privateSector = builder.privateSector;
        joblist = builder.joblist;
    }

    public static class Builder extends Client.Builder<Builder> {
        // Required parameters
        private final String employerId;
        private final int employerNumber;
        private final String name;
        private final boolean privateSector;

        // Optional parameters
        private ArrayList<String> joblist = null;

        // Builder for required parameters
        public Builder(String name, String address, int zipcode, String city, boolean privateSector, String industry) {
            super(address, zipcode, city, industry);
            this.name = name;
            this.privateSector = privateSector;
            this.employerNumber = IdManager.INSTANCE.getEmployerIdAndIncrement();
            UUID uuid = UUID.randomUUID();
            this.employerId = uuid.toString();
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

    public String getName() {
        return name;
    }

    public boolean isPrivateSector() {
        return privateSector;
    }

    public String getEmployerId() {
        return employerId;
    }

    public int getEmployerNumber() { return  employerNumber; }

    public void setPrivateSector(Boolean privateSector) {
        this.privateSector = privateSector;
    }

    public ArrayList<String> getJoblist() {
        return joblist;
    }

    public void setJoblist(ArrayList<String> joblist) {
        this.joblist = joblist;
    }

    public String[] template() {
        return new String[] { "getEmployerNumber", "getName", "getAddress", "getZipcode", "getCity", "isPrivateSector",
                "getIndustry", "getJoblist", "getEmployerId", this.getClass().getName() };
    }

    @Override
    public String toString() {
        return super.toString() + "Employer{" + "employerNumber='" + employerNumber
                + '\'' + ", employerId='" + employerId
                + '\'' + ", name='" + name + '\''
                + ", sector='" + privateSector + '\'' + ", joblist=" + joblist + '}';
    }
}
