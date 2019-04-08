package com.logic.people.infoclasses;

import java.time.LocalDate;

/**
 * <h1>Ecucation</h1>
 *
 * Class for making education objects.
 * The boolean values highSchool, bachelors, masters and phd are meant to indicate the substitute's currently active
 * studies. Combined with the finnished-value, it means that is their highest completed level of education.
 *
 * Use a Date-picker to get to the from and to values
 *
 * @author Fredrik Pedersen
 * @since 04-04-2019
 */

public class Education {

    private String nameOfEducation;
    private String institution;
    private boolean highSchool;
    private boolean bachelors;
    private boolean masters;
    private boolean phd;
    private boolean finnished;
    private LocalDate from;
    private LocalDate to;

    public Education(String nameOfEducation, String institution, boolean highSchool, boolean bachelors, boolean masters,
                     boolean phd, boolean finnished, LocalDate from, LocalDate to) {
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

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
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
