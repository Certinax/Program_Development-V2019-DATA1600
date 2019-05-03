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
        }
        try {
            generateEmployerNumber();
        } catch (NumberGenerationException e) {
            e.getMessage();
            e.printStackTrace();
        }
        try {
            generateAvailablePositionNumber();
        } catch (NumberGenerationException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    private void generateSubstituteNumber() throws NumberGenerationException {
        try {
            ArrayList<Substitute> substitutes = ReaderThreadStarter.startReader(ActivePaths.getSubstituteCSVPath());
            if (substitutes.size() == 0) {
                this.substituteNumber = 0;
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
                this.employerNumber = 0;
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
                this.availablePositionNumber = 0;
            } else {
                this.availablePositionNumber = positions.get(positions.size() - 1).getAvailablePositionNumber();
            }
        } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
                throw new NumberGenerationException("Couldn't find or load file");
        }
    }

    public int getSubstituteNumberAndIncrement() throws NumberGenerationException {
        generateSubstituteNumber();
        this.substituteNumber = substituteNumber + 1;
        return substituteNumber;
    }

    public int getEmployerNumberAndIncrement() throws NumberGenerationException {
        generateEmployerNumber();
        this.employerNumber = employerNumber + 1;
        return employerNumber;
    }

    public int getAvailablePositionNumberAndIncrement() throws NumberGenerationException {
        generateAvailablePositionNumber();
        this.availablePositionNumber = availablePositionNumber + 1;
        return availablePositionNumber;
    }

}
