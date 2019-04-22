package com.gui;

import com.data.client.Substitute;
import com.logic.io.writer.WriterCSV;

import java.util.ArrayList;

public class CombinedTesting {

    public static void main(String[] args) {


        ArrayList<String> job = new ArrayList<>();
        job.add("asdaasd");
        ArrayList<String> education = new ArrayList<>();
        education.add("asd");
        ArrayList<String> jobxp = new ArrayList<>();
        jobxp.add("aisd");
        ArrayList<String> ref = new ArrayList<>();
        ref.add("asdasd");

        Substitute sub1 = new Substitute("Ole", "Kristiansen", "Hovligata 12", 1121, "Oslo",
                1232123, 22, 22, education, jobxp, job,  ref);

        WriterCSV.writeObject(sub1, "employees.csv", true);
    }

}
