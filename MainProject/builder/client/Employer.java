package client;

import java.util.ArrayList;

public class Employer extends Client {

    private String sector;
    private String industry;
    private ArrayList<String> joblist;

    /*public Employer(String name, String address, int zipcode, String city, String sector, String industry, ArrayList<String> joblist) {
        super(name, address, zipcode, city);
        this.sector = sector;
        this.industry = industry;
        this.joblist = joblist;
    }*/

        public static class Builder extends Client.Builder<Builder> {
            // Required parameters
            private final String sector;
            private final String industry;

            // Optional parameters
            private ArrayList<String> joblist = null;

            public Builder(String name, String address, int zipcode, String city, String sector, String industry) {
                super(name, address, zipcode, city);
                this.sector = sector;
                this.industry = industry;
            }

            public Builder joblist(ArrayList<String> jobs) {
                joblist = jobs;
                return self();
            }

            @Override
            public Builder self() {
                return this;
            }

            @Override
            public Employer build() {
                return new Employer(this);
            }
        }

    private Employer(Builder builder) {
        super(builder);
        sector = builder.sector;
        industry = builder.industry;
        joblist = builder.joblist;
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
