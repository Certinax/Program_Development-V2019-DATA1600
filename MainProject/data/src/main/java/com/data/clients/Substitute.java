package com.data.clients;

import com.data.CSVWriteable;
import com.data.handlers.NumberManager;

import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

/**
 * <h1>Substitute</h1>
 *
 * Class for representing substitutes Utilizes the Builder-pattern to create
 * objects
 *
 * @author Mathias Lund Ahrn, Fredrik Pedersen
 * @since 24-04-2019
 */

public class Substitute extends Client implements Serializable, CSVWriteable {

    private int substituteNumber;
    private String substituteId;
    private String firstname;
    private String lastname;
    private int age;
    private int salaryRequirement;
    private int phoneNumber;
    private String email;
    private ArrayList<String> education;
    private ArrayList<String> workExperience;
    private ArrayList<String> workReference;

    private Substitute(Builder builder) {
        super(builder);
        substituteNumber = builder.substituteNumber;
        substituteId = builder.substituteId;
        firstname = builder.firstname;
        lastname = builder.lastname;
        age = builder.age;
        salaryRequirement = builder.salaryRequirement;
        education = builder.education;
        workExperience = builder.workExperience;
        workReference = builder.workReference;
        phoneNumber = builder.phoneNumber;
        email = builder.email;
    }

    private Substitute() {
    } // Default constructor used by the CSV Reader to create objects

    public static class Builder extends Client.Builder<Builder> {
        // Required parameters
        private final int substituteNumber;
        private final String substituteId;
        private final String firstname;
        private final String lastname;
        private final int age;
        private final int phoneNumber;
        private final String email;

        // Optional parameters
        private int salaryRequirement = 0;
        private ArrayList<String> education = new ArrayList<>();
        private ArrayList<String> workExperience = new ArrayList<>();
        private ArrayList<String> workReference = new ArrayList<>();

        // Builder for required parameters
        public Builder(String firstname, String lastname, int phoneNumber, String email, String address, int age, int zipcode, String city,
                       String industry) {
            super(address, zipcode, city, industry);
            this.firstname = firstname;
            this.lastname = lastname;
            this.age = age;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.substituteNumber = NumberManager.INSTANCE.getSubstituteNumberAndIncrement();
            UUID uuid = UUID.randomUUID();
            this.substituteId = uuid.toString();
        }

        // Builders for optional parameters
        public Builder salaryRequirement(int salaryRequirement) {
            this.salaryRequirement = salaryRequirement;
            return self();
        }

        public Builder education(ArrayList<String> education) {
            this.education = education;
            return self();
        }

        public Builder workExperience(ArrayList<String> workExperience) {
            this.workExperience = workExperience;
            return self();
        }

        public Builder workReference(ArrayList<String> workReference) {
            this.workReference = workReference;
            return self();
        }

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public Substitute build() {
            return new Substitute(this);
        }
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSubstituteId() {
        return substituteId;
    }

    public int getSubstituteNumber() { return  substituteNumber; }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalaryRequirement() {
        return salaryRequirement;
    }

    public void setSalaryRequirement(int salaryRequirement) {
        this.salaryRequirement = salaryRequirement;
    }

    public ArrayList<String> getEducation() {
        return education;
    }

    public void setEducation(ArrayList<String> education) {
        this.education = education;
    }

    public ArrayList<String> getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(ArrayList<String> workExperience) {
        this.workExperience = workExperience;
    }

    public ArrayList<String> getWorkReference() {
        return workReference;
    }

    public void setWorkReference(ArrayList<String> workReference) {
        this.workReference = workReference;
    }

    public String[] template() {
        return new String[] {"getSubstituteNumber", "getFirstname", "getLastname", "getAddress", "getZipcode",
                "getPhoneNumber", "getEmail", "getCity", "getAge", "getSalaryRequirement", "getIndustry",
                "getEducation", "getWorkExperience",
                "getWorkReference", "getSubstituteId", this.getClass().getName() };
    }

    /* ------------------------ Tableview Specific Getters --------------------------------*/

    /*
    TableView uses reflection to read the getters in a class. These getters are specificly made to
    return a correctly formated value to the TableView
     */

    public String getOneEducation() {
        if (this.education.size() > 1) {
            Random rng = new Random();
            return education.get(rng.nextInt(education.size()));
        } else if (this.education.size() == 1) {
            return education.get(0);
        }
        return "";
    }

    @Override
    public String toString() {
        return "Substitute{" +
                "substituteNumber=" + substituteNumber +
                ", substituteId='" + substituteId + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                ", salaryRequirement=" + salaryRequirement +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", education=" + education +
                ", workExperience=" + workExperience +
                ", workReference=" + workReference +
                '}';
    }
}