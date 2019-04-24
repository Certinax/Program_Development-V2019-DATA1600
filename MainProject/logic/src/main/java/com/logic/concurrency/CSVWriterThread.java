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
    private boolean append;
    private String[] template;

    protected CSVWriterThread(Object objectToWrite, String path, boolean append, String[] template) { // contructor for writing only one object to file
        this.objectToWrite = Objects.requireNonNull(objectToWrite);
        this.path = StringValidator.requireNonNullAndNotEmpty(path);
        this.append = append;
        this.template = template;
    }

    protected <T> CSVWriterThread(ObservableList<T> data, String path, boolean append, String[] template) { // constructor for writing several objects to file
        this.data = ListValidator.requireNonNullObservable(data);
        this.path = StringValidator.requireNonNullAndNotEmpty(path);
        this.append = append;
        this.template = template;
    }

    @Override
    public void run() {
        System.out.println("Writing to file with thread " + Thread.currentThread().getId());
        try {
            writeObject();
        } catch (IllegalAccessException | InvocationTargetException | ClassNotFoundException | IOException e) { // Exceptions thrown to run must be handled here
            e.printStackTrace();
        }
    }

    private void writeObject() throws IllegalAccessException, InvocationTargetException, ClassNotFoundException, IOException {
        if (objectToWrite != null) {
            WriterCSV.writeObject(objectToWrite, path, append, template);
        } else {
            for (Object aData : data) {
                WriterCSV.writeObject(aData, path, append, template);
                if (!append) {
                    append = true;
                }
            }
        }
    }

}
