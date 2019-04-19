package com.data.work;

import java.util.UUID;

import static com.logic.utilities.validators.StringValidator.stringLengthIsValid;

/**
 * <h1>Job</h1>
 *
 * This class is used in combination with Temporaryosistion to link jobs and positions together
 *
 * @author Mathias Lund Ahrn
 * @since 16-04-2019
 */

public class Job {

    private String title;
    private String description;
    private UUID jobID;
    private final static int MAX_LENGTH = 200;

    public Job(String title, String description) {
        if(!stringLengthIsValid(description, MAX_LENGTH)) {
            throw new IllegalStateException("Description section exceeds max limit of " + MAX_LENGTH + " characters");
        }
        this.title = title;
        this.description = description;
        this.jobID = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "Job{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", jobID=" + jobID +
                '}';
    }

    public static int getMaxLength() {
        return MAX_LENGTH;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getJobID() {
        return jobID;
    }

    public void setJobID(UUID jobID) {
        this.jobID = jobID;
    }
}
