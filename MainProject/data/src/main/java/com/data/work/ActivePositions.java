package com.data.work;

import com.data.clients.Substitute;

import java.util.ArrayList;

public class ActivePositions {

    private ArrayList<String> substituteId;
    private String availablePositionId;
    private boolean active = false;

    public ActivePositions(ArrayList<String> substituteId, String availablePositionId) {
        this.substituteId = substituteId;
        this.availablePositionId = availablePositionId;
    }

    public void setActive(boolean status) {
        this.active = status;
    }
}
