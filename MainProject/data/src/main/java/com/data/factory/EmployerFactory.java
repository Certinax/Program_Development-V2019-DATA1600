package com.data.factory;

import com.data.clients.Employer;
import com.logic.concurrency.WriterThreadStarter;
import com.logic.filePaths.ActivePaths;
import com.logic.utilities.exceptions.NumberGenerationException;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.Map;

/**
 * <h1>Employer Factory</h1>
 *
 * This is a factory-class that is used for creating objects from a bigger chunk of
 * dataset (typically a registration form) of type Employer and store it to file.
 *
 * @author Candidate 511
 * @since 23-04-2019
 */
public class EmployerFactory {

    private Map<Node, Object> objectInfo;
    private Employer employer;

    private String name;
    private String address;
    private int zipcode;
    private String city;
    private boolean privateSector;
    private String industry;
    private int phoneNumber;
    private String email;

    // Optional fields with default values to avoid null
    private ArrayList<String> joblist = new ArrayList<>();

    public EmployerFactory(Map<Node, Object> objectInfo) throws IllegalArgumentException, InterruptedException, NumberGenerationException {
        this.objectInfo = objectInfo;
        generateRequiredFields();
        generateOptionalFields();
        createEmployer();
        saveEmployer(employer);
    }

    private void createEmployer() throws NumberGenerationException {
        this.employer = new Employer
                .Builder(name, address, zipcode, city, phoneNumber, email, privateSector, industry)
                .joblist(joblist)
                .build();
    }

    private void saveEmployer(Employer employer) throws InterruptedException {
        WriterThreadStarter.startWriter(employer, ActivePaths.getEmployerCSVPath(), true);
        WriterThreadStarter.startWriter(employer, ActivePaths.getEmployerJOBJPath(), false);
    }

    private void generateRequiredFields() throws IllegalArgumentException {

        for (Map.Entry<Node, Object> entry : objectInfo.entrySet()) {
            if (entry.getKey().getId().equals("name")) {
                this.name = entry.getValue().toString();
            }
            if (entry.getKey().getId().equals("address")) {
                this.address = entry.getValue().toString();
            }
            if (entry.getKey().getId().equals("zipcode")) {
                this.zipcode = Integer.parseInt(entry.getValue().toString());
            }
            if (entry.getKey().getId().equals("city")) {
                this.city = entry.getValue().toString();
            }
            if (entry.getKey().getId().equals("privateSector")) {
                this.privateSector = Boolean.parseBoolean(entry.getValue().toString());
            }
            if (entry.getKey().getId().equals("industry")) {
                this.industry = entry.getValue().toString();
            }
            if (entry.getKey().getId().equals("phoneNumber")) {
                if (!entry.getValue().toString().isEmpty()) {
                    this.phoneNumber = Integer.parseInt(entry.getValue().toString());
                }
            }
            if (entry.getKey().getId().equals("email")) {
                this.email = entry.getValue().toString();
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void generateOptionalFields() {

        for (Map.Entry<Node, Object> entry : objectInfo.entrySet()) {
            if (entry.getKey().getId().equals("joblist")) {
                if(entry.getValue() instanceof ObservableList) {
                    this.joblist = (ArrayList<String>) entry.getValue();
                }
            }
        }
    }
}
