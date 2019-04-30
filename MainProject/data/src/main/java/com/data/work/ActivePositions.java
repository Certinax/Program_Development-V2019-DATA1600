package com.data.work;

import com.data.CSVWriteable;
import com.data.clients.Substitute;

import java.io.Serializable;
import java.util.ArrayList;

public class ActivePositions implements Serializable, CSVWriteable {

    private String availablePositionId;
    private ArrayList<String> substituteId;
    private boolean active = false;

    public ActivePositions(ArrayList<String> substituteId, String availablePositionId) {
        this.substituteId = substituteId;
        this.availablePositionId = availablePositionId;
    }

    public void setActive(boolean status) {
        this.active = status;
    }

    public ArrayList<String> getSubstituteId() {
        return substituteId;
    }

    public String getAvailablePositionId() {
        return availablePositionId;
    }

    public boolean isActive() {
        return active;
    }

    public String[] template() {
        return new String[] {"getAvailablePositionId", "getSubstituteId", "isActive", this.getClass().getName()};
    }

    @Override
    public String toString() {
        return "ActivePositions{" +
                "availablePositionId='" + availablePositionId + '\'' +
                ", substituteId=" + substituteId +
                ", active=" + active +
                '}';
    }
}
