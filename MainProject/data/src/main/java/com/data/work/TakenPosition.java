package com.data.work;

import com.data.CSVWriteable;

import java.io.Serializable;
import java.util.ArrayList;

//TODO Write JavaDocs!
public class TakenPosition extends AvailablePosition implements Serializable, CSVWriteable {

    private int substituteID;

    private TakenPosition() {}

    private TakenPosition(Builder builder) {
        super(builder);
        substituteID = builder.substituteID;
        setApplicants(null);
    }

    public static class Builder extends AvailablePosition.Builder<Builder> {
        //Required parameters
        private final int substituteID;

        public Builder(int positionsID, Boolean publicSector, String workplace, int employer, String positionType,
                       String industry, int duration, int salary, String contactInfo, ArrayList<Integer> applicants,
                       int substituteID) {

            super(positionsID, publicSector, workplace, employer, positionType , industry , duration, salary, contactInfo, applicants);
            this.substituteID = substituteID;

        }

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public TakenPosition build() {
            return new TakenPosition(this);
        }

        public int getSubstituteID() {
            return substituteID;
        }

    }

    @Override
    public String[] template() {
        return new String[] {""}; //TODO Correctly implement this
    }

    @Override
    public String toString() {
        return super.toString() +
                "Builder{" +
                "substituteID=" + substituteID +
                '}';
    }
}
