package com.logic.clients;

import com.logic.clients.infoclasses.Personalia;

import java.util.ArrayList;

/**
 * <h1>Employer</h1>
 *
 * Class for making Employer objects
 *
 *
 * @author Fredrik Pedersen
 * @since 04-04-2019
 */

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
