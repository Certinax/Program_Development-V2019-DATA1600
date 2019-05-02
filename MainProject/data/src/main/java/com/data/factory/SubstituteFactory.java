package com.data.factory;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.data.clients.Substitute;
import com.logic.concurrency.WriterThreadStarter;
import javafx.collections.ObservableList;
import javafx.scene.Node;

//TODO JavaDocs
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
    private int phoneNumber;
    private String email;

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
         this.substitute = new Substitute.Builder(firstname, lastname, phoneNumber, email, address, age, zipcode, city, industry)
                 .salaryRequirement(salaryRequirement)
                 .education(education)
                 .workExperience(workExperience)
                 .workReference(workReference)
                 .build();
    }

    private void saveSubstitute(Substitute substitute) throws InterruptedException {
        // TODO FIKS FREDRIK PATHS HER FØR INNLEVERING
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
                this.industry = entry.getValue().toString();
            }
            if(entry.getKey().getId().equals("phoneField")) {
                if(!entry.getValue().toString().isEmpty()) {
                    this.phoneNumber = Integer.parseInt(entry.getValue().toString());
                }
            }
            if(entry.getKey().getId().equals("emailField")) {
                this.email = entry.getValue().toString();
            }
        }

    }

    @SuppressWarnings("unchecked")
    private void generateOptionalFields() {

        for (Map.Entry<Node, Object> entry : objectInfo.entrySet()) {
            if(entry.getKey().getId().equals("salaryRequirement")) {
                if(!entry.getValue().toString().isEmpty()) {
                    this.salaryRequirement = Integer.parseInt(entry.getValue().toString());
                }
            }
            if(entry.getKey().getId().equals("schoolList")) {
                if(entry.getValue() instanceof ObservableList) {
                    Collection<String> education = (ObservableList<String>)entry.getValue();
                    this.education = new ArrayList<>(education);
                }
            }
            if(entry.getKey().getId().equals("workList")) {
                if(entry.getValue() instanceof ObservableList) {
                    Collection<String> workExperience = (ObservableList<String>)entry.getValue();
                    this.workExperience = new ArrayList<>(workExperience);
                }
            }
            if(entry.getKey().getId().equals("referenceList")) {
                if(entry.getValue() instanceof ObservableList) {
                    Collection<String> workReference = (ObservableList<String>)entry.getValue();
                    this.workReference = new ArrayList<>(workReference);
                }
            }
        }
    }



}
