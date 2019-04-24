package com.data.client;

import java.util.Map;

import static java.util.Map.entry;

// TODO THIS ENUM IS TEMPORARILY NOT IN USE, REMOVE UPON DELIVERY UNLESS IT BECOMES USEFUL

public enum Template {

    SUBSTITUTE() {
        @Override
        public Map<String, String> template() {
            Map<String, String> temp = Map.ofEntries(
                    entry("Firstname", "String"),
                    entry("Lastname", "String"),
                    entry("Address", "String"),
                    entry("Zipcode", "int"),
                    entry("City", "String"),
                    entry("Ssn", "int"),
                    entry("SalaryRequirement", "int"),
                    entry("Education", "ArrayList<String>"),
                    entry("JobExperience", "ArrayList<String>"),
                    entry("JobRequirements", "ArrayList<String>"),
                    entry("References", "ArrayList<String>")
            );
            return temp;
        }
    },

    EMPLOYER() {
        @Override
        public Map<String,String> template() {
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

    public abstract Map<String,String> template();
}
