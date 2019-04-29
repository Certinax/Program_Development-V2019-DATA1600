package com.data.handlers;


import com.data.clients.Substitute;
import com.logic.concurrency.ReaderThreadStarter;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

// TODO THIS IdHandler CAN MOST LIKELY BE MOVED TO DEPRECATED AFTER CONSULTATION AND EVALUATION OF IdManager

public class IdHandler {


    private int employerId;
    private AtomicInteger substituteId;

    private ArrayList<Substitute> sublist;

    public IdHandler() {
        if(!idChecker()) {
            substituteId = new AtomicInteger(1);
        }

    }

    private boolean idChecker() {
        try {
            sublist = ReaderThreadStarter.startReader("resources/substitutes.csv");
            //this.substituteId = new AtomicInteger(sublist.get(sublist.size()-1).getSubstituteId());
            return true;
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getSubstituteIdAndIncrement() {
        return substituteId.getAndIncrement();
    }

    public AtomicInteger getSubstituteId() {
        return substituteId;
    }

}
