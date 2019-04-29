package com.data.handlers;

import com.data.clients.Employer;
import com.data.clients.Substitute;
import com.logic.concurrency.ReaderThreadStarter;
import com.logic.utilities.exceptions.IdGenerationException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public enum IdManager {
    INSTANCE;


    private int substituteId = 0;
    private int employerId = 0;
    private int availablePositionId = 0;

    IdManager() {
        try {
            generateSubstituteId();
        } catch (IdGenerationException e) {
            e.getMessage();
            substituteId = 1;
        }
        try {
            generateEmployerId();
        } catch (IdGenerationException e) {
            e.getMessage();
            employerId = 1;
        }
    }

    private void generateSubstituteId() throws IdGenerationException{
        try {
            ArrayList<Substitute> substitutes = ReaderThreadStarter.startReader("resources/substitutes.csv");
            this.substituteId = substitutes.get(substitutes.size()-1).getSubstituteId();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            throw new IdGenerationException("Couldn't find or load file");
        }
    }

    private void generateEmployerId() throws IdGenerationException{
        try {
            ArrayList<Employer> employers = ReaderThreadStarter.startReader("resources/employers.csv");
            this.employerId = employers.get(employers.size()-1).getEmployerId();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            throw new IdGenerationException("Couldn't find or load file");
        }
    }

    public int getSubstituteIdAndIncrement() {
        int current = substituteId;
        this.substituteId = substituteId + 1;
        return current;
    }

    public int getEmployerIdAndIncrement() {
        int current = employerId;
        this.employerId = employerId + 1;
        return current;
    }

}
