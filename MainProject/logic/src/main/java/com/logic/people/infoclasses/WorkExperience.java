package com.logic.people.infoclasses;

import java.time.LocalDate;

/**
 * <h1>Email</h1>
 *
 * Class for making WorkExperience objects
 *
 * Use a DatePicker to get the from and to-values
 *
 * @author Fredrik Pedersen
 * @since 04-04-2019
 */

public class WorkExperience {

    private String workplace;
    private String position;
    private Reference referenceContact;
    private LocalDate from;
    private LocalDate to;

    public WorkExperience(String workplace, String position, Reference referenceContact, LocalDate from, LocalDate to) {
        this.workplace = workplace;
        this.position = position;
        this.referenceContact = referenceContact;
        this.from = from;
        this.to = to;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getReferenceContact() {
        return referenceContact.toString();
    }

    public void setReferenceContact(Reference referenceContact) {
        this.referenceContact = referenceContact;
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
        return "Work Experience: " + position + " at " + workplace + " From " + from + " To " + to + "." + " " + referenceContact.toString();
    }
}
