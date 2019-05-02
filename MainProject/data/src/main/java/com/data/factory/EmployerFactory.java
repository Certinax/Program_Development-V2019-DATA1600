package com.data.factory;

import com.data.clients.Employer;
import com.logic.concurrency.WriterThreadStarter;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.Map;

public class EmployerFactory {

    private Map<Node, Object> objectInfo;
    private Employer employer;

    private String name;
    private String address;
    private int zipcode;
    private String city;
    private boolean privateSector;
    private String industry;

    // Optional fields with default values to avoid null
    private ArrayList<String> joblist = new ArrayList<>();

    public EmployerFactory(Map<Node, Object> objectInfo) throws IllegalArgumentException {
        this.objectInfo = objectInfo;
        generateRequiredFields();
        generateOptionalFields();
        createEmployer();
        saveEmployer(employer);
    }

    private void createEmployer() {
        this.employer = new Employer
                .Builder(name, address, zipcode, city, privateSector, industry)
                .joblist(joblist)
                .build();
    }

    private void saveEmployer(Employer employer) {
        try {
            WriterThreadStarter.startWriter(employer, "resources/empcreation.csv");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
