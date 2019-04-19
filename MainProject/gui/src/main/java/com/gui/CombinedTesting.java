package com.gui;

import com.data.client.Employer;
import com.logic.io.reader.ReaderCSV;
import com.logic.io.writer.WriterCSV;

import java.io.IOException;
import java.util.ArrayList;

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
        Employer emp1 = new Employer("DNB", "Bjørvika 12", 0166, "Oslo", "Private", "Bank", jobs);
        //System.out.println(emp1);

        WriterCSV.writeClient(emp1, "clients.csv");
    }

    public static void employerReader() {
        try {
            System.out.println(ReaderCSV.readClient("clients.csv").toString());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
