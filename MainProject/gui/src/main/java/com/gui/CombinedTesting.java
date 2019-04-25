package com.gui;

import com.data.client.Substitute;
import com.logic.io.writer.WriterCSV;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class CombinedTesting {

    public static void main(String[] args) {

        ArrayList<String> jobxp = new ArrayList<>();
        jobxp.add("Bane Nor");
        jobxp.add("Deloitte");
        jobxp.add("DNB");

        Substitute sub1 = new Substitute.Builder("Petter", "Olsen", "Frogner Alle 20", 24,
                1212, "Oslo", "Banking").salaryRequirement(22).workExperience(jobxp).build();

        System.out.println(sub1);

        try {
            WriterCSV.writeObject(sub1, "test.csv", true);
        } catch (IllegalAccessException | InvocationTargetException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
