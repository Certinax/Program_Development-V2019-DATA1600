package com.logic.people.infoclasses;

import com.logic.exceptions.InvalidPhonenumberException;
import com.logic.validators.accountValidator;

public class Phonenumber {

    private int phonenumber;

    public Phonenumber(int phonenumber) throws InvalidPhonenumberException {

        if (!accountValidator.intLengthChecker(phonenumber, 6)) {
            throw new InvalidPhonenumberException("A phone number must be six digits");
        }

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
