package com.data.factory;

import com.data.clients.Employer;
import com.data.work.AvailablePosition;
import com.logic.concurrency.ReaderThreadStarter;
import com.logic.concurrency.WriterThreadStarter;
import com.logic.filePaths.ActivePaths;
import com.logic.utilities.exceptions.AvailablePositionException;
import com.logic.utilities.exceptions.NumberGenerationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * <h1>AvailablePosition Factory</h1>
 *
 * This is a factory-class that is used for creating objects from a bigger chunk of
 * dataset (typically a registration form) of type AvailablePosition and store it to file.
 *
 * @author Mathias Lund Ahrn
 * @since 23-04-2019
 */
public class AvailablePositionFactory {

    private Map<Node, Object> objectInfo;
    private AvailablePosition availablePosition;

    private String employerId;
    private int numberOfPositions;
    private boolean publicSector;
    private String industry;
    private String positionType;

    // Optional fields with default values to avoid null
    private String workplace = "";
    private String duration = "";
    private String startingTime = "";
    private String endingTime = "";
    private String requiredQualifications = "";
    private int salary = 0;
    private String contactInfo = "";
    private String description = "";
    private ArrayList<String> applicants = new ArrayList<>();

    public AvailablePositionFactory(Map<Node, Object> objectInfo) throws IllegalArgumentException,
            AvailablePositionException, InterruptedException, NumberGenerationException {
        this.objectInfo = objectInfo;
        generateRequiredFields();
        generateOptionalFields();
        createAvailablePosition();
        saveAvailablePosition(availablePosition);
    }

    private void createAvailablePosition() throws AvailablePositionException, NumberGenerationException {
        this.availablePosition = new AvailablePosition.Builder(employerId, publicSector, numberOfPositions, industry, positionType)
                .workplace(workplace)
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
        ObservableList<AvailablePosition> templist = FXCollections.observableArrayList();
        try {
            templist.addAll(ReaderThreadStarter.startReader(ActivePaths.getAvailablePositionJOBJPath()));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        templist.add(availablePosition);

        if (!templist.isEmpty()) {
            WriterThreadStarter.startWriter(templist, ActivePaths.getAvailablePositionJOBJPath(), false);
        } else {
            WriterThreadStarter.startWriter(availablePosition, ActivePaths.getAvailablePositionJOBJPath(), false);
        }

        WriterThreadStarter.startWriter(availablePosition, ActivePaths.getAvailablePositionCSVPath(), true);
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
            if(entry.getKey().getId().equals("position")) {
                this.positionType = entry.getValue().toString();
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void generateOptionalFields() throws IllegalArgumentException {

        for (Map.Entry<Node, Object> entry : objectInfo.entrySet()) {
            if(entry.getKey().getId().equals("workplace")) {
                this.workplace = entry.getValue().toString();
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
                this.startingTime = entry.getValue().toString();
            }
            if(entry.getKey().getId().equals("endingTime")) {
                this.endingTime = entry.getValue().toString();
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
            if(entry.getKey().getId().equals("positionDescription")) {
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
