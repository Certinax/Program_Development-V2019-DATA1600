package com.logic.concurrency;

import javafx.collections.ObservableList;

/**
 * <h1>CSV Writer Thread Starter</h1>
 *
 * Class for handling the CSV writer's thread
 *
 * @author Fredrik Pedersen
 * @since 19-04-2019
 */

public class WriterThreadStarter {

    private WriterThreadStarter() { //Private constructor to deter initialization
    }

    /**
     * <h2>startWriter</h2>
     * Method for writing a single object to file.
     *
     * @param objectToWrite the object that shall be written
     * @param path path to the file
     * @throws InterruptedException if any thread has interrupted the current thread. The
     *                              <i>interrupted status</i> of the current thread is
     *                              cleared when this exception is thrown.
     *
     */

    public static void startWriter(Object objectToWrite, String path, boolean append) throws InterruptedException {
        Thread writerThread = new Thread(new WriterThread(objectToWrite, path, append));
        writerThread.start();
        writerThread.join();
    }

    /**
     * <h2>startWriter</h2>
     * Method for writing a list of objects to file.
     *
     * @param data an ObservableList containing all the objects to be written to file
     * @param path path to the file
     * @param <T> a generic used to represent whatever type of object is to be written to file
     * @throws InterruptedException if any thread has interrupted the current thread. The
     *                              <i>interrupted status</i> of the current thread is
     *                              cleared when this exception is thrown.
     */

   public static <T> void startWriter(ObservableList<T> data, String path, boolean append) throws InterruptedException {
        Thread writerThread = new Thread(new WriterThread(data, path, append));
        writerThread.start();
        writerThread.join();
    }
}
