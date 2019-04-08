package com.logic.people;

import com.logic.people.infoclasses.Personalia;

import java.util.ArrayList;

public class Employer extends Person {

    private boolean publicSector;
    private String lineofIndustry;
    private Personalia personalia;
    private ArrayList<String> temporaryPositions;

    public Employer(Personalia personalia, boolean publicSector,
                    String lineofIndustry, Personalia personalia1, ArrayList<String> temporaryPositions) {

        super(personalia);
        this.publicSector = publicSector;
        this.lineofIndustry = lineofIndustry;
        this.personalia = personalia1;
        this.temporaryPositions = temporaryPositions;
    }
}
