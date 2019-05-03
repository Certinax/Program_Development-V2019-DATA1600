package com.logic.concurrency;

import com.logic.io.writer.Writer;
import com.logic.io.writer.WriterCSV;
import com.logic.io.writer.WriterJOBJ;
import com.logic.utilities.exceptions.UnsupportedFileFormatException;
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
 * @author Candidate 730
 * @since 19-04-2019
 */

public class WriterThread implements Runnable {

    private Object objectToWrite = null;
    private ObservableList data = null;
    private String path;
    private boolean append;

    protected WriterThread(Object objectToWrite, String path, boolean append) { // contructor for writing only one object to file
        this.objectToWrite = Objects.requireNonNull(objectToWrite);
        this.path = StringValidator.requireNonNullAndNotEmpty(path);
        this.append = append;
    }

    protected <T> WriterThread(ObservableList<T> data, String path, boolean append) { // constructor for writing several objects to file
        this.data = ListValidator.requireNonNullObservable(data);
        this.path = StringValidator.requireNonNullAndNotEmpty(path);
        this.append = append;
    }

    @Override
    public void run() {
        System.out.println("Writing to file with thread " + Thread.currentThread().getId());

        try {
            writeObjects();
        } catch (UnsupportedFileFormatException | IllegalAccessException | InvocationTargetException | ClassNotFoundException | IOException e) { // Exceptions thrown to run must be handled here
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void writeObjects() throws UnsupportedFileFormatException, IllegalAccessException,
            InvocationTargetException, ClassNotFoundException, IOException {

        Writer writer;
        String[] separator = path.split("\\.");

        switch (separator[separator.length - 1]) {
            case "jobj":
                writer = new WriterJOBJ();
                break;
            case "csv":
                writer = new WriterCSV();
                break;
            default:
                throw new UnsupportedFileFormatException("Wrong file format selected. Please select a .csv or .jobj-file. No data was saved");
        }

        if (objectToWrite == null) {
            writer.writeObjects(data, path, append);
        } else {
            writer.writeObject(objectToWrite, path, append);
        }

    }

}
