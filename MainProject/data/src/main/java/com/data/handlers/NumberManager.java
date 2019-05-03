package com.data.handlers;

import com.data.clients.Employer;
import com.data.clients.Substitute;
import com.data.work.AvailablePosition;
import com.logic.concurrency.ReaderThreadStarter;
import com.logic.filePaths.ActivePaths;
import com.logic.utilities.exceptions.NumberGenerationException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * <h1>Number Manager</h1>
 *
 * Singleton Enum that provides an internal numbering of each object created in this program.
 *
 * @author Candidate 511
 * @since 22-04-19
 */
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
            e.printStackTrace();
            substituteNumber = 1;
        }
        try {
            generateEmployerNumber();
        } catch (NumberGenerationException e) {
            e.getMessage();
            e.printStackTrace();
            employerNumber = 1;
        }
        try {
            generateAvailablePositionNumber();
        } catch (NumberGenerationException e) {
            e.getMessage();
            e.printStackTrace();
            availablePositionNumber = 1;
        }
    }

    private void generateSubstituteNumber() throws NumberGenerationException {
        try {
            ArrayList<Substitute> substitutes = ReaderThreadStarter.startReader(ActivePaths.getSubstituteCSVPath());
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
            ArrayList<Employer> employers = ReaderThreadStarter.startReader(ActivePaths.getEmployerCSVPath());
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
            ArrayList<AvailablePosition> positions = ReaderThreadStarter.startReader(ActivePaths.getAvailablePositionCSVPath());
            if (positions.size() == 0) {
                this.availablePositionNumber = 1;
            } else {
                this.availablePositionNumber = positions.get(positions.size() - 1).getAvailablePositionNumber();
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
