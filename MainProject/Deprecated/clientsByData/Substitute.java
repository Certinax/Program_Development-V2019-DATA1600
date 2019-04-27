package com.data.clients;


import java.util.ArrayList;

public class Substitute extends Client {

    private int age;
    private int salaryRequirement;
    private String industry;
    private ArrayList<String> education;
    private ArrayList<String> jobExperience;
    private ArrayList<String> references;

    public Substitute(
            String firstname,
            String lastname,
            String address,
            int zipcode,
            String city,
            int age,
            int salaryRequirement,
            String industry,
            ArrayList<String> education,
            ArrayList<String> jobExperience,
            ArrayList<String> references)
    {
        super(
            firstname,
            lastname,
            address,
            zipcode,
            city
        );
        this.age = age;
        this.industry = industry;
        this.salaryRequirement = salaryRequirement;
        this.education = education;
        this.jobExperience = jobExperience;
        this.references = references;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Substitute{" +
                ", age=" + age +
                ", salaryRequirement=" + salaryRequirement +
                ", industry=" + industry +
                ", education=" + education +
                ", jobExperience=" + jobExperience +
                ", references=" + references +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalaryRequirement() {
        return salaryRequirement;
    }

    public void setSalaryRequirement(int salaryRequirement) {
        this.salaryRequirement = salaryRequirement;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public ArrayList<String> getEducation() {
        return education;
    }

    public void setEducation(ArrayList<String> education) {
        this.education = education;
    }

    public ArrayList<String> getJobExperience() {
        return jobExperience;
    }

    public void setJobExperience(ArrayList<String> jobExperience) {
        this.jobExperience = jobExperience;
    }

    public ArrayList<String> getReferences() {
        return references;
    }

    public void setReferences(ArrayList<String> references) {
        this.references = references;
    }
}