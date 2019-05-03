package com.logic.utilities.validators;


import java.util.Map;

/**
 * <h1>RequireDataContainer</h1>
 *
 * This enum holds on to fixed maps of data that each object needs to have.
 * Used for comparing other maps.
 *
 * @author Candidate 511
 * @since 19-04--2019
 */

import static java.util.Map.entry;

public enum RequiredDataContainer {



    SUBSTITUTE() {
        @Override
        public Map<String, String> requiredData() {
            Map<String, String> temp = Map.ofEntries(
                    entry("firstname", "String"),
                    entry("lastname", "String"),
                    entry("address", "String"),
                    entry("phoneField", "int"),
                    entry("emailField", "String"),
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
                    entry("phoneNumber", "int"),
                    entry("email", "String"),
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
                    entry("publicSector", "boolean"),
                    entry("industry", "String"),
                    entry("position", "String")
            );
            return temp;
        }
    };

    public abstract Map<String,String> requiredData();
}
