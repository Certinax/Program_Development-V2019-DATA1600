package com.data;

import com.data.client.Employer;
import com.data.io.reader.ReaderCSV;
import com.data.io.writer.WriterCSV;

import java.io.IOException;
import java.util.ArrayList;

public class EmployeeTester {


    public static void main(String[] args) {


        ArrayList<String> education = new ArrayList<String>();
        ArrayList<String> jobExperience = new ArrayList<String>();
        ArrayList<String> jobRequirements = new ArrayList<String>();
        ArrayList<String> references = new ArrayList<String>();

        String edc1 = "Kristelig Gymnas, 2007-2010, college";
        String edc2 = "Høgskolen i Oslo og Akershus, 2010-2013, Computer Science Bachelor";
        String edc3 = "Monterey Bay, 2014-2019, Masters";
        education.add(edc1);
        education.add(edc2);
        education.add(edc3);

        String jobXp1 = "Ica Maxi Strømmen, 2007-2012";
        String jobXp2 = "Boss Europe, 2012-2014";
        String jobXp3 = "Freeland, 2013-";
        jobExperience.add(jobXp1);
        jobExperience.add(jobXp2);
        jobExperience.add(jobXp3);

        String jobReq1 = "Frontend development";
        String jobReq2 = "Fullstack developer";
        String jobReq3 = "IT consultant";
        jobRequirements.add(jobReq1);
        jobRequirements.add(jobReq2);
        jobRequirements.add(jobReq3);

        String ref1 = "Karen Hannasvik, email, telefon";
        String ref2 = "Helge Mood, email, telefon";
        String ref3 = "Martin Ahrn, email, telefon";
        references.add(ref1);
        references.add(ref2);
        references.add(ref3);
        /*

        Employee employee1 = new Employee("Mathias", "Lund Ahrn",
                "Fjellstadvegen 31", 2009, "Nordby",
                123123, 28, 200, education, jobExperience, jobRequirements, references);

        //System.out.println(employee1);

        //System.out.println(employee1.getEducation());

        WriterCSV.writeClient(employee1);*/

        try {
            System.out.println(ReaderCSV.readClient("clients.csv").toString());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}