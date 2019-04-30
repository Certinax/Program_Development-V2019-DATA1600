package com.data.handlers;

import com.data.clients.Employer;
import com.data.clients.Substitute;
import com.logic.concurrency.ReaderThreadStarter;
import com.logic.utilities.FilePaths;
import com.logic.utilities.exceptions.IdGenerationException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public enum IdManager {
    INSTANCE;


    private int substituteNumber = 0;
    private int employerId = 0;
    private int availablePositionId = 0;

    IdManager() {
        try {
            generateSubstituteId();
        } catch (IdGenerationException e) {
            e.getMessage();
            substituteNumber = 1;
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
            ArrayList<Substitute> substitutes = ReaderThreadStarter.startReader(FilePaths.SUBSTITUTESCSV.toString());
            if (substitutes.size() == 0) {
                this.substituteNumber = 1;
            } else {
                this.substituteNumber = substitutes.get(substitutes.size() - 1).getSubstituteNumber();
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            throw new IdGenerationException("Couldn't find or load file");
        }
    }

    private void generateEmployerId() throws IdGenerationException{
        try {
            ArrayList<Employer> employers = ReaderThreadStarter.startReader(FilePaths.EMPLOYERCSV.toString());
            if (employers.size() == 0) {
                this.employerId = 1;
            } else {
                this.employerId = employers.get(employers.size()-1).getEmployerId();
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            throw new IdGenerationException("Couldn't find or load file");
        }
    }

    public int getSubstituteIdAndIncrement() {
        int current = substituteNumber;
        this.substituteNumber = substituteNumber + 1;
        return current;
    }

    public int getEmployerIdAndIncrement() {
        int current = employerId;
        this.employerId = employerId + 1;
        return current;
    }

}
