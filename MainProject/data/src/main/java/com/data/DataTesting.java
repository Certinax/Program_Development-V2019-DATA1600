package com.data;

import com.data.clients.infoclasses.Address;
import com.data.clients.infoclasses.Industry;
import com.logic.exceptions.InvalidAddressException;

import java.util.ArrayList;
import java.util.EnumSet;

public class DataTesting {

    public static void main(String[] args) {

        try {
            Address test = new Address("Haugveien 19", 1920);
        }catch (InvalidAddressException e) {
            e.printStackTrace();
        }

        ArrayList<Industry> industries = new ArrayList<>(EnumSet.allOf(Industry.class));

        System.out.println(industries.toString());

        for(Industry industry: Industry.values()) {
            //System.out.println(industry);
            System.out.println(industry.getIndustryName());
        }

    }
}
