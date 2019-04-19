package com.logic.concurrency;

import com.logic.io.writer.WriterCSV;
import com.logic.validators.ListValidator;
import com.logic.validators.StringValidator;
import javafx.collections.ObservableList;

import java.util.Objects;

/**
 * <h1>CSV Writer Thread</h1>
 *
 * Class for
 */

public class CSVWriterThread implements Runnable {

    private Object objectToWrite = null;
    private ObservableList<Object> data = null;
    private String path;
    private boolean append;

    public CSVWriterThread(Object objectToWrite, String path, boolean append) { //contructor for writing only one object to file
        this.objectToWrite = Objects.requireNonNull(objectToWrite);
        this.path = StringValidator.requireNonNullAndNotEmpty(path);
        this.append = append;
    }

    public CSVWriterThread(ObservableList<Object> data, String path, boolean append) { //constructor for writing several objects to file
        if (ListValidator.requireNonNullObject(data)) {
            this.data = data;
        }

        this.path = StringValidator.requireNonNullAndNotEmpty(path);
        this.append = append;
    }

    @Override
    public void run() {
        System.out.println("Writing to file with thread " + Thread.currentThread().getId());

    }

    private void writeObject() {
        if (objectToWrite != null) {
            WriterCSV.writeObject(objectToWrite, path, append);
        } else {
            for (int i = 0; i < data.size(); i++) {
                WriterCSV.writeObject(data.get(i), path, append);
                if (i == 0) { //
                    append = true;
                }
            }
        }
    }


}
