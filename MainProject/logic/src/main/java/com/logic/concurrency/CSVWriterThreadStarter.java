package com.logic.concurrency;

import javafx.collections.ObservableList;

/**
 * <h1>CSV Writer Thread Starter</h1>
 *
 * Class for handling the CSV writer's thread
 */

public class CSVWriterThreadStarter {

    private CSVWriterThreadStarter() { //Private constructor to deter initialization
    }

    public static <T extends Object> void startWriter(Object objectToWrite, String path, boolean append, String[] template) {
        Thread writerThread = new Thread(new CSVWriterThread(objectToWrite, path, append, template));
        writerThread.start();

        try {
            writerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static <T extends Object> void startWriter(ObservableList<T> data, String path, boolean append, String[] template) {
        Thread writerThread = new Thread(new CSVWriterThread(data, path, append, template));
        writerThread.start();

        try {
            writerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
