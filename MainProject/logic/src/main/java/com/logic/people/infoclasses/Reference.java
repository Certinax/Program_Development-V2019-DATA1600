package com.logic.people.infoclasses;

public class Reference {

    private String firstname;
    private String lastname;
    private Email email;
    private Phonenumber phonenumber;

    public Reference(String firstname, String lastname, Email email, Phonenumber phonenumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Phonenumber getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Phonenumber phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public String toString() {
        return "Reference: " + firstname + " " + lastname + " " + phonenumber + " " +  email.toString();
    }
}
