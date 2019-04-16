package com.data;

import com.data.clients.Client;
import com.data.clients.employers.Employer;
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
            email = new Email("test@mordi.com");
        } catch (InvalidMailException ime) {
            ime.printStackTrace();
        }

        Phonenumber tlf = new Phonenumber(90899281);
        Clientinfo client1 = new Clientinfo(email, tlf);

        //System.out.println(client1);


        Email email2 = null;
        try {
            email2 = new Email("admin@hotmail.com");
        } catch (InvalidMailException ime) {
            ime.printStackTrace();
        }

        Phonenumber tlf2 = new Phonenumber(99231543);
        Clientinfo client2 = new Clientinfo(email2, tlf2);

        //System.out.println(client2);


        Employer emp1 = new Employer(client1,true, Industry.FINANCE_AND_ACCOUNTING.getIndustryName());
        Employer emp2 = new Employer(client2, false, Industry.BIOLOGY.getIndustryName());
        System.out.println(emp1);
        System.out.println(emp2);

        /*System.out.println(emp1.getClientinfo().getPhonenumber());
        System.out.println(emp1.getClientinfo().getClient_ID());*/

        if(emp1.getClientinfo().getClient_ID().equals(emp2.getClientinfo().getClient_ID())) {
            System.out.println("ID'ene er like");
        } else {
            System.out.println("Unike ID'er");
        }

        System.out.println(emp1.getClientinfo().getClient_ID());

    }
}
