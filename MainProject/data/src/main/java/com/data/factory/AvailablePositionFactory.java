package com.data.factory;

import com.data.clients.Employer;
import com.data.work.AvailablePosition;
import com.logic.concurrency.WriterThreadStarter;
import com.logic.utilities.exceptions.AvailablePositionException;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

//TODO JavaDocs
public class AvailablePositionFactory {

    Map<Node, Object> objectInfo;
    private AvailablePosition availablePosition;

    private String employerId;
    private int numberOfPositions;
    private boolean publicSector;
    private String industry;

    // Optional fields with default values to avoid null
    private String workplace = "";
    private String positionType = "";
    private String duration = "";
    private int startingTime = 0;
    private int endingTime = 0;
    private String requiredQualifications = "";
    private int salary = 0;
    private String contactInfo = "";
    private String description = "";
    private ArrayList<String> applicants = new ArrayList<>();

    public AvailablePositionFactory(Map<Node, Object> objectInfo) throws IllegalArgumentException, AvailablePositionException, InterruptedException {
        this.objectInfo = objectInfo;
        generateRequiredFields();
        generateOptionalFields();
        createAvailablePosition();
        saveAvailablePosition(availablePosition);
    }

    private void createAvailablePosition() throws AvailablePositionException {
        this.availablePosition = new AvailablePosition.Builder(employerId, publicSector, numberOfPositions, industry)
                .workplace(workplace)
                .positionType(positionType)
                .duration(duration)
                .startingTime(startingTime)
                .endingTime(endingTime)
                .requiredQualifications(requiredQualifications)
                .salary(salary)
                .contactInfo(contactInfo)
                .description(description)
                .applicants(applicants)
                .build();
    }

    private void saveAvailablePosition(AvailablePosition availablePosition) throws InterruptedException {
        // TODO FIKS FREDRIK PATHS HER FØR INNLEVERING
        WriterThreadStarter.startWriter(availablePosition, "resources/tempPoscreation.csv");
    }

    private void generateRequiredFields() throws IllegalArgumentException {

        for (Map.Entry<Node, Object> entry : objectInfo.entrySet()) {
            if(entry.getKey().getId().equals("employer")) {
                Employer emp = (Employer)entry.getValue();
                this.employerId = emp.getEmployerId();
            }
            if(entry.getKey().getId().equals("publicSector")) {
                if (!entry.getValue().toString().isEmpty()) {
                    this.publicSector = Boolean.parseBoolean(entry.getValue().toString());
                }
            }
            if(entry.getKey().getId().equals("numberOfPositions")) {
                if (!entry.getValue().toString().isEmpty()) {
                    this.numberOfPositions = Integer.parseInt(entry.getValue().toString());
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void generateOptionalFields() throws IllegalArgumentException {

        for (Map.Entry<Node, Object> entry : objectInfo.entrySet()) {
            if(entry.getKey().getId().equals("workplace")) {
                this.workplace = entry.getValue().toString();
            }
            if(entry.getKey().getId().equals("positionType")) {
                this.positionType = entry.getValue().toString();
            }
            if(entry.getKey().getId().equals("industry")) {
                if(!entry.getValue().toString().isEmpty()) {
                    this.industry = entry.getValue().toString();
                }
            }
            if(entry.getKey().getId().equals("duration")) {
                this.duration = entry.getValue().toString();
            }
            if(entry.getKey().getId().equals("startingTime")) {
                int startingTime = Integer.parseInt(entry.getValue().toString());
                if(startingTime >= this.endingTime && this.endingTime != 0) {
                    throw new IllegalArgumentException("Starting time cannot be greater than ending time");
                } else {
                    this.startingTime = startingTime;
                }
            }
            if(entry.getKey().getId().equals("endingTime")) {
                int endingTime = Integer.parseInt(entry.getValue().toString());
                if(endingTime <= this.startingTime && this.startingTime != 0) {
                    throw new IllegalArgumentException("Ending time cannot be less than starting time");
                } else {
                    this.endingTime = endingTime;
                }
            }
            if(entry.getKey().getId().equals("requiredQualifications")) {
                this.requiredQualifications = entry.getValue().toString();
            }
            if(entry.getKey().getId().equals("salary")) {
                if(!entry.getValue().toString().isEmpty()) {
                    this.salary = Integer.parseInt(entry.getValue().toString());
                }
            }
            if(entry.getKey().getId().equals("contactInfo")) {
                this.contactInfo = entry.getValue().toString();
            }
            if(entry.getKey().getId().equals("description")) {
                this.description = entry.getValue().toString();
            }
            if(entry.getKey().getId().equals("applicants")) {
                if(entry.getValue() instanceof ObservableList) {
                    Collection<String> education = (ObservableList<String>)entry.getValue();
                    this.applicants = new ArrayList<>(education);
                }
            }
        }
    }
}
