package com.gui;

import com.data.client.SortingTemplates;
import com.data.client.Substitute;
import com.logic.io.writer.WriterCSV;
import com.logic.io.writer.WriterCSV2;

import java.lang.reflect.Method;
import java.util.*;

public class CombinedTesting {

    public static void main(String[] args) {


        ArrayList<String> job = new ArrayList<>();
        job.add("Job");
        ArrayList<String> education = new ArrayList<>();
        education.add("Education");
        ArrayList<String> jobxp = new ArrayList<>();
        jobxp.add("JobXp");
        ArrayList<String> ref = new ArrayList<>();
        ref.add("References");

        Substitute sub1 = new Substitute("Ole", "Kristiansen", "Hovligata 12", 1121, "Oslo",
                1232123, 22, 30, education, jobxp, job,  ref);

        //WriterCSV.writeObject(sub1, "employees.csv", true);

       try {
            WriterCSV2.writeObject(sub1, "test.csv", true, SortingTemplates.substituteTemplate());
        } catch (Exception e) {
            e.printStackTrace();
        }



        //Method[] methods = sub1.getClass().getDeclaredMethods();
        //System.out.println(methods[1].getName());
    }
}
