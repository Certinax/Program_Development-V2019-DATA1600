package com.data.objectCreation;


import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import com.data.clients.Substitute;
import com.logic.concurrency.WriterThreadStarter;
import javafx.scene.Node;

public class SubstituteFactory {

    Map<Node, Object> objectInfo;

    Substitute substitute;

    private String firstname;
    private String lastname;
    private int age;
    private String address;
    private int zipcode;
    private String city;
    private String industry;

    private int salaryRequirement;
    private ArrayList<String> education;
    private ArrayList<String> workExcperience;
    private ArrayList<String> workReference;


    public SubstituteFactory(Map<Node, Object> objectInfo) throws IllegalArgumentException{
        this.objectInfo = objectInfo;
        generateFields();
        createSubstitute();
        saveSubstitute(substitute);
    }

    private void createSubstitute() {
         this.substitute = new Substitute.Builder(firstname, lastname, address, age, zipcode, city, industry)
                 .salaryRequirement(salaryRequirement)
                 .education(education)
                 .workExperience(workExcperience)
                 .workReference(workReference)
                 .build();
    }

    private void saveSubstitute(Substitute substitute) {
        try {
            WriterThreadStarter.startWriter(substitute, "resources/subcreation.csv");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void generateFields() throws IllegalArgumentException{
        for (Map.Entry<Node, Object> entry : objectInfo.entrySet()) {
            if (entry.getKey().getId().equals("firstname")) {
                this.firstname = entry.getValue().toString();
            }
            if (entry.getKey().getId().equals("lastname")) {
                this.lastname = entry.getValue().toString();
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
                this.city = entry.getValue().toString();
            }
        }

    }



}
