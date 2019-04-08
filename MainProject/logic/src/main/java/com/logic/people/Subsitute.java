package com.logic.people;

import com.logic.people.infoclasses.Education;
import com.logic.people.infoclasses.Personalia;
import com.logic.people.infoclasses.WorkExperience;

public class Subsitute extends Person {

    private String category;
    private Education education;
    private WorkExperience workExperience;
    private WorkExperience workExperience2;
    private int salaryRequirement;

    public Subsitute(Personalia personalia, String category,
                     Education education, WorkExperience workExperience, WorkExperience workExperience2,
                     int salaryRequirement) {

        super(personalia);
        this.category = category;
        this.education = education;
        this.workExperience = workExperience;
        this.workExperience2 = workExperience2;
        this.salaryRequirement = salaryRequirement;

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Education getEducation() {
        return education;
    }

    public void setEducation(Education education) {
        this.education = education;
    }

    public WorkExperience getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(WorkExperience workExperience) {
        this.workExperience = workExperience;
    }

    public WorkExperience getWorkExperience2() {
        return workExperience2;
    }

    public void setWorkExperience2(WorkExperience workExperience2) {
        this.workExperience2 = workExperience2;
    }

    public int getSalaryRequirement() {
        return salaryRequirement;
    }

    public void setSalaryRequirement(int salaryRequirement) {
        this.salaryRequirement = salaryRequirement;
    }

    @Override
    public String toString() {
        String out = super.toString() + "\nCategory: " + category + "\n" + education.toString() + "\n" + workExperience.toString();

        if (workExperience2 != null) {
            out += "\n" + workExperience2.toString();
        }

        out += "\nSalary Requirement: " + salaryRequirement;

        return out;
    }
}
