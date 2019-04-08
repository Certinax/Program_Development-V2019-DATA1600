package com.logic.people.infoclasses;

public class Personalia {

    private String firstname;
    private String lastname;
    private int age;
    private Email email;
    private Address address;
    private Phonenumber phonenumber;

    public Personalia(String firstname, String lastname, int age, Email email, Address address, Phonenumber phonenumber){
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.address = address;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Phonenumber getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Phonenumber phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String toString() {
        return firstname + " " + lastname + ". Age: " + age + " " + "\n"+ address.toString() + "\n" + phonenumber + "\n" + email.toString();
    }


}
