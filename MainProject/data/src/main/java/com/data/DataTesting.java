package com.data;

import com.data.clients.infoclasses.Address;
import com.logic.exceptions.InvalidAddressException;

public class DataTesting {

    public static void main(String[] args) {

        try {
            Address test = new Address("Haugveien 19", 1920);
        }catch (InvalidAddressException e) {
            e.printStackTrace();
        }

    }
}
