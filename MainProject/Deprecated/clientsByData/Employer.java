package com.data.clients;

import java.util.ArrayList;

public class Employer extends Client {

    private String sector;
    private String industry;
    private ArrayList<String> joblist;

    public Employer(String name, String address, int zipcode, String city, String sector, String industry, ArrayList<String> joblist) {
        super(name, address, zipcode, city);
        this.sector = sector;
        this.industry = industry;
        this.joblist = joblist;
    }

    public String[] template() {
        return new String[] {"getSector","getIndustry","getJoblist"};
    }

    @Override
    public String toString() {
        return super.toString() +
                "Employer{" +
                "sector='" + sector + '\'' +
                ", industry='" + industry + '\'' +
                ", joblist=" + joblist +
                '}';
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public ArrayList<String> getJoblist() {
        return joblist;
    }

    public void setJoblist(ArrayList<String> joblist) {
        this.joblist = joblist;
    }
}

