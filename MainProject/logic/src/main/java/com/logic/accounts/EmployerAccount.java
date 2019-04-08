package com.logic.accounts;

import com.logic.accounts.accountinfo.LoginCredentials;
import com.logic.accounts.accountinfo.Personalia;

import java.util.ArrayList;

public class EmployerAccount extends UserAccount {

    private boolean publicSector;
    private String lineofIndustry;
    private Personalia personalia;
    private ArrayList<String> temporaryPositions;

    public EmployerAccount(Personalia personalia, LoginCredentials credentials, boolean publicSector,
                           String lineofIndustry, Personalia personalia1, ArrayList<String> temporaryPositions) {

        super(personalia, credentials);
        this.publicSector = publicSector;
        this.lineofIndustry = lineofIndustry;
        this.personalia = personalia1;
        this.temporaryPositions = temporaryPositions;
    }
}
