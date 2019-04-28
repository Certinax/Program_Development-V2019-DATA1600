package com.gui;

import com.data.client.SortingTemplates;
import com.data.client.Substitute;
import com.logic.io.reader.Reader;
import com.logic.io.reader.ReaderCSV;
import com.logic.io.reader.ReaderJOBJ;
import com.logic.io.writer.WriterCSV;
import com.logic.io.writer.Writer;
import com.logic.io.writer.WriterJOBJ;
import com.logic.utilities.exceptions.CSVParseException;

import java.io.IOException;
import java.util.ArrayList;

public class CombinedTesting {

    public static void main(String[] args) {
/*
        ArrayList<String> education = new ArrayList<>();
        education.add("OsloMet");
        education.add("Software Engineering");
        education.add("2017");
        ArrayList<String> jobxp = new ArrayList<>();
        jobxp.add("WikborgRein");
        jobxp.add("2017");
        jobxp.add("2019");
        ArrayList<String> ref = new ArrayList<>();
        ref.add("Kim Knudsen");
        ref.add("kimmelim@mail.com");
        ref.add("99106201");

        Substitute sub1 = new Substitute.Builder("Petter", "Olsen", "Sveitsrupveien 20", 24,
                1212, "Oslo", "Banking").salaryRequirement(220000).education(education).workExperience(jobxp).workReference(ref).build();

        Substitute sub2 = new Substitute.Builder("Victor", "Pishva", "Sveitsrupveien 20", 25,
                2007, "Oslo", "Banking").salaryRequirement(200000).education(education).workExperience(jobxp).workReference(ref).build();


        System.out.println(sub1);

        write(new WriterCSV(), sub1, "resources/substitutes.csv", true, SortingTemplates.substituteTemplate());
        write(new WriterJOBJ(), sub1, "resources/substitutes.jobj", false);
        //System.out.println(read(new ReaderJOBJ(), "resources/substitutes.jobj"));*/

        ReaderCSV reader = new ReaderCSV();

        ArrayList<Substitute> sub = new ArrayList<>();

        try {
            Class clazz = Class.forName("com.data.client.Substitute");
            sub = reader.read("resources/substitutes.csv", clazz);
        } catch (ClassNotFoundException | CSVParseException e) {
            e.printStackTrace();
        }
/*
        for (Substitute s : sub) {
            System.out.println(s.toString());
        }
        Substitute s = sub.get(2);

        System.out.println(s.getEducation().get(0));*/
    }

    private static void write(Writer writer, Object obj, String path, boolean append, String[] template) {
        try {
            writer.writeObject(obj, path, append, template);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void write(Writer writer, Object obj, String path, boolean append) {
        try {
            writer.writeObject(obj, path, append);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Object> read(Reader reader, String path) {
        return reader.readObjects(path);
    }
}
