package com.logic.concurrency;

import com.logic.io.reader.Reader;
import com.logic.io.reader.ReaderCSV;
import com.logic.io.reader.ReaderJOBJ;
import com.logic.utilities.exceptions.CSVParseException;
import com.logic.utilities.exceptions.SerializationException;
import com.logic.utilities.exceptions.UnsupportedFileFormatException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * <h1>CSV Reader Thread</h1>
 *
 * Class for using the CSV-reader in it's own thread with any object in an ObservableList
 * @param <T> generic representing whatever object you wish to read
 *
 * @author Fredrik Pedersen
 * @since 23-04-2019
 */

public class ReaderThread<T> implements Callable<ArrayList<T>> {

    private String path;

    protected ReaderThread(String path) {
        this.path = path;
    }

    @Override
    public ArrayList<T> call() {
        try {
            return readObjects();
        } catch (UnsupportedFileFormatException
                | IOException
                | CSVParseException
                | ClassNotFoundException
                | SerializationException e) {
            return new ArrayList<>();
        }
    }

    private ArrayList<T> readObjects() throws UnsupportedFileFormatException, IOException,
            ClassNotFoundException, CSVParseException, SerializationException {
        Reader reader;
        String[] separator = path.split("\\.");

        switch (separator[separator.length - 1]) {
            case "jobj":
                reader = new ReaderJOBJ();
                break;
            case "csv":
                reader = new ReaderCSV();
                break;
            default:
                throw new UnsupportedFileFormatException("Wrong file format selected. Please select a .csv or .jobj-file. No data was saved");
        }

        return reader.readObjects(path);
    }
}
