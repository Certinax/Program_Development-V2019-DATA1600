package com.logic.accounts.accountinfo;

public class Education {

    private String nameOfEducation;
    private String institution;
    private boolean highSchool;
    private boolean bachelors;
    private boolean masters;
    private boolean phd;
    private boolean finnished;
    private int from;
    private int to;

    public Education(String nameOfEducation, String institution, boolean highSchool, boolean bachelors, boolean masters,
                     boolean phd, boolean finnished, int from, int to) {
        this.nameOfEducation = nameOfEducation;
        this.institution = institution;
        this.highSchool = highSchool;
        this.bachelors = bachelors;
        this.masters = masters;
        this.phd = phd;
        this.finnished = finnished;
        this.from = from;
        this.to = to;
    }

    public String getNameOfEducation() {
        return nameOfEducation;
    }

    public void setNameOfEducation(String nameOfEducation) {
        this.nameOfEducation = nameOfEducation;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public boolean isHighSchool() {
        return highSchool;
    }

    public void setHighSchool(boolean highSchool) {
        this.highSchool = highSchool;
    }

    public boolean isBachelors() {
        return bachelors;
    }

    public void setBachelors(boolean bachelors) {
        this.bachelors = bachelors;
    }

    public boolean isMasters() {
        return masters;
    }

    public void setMasters(boolean masters) {
        this.masters = masters;
    }

    public boolean isPhd() {
        return phd;
    }

    public void setPhd(boolean phd) {
        this.phd = phd;
    }

    public boolean isFinnished() {
        return finnished;
    }

    public void setFinnished(boolean finnished) {
        this.finnished = finnished;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String toString() {
        if (highSchool && !finnished)
            return "No finnished education above middle school";

        if (highSchool)
            return "Education: " + nameOfEducation + " at " + institution + ". From " + from + " to " + to;

        if (bachelors)
            return "Education: Bachelor's Degree in " + nameOfEducation + " at " + institution + ". From " + from + " to " + to;

        if (masters)
            return "Education: Masters's Degree in " + nameOfEducation + " at " + institution + ". From " + from + " to " + to;

        return "Education: PhD in " + nameOfEducation + " at " + institution + ". From " + from + " to " + to;
    }
}
