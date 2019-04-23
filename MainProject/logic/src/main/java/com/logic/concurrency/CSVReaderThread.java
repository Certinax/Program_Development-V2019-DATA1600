package com.logic.concurrency;

import javafx.collections.ObservableList;

import java.util.concurrent.Callable;

/**
 * <h1>CSV Reader Thread</h1>
 *
 * Class for using the CSV-reader in it's own thread with any object in an ObservableList
 * @param <T> generic representing whatever object you wish to read
 * @author Fredrik Pedersen
 * @since 23-04-2019
 */

//TODO EXAMPLE IMPLEMENTATION. ACTUAL IMPLEMENTATION INCOMING WHEN READER IS READY
public class CSVReaderThread<T> implements Callable<ObservableList<T>> {

    String path;

    protected CSVReaderThread(String path) {
        this.path = path;
    }

    @Override
    public ObservableList<T> call() {
        //return CSVReader.readCSVFile(path);
        return null;
    }
}
