package com.data.clients.infoclasses;

import com.logic.exceptions.InvalidAddressException;
import com.logic.validators.DataValidator;

/**
 * <h1>Address</h1>
 *
 * Class for making address objects
 * Use PostNumberFields for getting the postnumber from the GUI.
 *
 * @author Fredrik Pedersen
 * @since 04-04-2019
 */
public class Address {

    private String address;
    private int postnumber;

    public Address(String address, int postnumber) throws InvalidAddressException {

        if (!DataValidator.addressChecker(address)) {
            throw new InvalidAddressException("An address needs to be on the format \"Streetname 12\"");
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
