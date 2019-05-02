package com.logic.utilities.validators;

import javafx.scene.Node;

import java.util.Map;

import static java.util.Map.entry;

public enum RequiredDataContainer {



    SUBSTITUTE() {
        @Override
        public Map<String, String> requiredData() {
            Map<String, String> temp = Map.ofEntries(
                    entry("firstname", "String"),
                    entry("lastname", "String"),
                    entry("address", "String"),
                    entry("age", "int"),
                    entry("zipcode", "int"),
                    entry("city", "String"),
                    entry("industry", "String")
            );
            return temp;
        }
    },

    EMPLOYER() {
        @Override
        public Map<String,String> requiredData() {
            Map<String, String> temp = Map.ofEntries(
                    entry("name", "String"),
                    entry("address", "String"),
                    entry("zipcode", "int"),
                    entry("city", "String"),
                    entry("privateSector", "boolean"),
                    entry("industry", "String")
            );
            return temp;
        }
    },

    AVAILABLE_POSITION() {
        @Override
        public Map<String, String> requiredData() {
            Map<String, String> temp = Map.ofEntries(
                    entry("employerId", "String"),
                    entry("numberOfPositions", "int"),
                    entry("publicSector", "boolean")
            );
            return temp;
        }
    };

    public abstract Map<String,String> requiredData();
}
