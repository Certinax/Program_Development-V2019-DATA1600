package com.logic.accounts;

import com.logic.accounts.accountinfo.Education;
import com.logic.accounts.accountinfo.LoginCredentials;
import com.logic.accounts.accountinfo.Personalia;
import com.logic.accounts.accountinfo.WorkExperience;

public class SubstituteAccount extends UserAccount {

    private String category;
    private Education education;
    private WorkExperience workExperience;
    private WorkExperience workExperience2;
    private int salaryRequirement;

    public SubstituteAccount(Personalia personalia, LoginCredentials credentials, String category,
                             Education education, WorkExperience workExperience, WorkExperience workExperience2,
                             int salaryRequirement) {

        super(personalia, credentials);
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
