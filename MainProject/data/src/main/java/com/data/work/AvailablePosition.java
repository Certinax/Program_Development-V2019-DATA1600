package com.data.work;

import com.data.CSVWriteable;
import com.data.handlers.NumberManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

//TODO Write JavaDocs!
public class AvailablePosition implements Serializable, CSVWriteable {

    private int availablePositionNumber; //An internal ID for the position
    private String availablePositionId; // Unique UUID
    private Boolean publicSector; //Is it public or private sector?
    private String workplace; //Where is it?
    private int employer; //ID of the employer
    private String positionType; //What kind of position is it? Consulent? Manager? Crewmember etc...
    private String industry; //What industry is it? IT? Economics? etc
    private int duration; //For how long do they need a substitute
    private int startingTime; //When does the workday start
    private int endingTime; //When does the workday end
    private String requiredQualifications;
    private int salary; //Hourly salary
    private String contactInfo; //An email for contacting them
    private String description; //A description of the position
    private ArrayList<String> applicants; //A list of applicants for the position

    protected AvailablePosition() {} //Default constructor used by the CSV Reader to create objects

    protected AvailablePosition(Builder<?> builder) {
        this.availablePositionNumber = builder.availablePositionNumber;
        this.availablePositionId = builder.availablePositionId;
        this.publicSector = builder.publicSector;
        this.workplace = builder.workplace;
        this.employer = builder.employer;
        this.positionType = builder.positionType;
        this.industry = builder.industry;
        this.duration = builder.duration;
        this.salary = builder.salary;
        this.contactInfo = builder.contactInfo;
        this.applicants = builder.applicants;
    }

    public static class Builder<T extends Builder<T>> {
        // Required parameters
        private int availablePositionNumber; //An internal ID for the position
        private String availablePositionId;
        private Boolean publicSector; //Is it public or private sector?
        private String workplace; //Where is it?
        private int employer; //ID of the employer
        private String positionType; //What kind of position is it? Consulent? Manager? Crewmember etc...
        private String industry; //What industry is it? IT? Economics? etc
        private int duration; //For how long do they need a substitute
        private int salary; //Hourly salary
        private String contactInfo; //An email for contacting them

        // Optional parameters
        private int startingTime = 0; //When does the workday start
        private int endingTime = 0; //When does the workday end
        private String requiredQualifications = "";
        private String description = ""; //A description of the position
        private ArrayList<String> applicants = new ArrayList<>(); //A list of IDs of applicants for the position

        //Builder for required parameters
        public Builder(boolean publicSector, String workplace, int employer, String positionType, String industry, int duration,
                       int salary, String contactInfo) {
            this.publicSector = publicSector;
            this.workplace = workplace;
            this.employer = employer;
            this.positionType = positionType;
            this.industry = industry;
            this.duration = duration;
            this.salary = salary;
            this.contactInfo = contactInfo;
            this.availablePositionNumber = NumberManager.INSTANCE.getAvailablePositionNumberAndIncrement();
            UUID uuid = UUID.randomUUID();
            this.availablePositionId = uuid.toString();
        }

        //Builders for optional parameters
        public Builder startingTime(int startingTime) {
            this.startingTime = startingTime;
            return self();
        }

        public Builder applicants(ArrayList<String> applicants) {
            this.applicants = applicants;
            return self();
        }

        public Builder endingTime(int endingTime) {
            this.endingTime = endingTime;
            return self();
        }

        public Builder requiredQualifications(String requiredQualifications) {
            this.requiredQualifications = requiredQualifications;
            return self();
        }

        public Builder description(String description) {
            this.description = description;
            return self();
        }

        public Builder<T> self() {
            return this;
        }

        public AvailablePosition build() {
            return new AvailablePosition(this);
        }
    }

    @Override
    public String[] template() {
        return new String[] {"getPublicSector", "getWorkplace", "getEmployer", "getPositionType", "getIndustry", "getDuration",
                "getStartingTime", "getEndingTime", "getRequiredQualifications", "getSalary", "getContactInfo", "getDescription",
                "getApplicants", this.getClass().getName()};
    }

    public int getAvailablePositionNumber() {
        return availablePositionNumber;
    }

    public String getAvailablePositionId() {
        return availablePositionId;
    }

    public Boolean getPublicSector() {
        return publicSector;
    }

    public void setPublicSector(Boolean publicSector) {
        this.publicSector = publicSector;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public int getEmployer() {
        return employer;
    }

    public void setEmployer(int employer) {
        this.employer = employer;
    }

    public String getPositionType() { return positionType; }

    public void setPositionType(String positionType) {this.positionType = positionType;}

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(int startingTime) {
        this.startingTime = startingTime;
    }

    public int getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(int endingTime) {
        this.endingTime = endingTime;
    }

    public String getRequiredQualifications() {
        return requiredQualifications;
    }

    public void setRequiredQualifications(String requiredQualifications) {
        this.requiredQualifications = requiredQualifications;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getApplicants() {
        return applicants;
    }

    public void setApplicants(ArrayList<String> applicants) {
        this.applicants = applicants;
    }

    @Override
    public String toString() {
        return "AvailablePosition{" +
                "availablePositionNumber=" + availablePositionNumber +
                ", availablePositionId='" + availablePositionId + '\'' +
                ", publicSector=" + publicSector +
                ", workplace='" + workplace + '\'' +
                ", employer=" + employer +
                ", positionType='" + positionType + '\'' +
                ", industry='" + industry + '\'' +
                ", duration=" + duration +
                ", startingTime=" + startingTime +
                ", endingTime=" + endingTime +
                ", requiredQualifications='" + requiredQualifications + '\'' +
                ", salary=" + salary +
                ", contactInfo='" + contactInfo + '\'' +
                ", description='" + description + '\'' +
                ", applicants=" + applicants +
                '}';
    }
}
