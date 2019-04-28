package com.gui;

import com.data.clients.Substitute;
import com.logic.concurrency.ReaderThreadStarter;
import com.logic.concurrency.WriterThreadStarter;
import com.logic.io.reader.Reader;
import com.logic.io.reader.ReaderCSV;
import com.logic.io.reader.ReaderJOBJ;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CombinedTesting {

    public static void main(String[] args) throws Exception {

        final String SUBCSV_PATH = "resources/substitutes.csv";
        final String SUBJOBJ_PATH = "resources/substitutes.jobj";

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

        WriterThreadStarter.startWriter(list, SUBCSV_PATH);
        //WriterThreadStarter.startWriter(list, SUBJOBJ_PATH);
        // System.out.println(read(new ReaderJOBJ(), SUBJOBJ_PATH));
        ReaderCSV readerCSV = new ReaderCSV();
        //readerCSV.read(SUBCSV_PATH);

        ArrayList<Substitute> subs = ReaderThreadStarter.startReader(SUBCSV_PATH);



        ObservableList<Substitute> obsSubs = FXCollections.observableArrayList(subs);

        for (Substitute s : obsSubs) {
            System.out.println(s);
        }



        for(Substitute s : subs) {
            System.out.println(s.getFirstname());
        }


    }

}
