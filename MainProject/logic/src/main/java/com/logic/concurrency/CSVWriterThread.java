package com.logic.concurrency;
import com.logic.utilities.validators.ListValidator;
import com.logic.utilities.validators.StringValidator;
import javafx.collections.ObservableList;
import java.util.Objects;

/**
 * <h1>CSV Writer Thread</h1>
 *
 * Class for using the CSV-writer in it's own thread with any object in an ObservableList
 *
 * @author Fredrik Pedersen
 * @since 19-04-2019
 */

public class CSVWriterThread implements Runnable {

    private Object objectToWrite = null;
    private ObservableList data = null;
    private String path;
    private boolean append;

    public CSVWriterThread(Object objectToWrite, String path, boolean append) { //contructor for writing only one object to file
        this.objectToWrite = Objects.requireNonNull(objectToWrite);
        this.path = StringValidator.requireNonNullAndNotEmpty(path);
        this.append = append;
    }

    public <T extends Object> CSVWriterThread(ObservableList<T> data, String path, boolean append) { //constructor for writing several objects to file
        this.data = ListValidator.requireNonNullObservable(data);
        this.path = StringValidator.requireNonNullAndNotEmpty(path);
        this.append = append;
    }

    @Override
    public void run() {
        System.out.println("Writing to file with thread " + Thread.currentThread().getId());
        //writeObject();

    }

    /*private void writeObject() {
        if (objectToWrite != null) {
            WriterCSV.writeObject(objectToWrite, path, append);
        } else {
            for (Object aData : data) {
                WriterCSV.writeObject(aData, path, append);
                if (!append) {
                    append = true;
                }
            }
        }
    }*/


}
