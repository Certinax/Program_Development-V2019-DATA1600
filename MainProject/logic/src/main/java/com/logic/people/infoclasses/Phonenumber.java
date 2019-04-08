package com.logic.people.infoclasses;

/**
 * <h1>Email</h1>
 *
 * Class for making phonenumber objects
 *
 * @author Fredrik Pedersen
 * @since 04-04-2019
 */
public class Phonenumber {

    private int phonenumber;

    public Phonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String toString() {
        return "Phonenumber: " + phonenumber;
    }
}
