package com.data.clients.employers;

import com.data.clients.Client;
import com.data.clients.infoclasses.Address;
import com.data.clients.infoclasses.Clientinfo;
import com.data.clients.infoclasses.Personalia;

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

public class Employer extends Client {

    /*private boolean publicSector;
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
    }*/

    private boolean publicSector;
    private String industry;

    public Employer(Clientinfo clientinfo, boolean publicSector, String industry) {
        super(clientinfo);
        this.publicSector = publicSector;
        this.industry = industry;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "publicSector=" + publicSector +
                ", industry='" + industry + '\'' +
                ", clientinfo=" + super.toString() +
                '}';
    }

    public boolean isPublicSector() {
        return publicSector;
    }

    public void setPublicSector(boolean publicSector) {
        this.publicSector = publicSector;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @Override
    public Clientinfo getClientinfo() {
        return super.getClientinfo();
    }
}
