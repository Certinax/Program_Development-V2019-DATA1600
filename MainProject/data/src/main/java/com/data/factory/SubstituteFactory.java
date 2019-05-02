package com.data.factory;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import com.data.clients.Substitute;
import com.logic.concurrency.WriterThreadStarter;
import javafx.collections.ObservableList;
import javafx.scene.Node;

public class SubstituteFactory {

    private Map<Node, Object> objectInfo;
    private Substitute substitute;

    private String firstname;
    private String lastname;
    private int age;
    private String address;
    private int zipcode;
    private String city;
    private String industry;

    // Optional fields with default values to avoid null
    private int salaryRequirement = 0;
    private ArrayList<String> education = new ArrayList<>();
    private ArrayList<String> workExperience = new ArrayList<>();
    private ArrayList<String> workReference = new ArrayList<>();


    public SubstituteFactory(Map<Node, Object> objectInfo) throws IllegalArgumentException, InterruptedException {
        this.objectInfo = objectInfo;
        generateRequiredFields();
        generateOptionalFields();
        createSubstitute();
        saveSubstitute(substitute);
    }

    private void createSubstitute() {
         this.substitute = new Substitute.Builder(firstname, lastname, address, age, zipcode, city, industry)
                 .salaryRequirement(salaryRequirement)
                 .education(education)
                 .workExperience(workExperience)
                 .workReference(workReference)
                 .build();
    }

    private void saveSubstitute(Substitute substitute) throws InterruptedException {
        WriterThreadStarter.startWriter(substitute, "resources/subcreation.csv");
    }

    private void generateRequiredFields() throws IllegalArgumentException{

        for (Map.Entry<Node, Object> entry : objectInfo.entrySet()) {
            if (entry.getKey().getId().equals("firstname")) {
                this.firstname = entry.getValue().toString();
            }
            if (entry.getKey().getId().equals("lastname")) {
                this.lastname = entry.getValue().toString();
            }
            if (entry.getKey().getId().equals("address")) {
                this.address = entry.getValue().toString();
            }
            if (entry.getKey().getId().equals("age")) {
                LocalDate now = LocalDate.now();
                LocalDate dob = (LocalDate)entry.getValue();
                if(now.getYear() <= dob.getYear()) {
                    throw new IllegalArgumentException("Date of birth can't be greater than current year");
                } else {
                    this.age = now.getYear() - dob.getYear();
                }
            }
            if (entry.getKey().getId().equals("zipcode")) {
                this.zipcode = Integer.parseInt(entry.getValue().toString());
            }
            if (entry.getKey().getId().equals("city")) {
                this.city = entry.getValue().toString();
            }
            if (entry.getKey().getId().equals("industry")) {
                System.out.println(entry.getValue().toString());
                this.industry = entry.getValue().toString();
            }
        }

    }

    @SuppressWarnings("unchecked")
    private void generateOptionalFields() {

        for (Map.Entry<Node, Object> entry : objectInfo.entrySet()) {
            if(entry.getKey().getId().equals("salaryRequirement")) {
                this.salaryRequirement = Integer.parseInt(entry.getValue().toString());
            }
            if(entry.getKey().getId().equals("education")) {
                if(entry.getValue() instanceof ObservableList) {
                    this.education = (ArrayList<String>) entry.getValue();
                }
            }
            if(entry.getKey().getId().equals("workExperience")) {
                if(entry.getValue() instanceof ObservableList) {
                    this.workExperience = (ArrayList<String>) entry.getValue();
                }
            }
            if(entry.getKey().getId().equals("workReference")) {
                if(entry.getValue() instanceof ObservableList) {
                    this.workReference = (ArrayList<String>) entry.getValue();
                }
            }
        }
    }



}