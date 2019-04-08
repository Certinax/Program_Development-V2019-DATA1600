package com.logic.accounts.accountinfo;

import com.logic.exceptions.InvalidAddressException;
import com.logic.exceptions.InvalidPostnumberException;
import com.logic.validators.accountValidator;

public class Address {

    private String address;
    private int postnumber;

    public Address(String address, int postnumber) throws InvalidAddressException, InvalidPostnumberException {

        if (!accountValidator.addressChecker(address)) {
            throw new InvalidAddressException("An address needs to be on the format \"Streetname 12\"");
        }

        if (!accountValidator.intLengthChecker(postnumber, 4)) {
            throw new InvalidPostnumberException("A postnumber needs to be four integers e.g \"1930\"");
        }

        this.address = address;
        this.postnumber = postnumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostnumber() {
        return postnumber;
    }

    public void setPostnumber(int postnumber) {
        this.postnumber = postnumber;
    }

    @Override
    public String toString() {
        return "Address: " + address + " " + postnumber;
    }
}