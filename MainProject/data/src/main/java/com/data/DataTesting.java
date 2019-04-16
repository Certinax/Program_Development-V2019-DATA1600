package com.data;

import com.data.clients.Client;
import com.data.clients.infoclasses.*;
import com.logic.exceptions.InvalidAddressException;
import com.logic.exceptions.InvalidMailException;

import java.util.ArrayList;
import java.util.EnumSet;

public class DataTesting {

    public static void main(String[] args) {

        try {
            Address test = new Address("Haugveien 19", 1920);
        }catch (InvalidAddressException e) {
            e.printStackTrace();
        }

        /*ArrayList<Industry> industries = new ArrayList<>(EnumSet.allOf(Industry.class));

        System.out.println(industries.toString());

        for(Industry industry: Industry.values()) {
            //System.out.println(industry);
            System.out.println(industry.getIndustryName());
        }*/
        Email email = null;
        try {
            email = new Email("test@gmail.com");
        } catch (InvalidMailException ime) {
            ime.printStackTrace();
        }

        Phonenumber tlf = new Phonenumber(90899281);
        Clientinfo client1 = new Clientinfo(email, tlf);

        System.out.println(client1);


        Email email2 = null;
        try {
            email2 = new Email("admin@hotmail.com");
        } catch (InvalidMailException ime) {
            ime.printStackTrace();
        }

        Phonenumber tlf2 = new Phonenumber(99231543);
        Clientinfo client2 = new Clientinfo(email2, tlf2);

        System.out.println(client2);

    }
}
