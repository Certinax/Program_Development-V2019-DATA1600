package com.logic.utilities.validators;

import javafx.scene.Node;

import java.util.Map;

import static java.util.Map.entry;

public enum RequiredDataContainer {



    SUBSTITUTE() {
        @Override
        public Map<String, String> requiredData() {
            Map<String, String> temp = Map.ofEntries(
                    entry("firstnameField", "String"),
                    entry("lastnameField", "String"),
                    entry("streetnameField", "String"),
                    entry("birthdayField", "int"),
                    entry("zipCodeField", "int"),
                    entry("cityField", "String"),
                    entry("industryList", "String")
            );
            return temp;
        }
    },

    EMPLOYER() {
        @Override
        public Map<String,String> requiredData() {
            Map<String, String> temp = Map.ofEntries(
                    entry("Name", "String"),
                    entry("Address", "String"),
                    entry("Zipcode", "int"),
                    entry("City", "String"),
                    entry("Sector", "String"),
                    entry("Industry", "ArrayList<String>"),
                    entry("Joblist", "ArrayList<String>")
            );
            return temp;
        }
    };

    public abstract Map<String,String> requiredData();
}
