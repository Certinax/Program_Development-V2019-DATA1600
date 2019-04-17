package com.data.clients.infoclasses;

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
    private LocalDate from;
    private LocalDate to;

    public Education(String nameOfEducation, String institution, boolean highSchool, boolean bachelors, boolean masters,
                     boolean phd, boolean finnished, LocalDate from, LocalDate to) {
        this.nameOfEducation = nameOfEducation;
        this.institution = institution;
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

    @Override
    public String toString() {
        return "Education: " + nameOfEducation + " at " + institution + ". From " + from + " to " + to;
    }
}
