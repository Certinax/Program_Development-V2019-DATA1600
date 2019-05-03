package com.logic.concurrency;
import java.io.File;
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
 * @author Candidate 730
 * @since 23-04-2019
 */

public class ReaderThreadStarter {

    private ReaderThreadStarter() { //Private constructor to deter initialization
    }

    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> startReader(String path) throws ExecutionException, InterruptedException {
        File file = new File(path);
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        ExecutorService service = Executors.newSingleThreadExecutor();
        ArrayList<T> result = null;
        Future<ArrayList<T>> returnedList = service.submit(new ReaderThread(path));
        result = returnedList.get();
        service.shutdown();
        return result;
    }
}
