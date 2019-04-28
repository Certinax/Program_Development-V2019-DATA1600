package com.logic.concurrency;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <h1>CSV Reader Thread Starter</h1>
 *
 * Class for handling the CSV reader's thread
 *
 * @author Fredrik Pedersen
 * @since 23-04-2019
 */

//TODO EXAMPLE IMPLEMENTATION. ACTUAL IMPLEMENTATION INCOMING WHEN READER IS READY
public class ReaderThreadStarter {

    private ReaderThreadStarter() { //Private constructor to deter initialization
    }

    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> startReader(String path) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(1);
        ArrayList<T> result = null;
        Future<ArrayList<T>> returnedList = service.submit(new ReaderThread(path));

        result = returnedList.get();
        service.shutdown();
        return result;
    }
}
