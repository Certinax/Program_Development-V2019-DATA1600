package com.gui;

import com.data.clients.Employer;
import com.logic.concurrency.ReaderThreadStarter;
import com.logic.concurrency.WriterThread;
import com.logic.concurrency.WriterThreadStarter;
import com.logic.filePaths.ActivePaths;
import com.logic.utilities.exceptions.NumberGenerationException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Test {

    public static void main(String[] args) throws InterruptedException, NumberGenerationException {
        try {
            ArrayList<Employer> employers = ReaderThreadStarter.startReader(ActivePaths.getEmployerCSVPath());
            System.out.println(employers.size());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        Employer emp = new Employer.Builder("Pettersens Eksos", "Industrigata 2", 1212,
                "Oslo", 98981292, "asd@asd.no", true, "Bilverksted").build();

        WriterThreadStarter.startWriter(emp, ActivePaths.getEmployerCSVPath(), true);
        System.out.println(emp);


    }
}
