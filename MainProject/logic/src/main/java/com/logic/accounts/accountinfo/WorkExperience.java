package com.logic.accounts.accountinfo;

public class WorkExperience {

    private String workplace;
    private String position;
    private Reference referenceContact;
    private int from;
    private int to;

    public WorkExperience(String workplace, String position, Reference referenceContact, int from, int to) {
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
        return "Work Experience: " + position + " at " + workplace + " From " + from + " To " + to + "." + " " + referenceContact.toString();
    }
}
