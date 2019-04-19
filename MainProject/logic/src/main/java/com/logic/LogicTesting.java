package com.logic;

import com.logic.concurrency.CSVWriterThreadStarter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class LogicTesting{

    public static void main(String[] args) {

        PersonTesting person = new PersonTesting("Ola", "Nordmann");
        PersonTesting person2 = new PersonTesting("Kari", "Knudsen");

        ObservableList<PersonTesting> testList = FXCollections.observableArrayList();
        testList.add(person);
        testList.add(person2);

        CSVWriterThreadStarter.startWriter(person,"tests.csv", false);
    }
}
