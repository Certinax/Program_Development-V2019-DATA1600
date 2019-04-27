package com.gui;

import com.data.client.Substitute;
import com.logic.io.reader.Reader;
import com.logic.io.reader.ReaderJOBJ;
import com.logic.io.writer.Writer;
import com.logic.io.writer.WriterCSV;
import com.logic.io.writer.WriterJOBJ;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
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

        write(new WriterCSV(), sub1, "resources/substitutes.csv");
        write(new WriterJOBJ(), sub1, "resources/substitutes.jobj");
        System.out.println(read(new ReaderJOBJ(), "resources/substitutes.jobj"));

    }

    private static void write(Writer writer, Object obj, String path) {
        try {
            writer.writeObject(obj, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static <T> void write(Writer writer, ObservableList<T> list, String path) {
        try {
            writer.writeObjects(list, path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Object> read(Reader reader, String path) {
        try {
            return reader.readObjects(path);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
