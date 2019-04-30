package com.data.handlers;

import com.data.clients.Employer;
import com.data.clients.Substitute;
import com.data.work.AvailablePosition;
import com.logic.concurrency.ReaderThreadStarter;
import com.logic.utilities.FilePaths;
import com.logic.utilities.exceptions.NumberGenerationException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public enum NumberManager {
    INSTANCE;


    private int substituteNumber = 0;
    private int employerNumber = 0;
    private int availablePositionNumber = 0;

    NumberManager() {
        try {
            generateSubstituteNumber();
        } catch (NumberGenerationException e) {
            e.getMessage();
            substituteNumber = 1;
        }
        try {
            generateEmployerNumber();
        } catch (NumberGenerationException e) {
            e.getMessage();
            employerNumber = 1;
        }
        try {
            generateAvailablePositionNumber();
        } catch (NumberGenerationException e) {
            e.getMessage();
            availablePositionNumber = 1;
        }
    }

    private void generateSubstituteNumber() throws NumberGenerationException {
        try {
            ArrayList<Substitute> substitutes = ReaderThreadStarter.startReader(FilePaths.SUBSTITUTESCSV.toString());
            if (substitutes.size() == 0) {
                this.substituteNumber = 1;
            } else {
                this.substituteNumber = substitutes.get(substitutes.size() - 1).getSubstituteNumber();
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            throw new NumberGenerationException("Couldn't find or load file");
        }
    }

    private void generateEmployerNumber() throws NumberGenerationException {
        try {
            ArrayList<Employer> employers = ReaderThreadStarter.startReader(FilePaths.EMPLOYERCSV.toString());
            if (employers.size() == 0) {
                this.employerNumber = 1;
            } else {
                this.employerNumber = employers.get(employers.size()-1).getEmployerNumber();
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            throw new NumberGenerationException("Couldn't find or load file");
        }
    }

    private void generateAvailablePositionNumber() throws NumberGenerationException {
        try {
            ArrayList<AvailablePosition> positions = ReaderThreadStarter.startReader(FilePaths.AVAILABLEPOSITIONCSV.toString());
            if (positions.size() == 0) {
                this.availablePositionNumber = 1;
            } else {
                this.availablePositionNumber = positions.get(positions.size() - 1).getPositionsID();
            }
        } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                throw new NumberGenerationException("Couldn't find or load file");
        }
    }

    public int getSubstituteNumberAndIncrement() {
        int current = substituteNumber;
        this.substituteNumber = substituteNumber + 1;
        return current;
    }

    public int getEmployerNumberAndIncrement() {
        int current = employerNumber;
        this.employerNumber = employerNumber + 1;
        return current;
    }

    public int getAvailablePositionNumberAndIncrement() {
        int current = availablePositionNumber;
        this.availablePositionNumber = availablePositionNumber + 1;
        return current;
    }

}
