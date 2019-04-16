package com.data.work;

import com.data.clients.Subsitute;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/**
 * <h1>Temopraryposistion</h1>
 *
 * This class makes an temporary position object that keep track of
 *      - What type of job
 *      - Duration for the position
 *      - Which substitutes
 *      - 
 */

public class Temporaryposistion {

    private Job job;
    private LocalDate[] duration;
    private ArrayList<Subsitute> subsitutes;
    private int numberOfPositions;
    private boolean staffed = false;
    private UUID temporaryPositionID;


    public Temporaryposistion(Job job, LocalDate[] duration, ArrayList<Subsitute> subsitutes, int numberOfPositions, boolean staffed) {
        this.job = job;
        this.duration = duration;
        this.subsitutes = subsitutes;
        this.numberOfPositions = numberOfPositions;
        this.staffed = staffed;
        this.temporaryPositionID = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "Temporaryposistion{" +
                "job=" + job +
                ", duration=" + Arrays.toString(duration) +
                ", numberOfPositions=" + numberOfPositions +
                ", staffed=" + staffed +
                '}';
    }

    public ArrayList<Subsitute> getSubsitutes() {
        return subsitutes;
    }

    public void setSubsitutes(ArrayList<Subsitute> subsitutes) {
        this.subsitutes = subsitutes;
    }

    public UUID getTemporaryPositionID() {
        return temporaryPositionID;
    }

    public void setTemporaryPositionID(UUID temporaryPositionID) {
        this.temporaryPositionID = temporaryPositionID;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public LocalDate[] getDuration() {
        return duration;
    }

    public void setDuration(LocalDate[] duration) {
        this.duration = duration;
    }

    public int getNumberOfPositions() {
        return numberOfPositions;
    }

    public void setNumberOfPositions(int numberOfPositions) {
        this.numberOfPositions = numberOfPositions;
    }

    public boolean isStaffed() {
        return staffed;
    }

    public void setStaffed(boolean staffed) {
        this.staffed = staffed;
    }
}
