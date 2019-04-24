package com.data.client;


public final class SortingTemplates {

    public static String[] substituteTemplate() {
        return new String[] {"getId", "getFirstname", "getLastname", "getAddress", "getZipcode", "getCity", "getAge", "getSalaryRequirement",
        "getIndustry", "getEducation", "getJobExperience", "getReferences"};
    }

    public static String[] testTemplate() {
        return new String[] {"getName", "getAddress", "getAge"};
    }


    public static String[] jobTemplate() {
        return new String[] {"getTitle"};
    }
}
