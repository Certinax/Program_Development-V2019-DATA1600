package com.logic.concurrency;

import com.logic.io.writer.WriterCSV;
import com.logic.utilities.validators.ListValidator;
import com.logic.utilities.validators.StringValidator;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * <h1>CSV Writer Thread</h1>
 *
 * Class for using the CSV-writer in it's own thread with any object in an
 * ObservableList
 *
 * @author Fredrik Pedersen
 * @since 19-04-2019
 */

public class CSVWriterThread implements Runnable {

    private Object objectToWrite = null;
    private ObservableList data = null;
    private String path;

    protected CSVWriterThread(Object objectToWrite, String path) { // contructor for writing only one object to file
        this.objectToWrite = Objects.requireNonNull(objectToWrite);
        this.path = StringValidator.requireNonNullAndNotEmpty(path);
    }

    protected <T> CSVWriterThread(ObservableList<T> data, String path) { // constructor for writing several objects to file
        this.data = ListValidator.requireNonNullObservable(data);
        this.path = StringValidator.requireNonNullAndNotEmpty(path);
    }

    @Override
    public void run() {
        System.out.println("Writing to file with thread " + Thread.currentThread().getId());

        try {
            writeObjects();
        } catch (IllegalAccessException | InvocationTargetException | ClassNotFoundException | IOException e) { // Exceptions thrown to run must be handled here
            e.printStackTrace();
        }
    }

    private void writeObjects() throws IllegalAccessException, InvocationTargetException, ClassNotFoundException, IOException {
        WriterCSV writer = new WriterCSV();

        if (objectToWrite != null) {
            writer.writeObject(objectToWrite, path);
        } else {
            writer.writeObjects(data, path);
        }
    }

}
