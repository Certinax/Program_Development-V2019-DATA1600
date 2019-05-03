package com.data.work;

import com.data.CSVWriteable;
import com.data.clients.Employer;
import com.data.clients.Substitute;
import com.data.handlers.NumberManager;
import com.logic.concurrency.ReaderThreadStarter;
import com.logic.filePaths.ActivePaths;
import com.logic.utilities.exceptions.AvailablePositionException;
import com.logic.utilities.exceptions.NumberGenerationException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * <h1>Positions</h1>
 *
 * Class for representing positions. Utilizes the Builder-pattern to create
 * objects
 *
 * @author Candidate 511
 * @since 26-04-2019
 */

public class AvailablePosition implements Serializable, CSVWriteable {

    // Required
    private int availablePositionNumber; //An internal ID for the position
    private String availablePositionId; // Unique UUID
    private String employerId; // This is foreign key for employerobject that owns this temporary position
    private int numberOfPositions; // This keeps track of how many subtitutes this position can employ
    private boolean publicSector; //Is it public or private sector?
    private String industry; //What industry is it? IT? Economics? etc
    private String positionType; //What kind of position is it? Consulent? Manager? Crewmember etc...
    // Dependable variable
    private boolean available; // This boolean should be changed upon applicants.size() == numberOfPositions

    // Optional
    private String workplace; //Where is it?
    private String duration; //For how long do they need a substitute
    private String startingTime; //When does the workday start
    private String endingTime; //When does the workday end
    private String requiredQualifications;
    private int salary; //Hourly salary
    private String contactInfo; //An email for contacting them
    private String description; //A description of the position
    private ArrayList<String> applicants; //A list of applicants for the position (use substituteIDs)

    protected AvailablePosition() {} //Default constructor used by the CSV Reader to create objects

    protected AvailablePosition(Builder<?> builder) {
        // Required
        this.availablePositionNumber = builder.availablePositionNumber;
        this.availablePositionId = builder.availablePositionId;
        this.employerId = builder.employerId;
        this.numberOfPositions = builder.numberOfPositions;
        this.publicSector = builder.publicSector;

        this.available = builder.available;
        // Optional
        this.workplace = builder.workplace;
        this.positionType = builder.positionType;
        this.industry = builder.industry;
        this.duration = builder.duration;
        this.startingTime = builder.startingTime;
        this.endingTime = builder.endingTime;
        this.requiredQualifications = builder.requiredQualifications;
        this.salary = builder.salary;
        this.contactInfo = builder.contactInfo;
        this.description = builder.description;
        this.applicants = builder.applicants;
    }

    public static class Builder<T extends Builder<T>> {
        // Required parameters
        private int availablePositionNumber; //An internal ID for the position
        private String availablePositionId;
        private String employerId;
        private int numberOfPositions;
        private boolean publicSector;
        private String industry;
        private String positionType;

        // Dependable variable
        private boolean available = true;

        // Optional parameters
        private String duration = "";
        private int salary = 0;
        private String contactInfo = "";
        private String workplace = "";
        private String startingTime = "";
        private String endingTime = "";
        private String requiredQualifications = "";
        private String description = "";
        private ArrayList<String> applicants = new ArrayList<>();

        //Builder for required parameters
        public Builder(String employerId, boolean publicSector, int numberOfPositions, String industry,
                       String positionType) throws NumberGenerationException {

            this.availablePositionNumber = NumberManager.INSTANCE.getAvailablePositionNumberAndIncrement();
            UUID uuid = UUID.randomUUID();
            this.availablePositionId = uuid.toString();
            this.employerId = employerId; // This is reference to employer-object
            this.publicSector = publicSector;
            this.numberOfPositions = numberOfPositions;
            this.industry = industry;
            this.positionType = positionType;
        }

        //Builders for optional parameters
        public Builder duration(String duration) {
            this.duration = duration;
            return self();
        }

        public Builder salary(int salary) {
            this.salary = salary;
            return self();
        }

        public Builder contactInfo(String contactInfo) {
            this.contactInfo = contactInfo;
            return self();
        }

        public Builder workplace(String workplace) {
            this.workplace = workplace;
            return self();
        }

        public Builder startingTime(String startingTime) {
            this.startingTime = startingTime;
            return self();
        }

        public Builder endingTime(String endingTime) {
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

        public Builder applicants(ArrayList<String> applicants) throws AvailablePositionException {
            if(applicants.size() < numberOfPositions) {
                this.applicants = applicants;
                return self();
            } else if (applicants.size() == numberOfPositions) {
                this.applicants = applicants;
                available = false;
                return self();
            } else {
                throw new AvailablePositionException("Number of applicants cannot be higher than number of positions");
            }
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
        return new String[] {"getAvailablePositionNumber", "getEmployerId", "getNumberOfPositions",
                "isPublicSector", "isAvailable", "getWorkplace", "getPositionType", "getIndustry", "getDuration",
                "getStartingTime","getEndingTime", "getRequiredQualifications", "getSalary", "getContactInfo",
                "getDescription", "getApplicants", "getAvailablePositionId", this.getClass().getName()};
    }

    public void setNumberOfPositions(int numberOfPositions) {
        this.numberOfPositions = numberOfPositions;
    }

    public void setPublicSector(boolean publicSector) {
        this.publicSector = publicSector;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public void setEndingTime(String endingTime) {
        this.endingTime = endingTime;
    }

    public void setRequiredQualifications(String requiredQualifications) {
        this.requiredQualifications = requiredQualifications;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setApplicants(ArrayList<String> applicants) throws AvailablePositionException{
        if(applicants.size() < numberOfPositions) {
            this.applicants = applicants;
        } else if (applicants.size() == numberOfPositions) {
            this.applicants = applicants;
            this.available = false;
        } else {
            throw new AvailablePositionException("Number of applicants cannot be higher than number of positions");
        }
    }

    public int getAvailablePositionNumber() {
        return availablePositionNumber;
    }

    public String getAvailablePositionId() {
        return availablePositionId;
    }

    public String getEmployerId() {
        return employerId;
    }

    public int getNumberOfPositions() {
        return numberOfPositions;
    }

    public boolean isPublicSector() {
        return publicSector;
    }

    public boolean isAvailable() {
        return available;
    }

    public String getWorkplace() {
        return workplace;
    }

    public String getPositionType() {
        return positionType;
    }

    public String getIndustry() {
        return industry;
    }

    public String getDuration() {
        return duration;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public String getRequiredQualifications() {
        return requiredQualifications;
    }

    public int getSalary() {
        return salary;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getApplicants() {
        return applicants;
    }


    /* ------------------------ Tableview Specific Getters --------------------------------*/

    /*
    TableView uses reflection to read the getters in a class. These getters are specificly made to
    return a correctly formated value to the TableView
     */


    public String getEmployerName() {
        ArrayList<Employer> employers;
        try {
           employers = ReaderThreadStarter.startReader(ActivePaths.getEmployerJOBJPath());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace(); //TODO Figure out what to properly do with this error message
            return "";
        }

        for (Employer employer : employers) {
            if (this.employerId.equals(employer.getEmployerId())) {
                return employer.getName();
            }
        }
        return "";
    }

    public String getApplicantNames(){
        ArrayList<Substitute> substitutes;
        StringBuilder sb = new StringBuilder();

        try {
            substitutes = ReaderThreadStarter.startReader(ActivePaths.getSubstituteJOBJPath());
        } catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
            return "";
        }

        for (Substitute substitute : substitutes) {
            for (String applicant : this.getApplicants()) {
                if (substitute.getSubstituteId().equals(applicant)) {
                    sb.append(substitute.getFirstname()).append(" ").append(substitute.getLastname()).append("\n");
                }
            }
        }

        return sb.toString();
    }

    public String getSectorAsString() {
        if (this.publicSector) {
            return "Public";
        } else {
            return "Private";
        }
    }

    @Override
    public String toString() {
        return "AvailablePosition{" +
                "availablePositionNumber=" + availablePositionNumber +
                ", availablePositionId='" + availablePositionId + '\'' +
                ", employerId='" + employerId + '\'' +
                ", numberOfPositions=" + numberOfPositions +
                ", publicSector=" + publicSector +
                ", available=" + available +
                ", workplace='" + workplace + '\'' +
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
