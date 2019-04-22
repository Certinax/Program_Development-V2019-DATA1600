package com.gui;

import com.data.client.Employer;
import com.data.client.Template;
import com.logic.io.reader.ReaderCSV;
import com.logic.io.writer.WriterCSV;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombinedTesting {

    public static void main(String[] args) {

        String job1 = "Sommervikar 2019";
        String job2 = "Deltidsjobb";
        String job3 = "Trainee internship summer 2020";
        String job4 = "Consultant";

        ArrayList<String> jobs = new ArrayList<String>();
        jobs.add(job1);
        jobs.add(job2);
        jobs.add(job3);
        jobs.add(job4);
        Employer emp1 = new Employer("DNB", "Bjørvika 12", 1166, "Oslo", "Private", "Bank", jobs);
        //System.out.println(emp1);

        WriterCSV.writeObject(emp1, "clients.csv", false);
        List<List<String>> clientList = new ArrayList<>();
        try {
            clientList = ReaderCSV.readClient("clients.csv");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        //System.out.println(clientList.toString());

        Map<String,String> template = Template.EMPLOYER.template();


        List<String> header = clientList.get(0);
        for(String s : header) {
            System.out.println(s);
        }

        System.out.println(template.keySet());



    }

    public static void employerReader() {
        try {
            System.out.println(ReaderCSV.readClient("clients.csv").toString());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
