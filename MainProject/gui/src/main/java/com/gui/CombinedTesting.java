package com.gui;

import com.data.client.Substitute;
import com.logic.io.reader.Reader;
import com.logic.io.sorting.SortingTemplates;
import com.logic.io.writer.WriterCSV;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class CombinedTesting {

    public static void main(String[] args) throws Exception {

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


        ObservableList<Substitute> list = FXCollections.observableArrayList();
        list.add(sub1);
        list.add(sub2);
        //WriterJOBJ writer = new WriterJOBJ();
        //writer.writeObjects(list, "resources/substitutes.jobj");

        WriterCSV writerCSV = new WriterCSV();
        writerCSV.writeObjects(list, "resources/substitutes.csv");

        //write(new WriterCSV(), sub1, "resources/substitutes.csv", false, SortingTemplates.substituteTemplate());
        //write(new WriterJOBJ(), sub1, "resources/substitutes.jobj", false);
        //System.out.println(read(new ReaderJOBJ(), "resources/substitutes.jobj"));

    }

   /* private static void write(Writer writer, Object obj, String path, boolean append, String[] template) {
        try {
            writer.writeObjects(obj, path, append, template);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void write(Writer writer, Object obj, String path, boolean append) {
        try {
            writer.writeObjects(obj, path, append);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } */

    private static ArrayList<Object> read(Reader reader, String path) {
        try {
            return reader.readObjects(path);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String[] findTemplate(Object obj) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = obj.getClass().getDeclaredMethods();
        String[] template = null;

        for (int i = 0; i < methods.length; i++) {
            if(methods[i].getName().startsWith("template")) {
                template = (String[])methods[i].invoke(obj);
            }
        }
        return template;
    }
}
